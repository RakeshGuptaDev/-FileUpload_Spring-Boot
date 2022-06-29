package com.fileupload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileupload.entity.Files;
import com.fileupload.service.FileStorageService;
import com.fileupload.service.FileUploadResponse;

@RestController
public class FileController {

	@Autowired
	private FileStorageService fileStorageService;

	// @RequestParam is used to read the HTML form data provided by a user and bind
	// it to the request parameter.
	@PostMapping("/fileupload")
	public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
		Files files = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/filedownload/")
				.path(String.valueOf(files.getId())).toUriString();
		System.out.println(fileDownloadUri);
		return new FileUploadResponse(files.getName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/filedownload/{fileId}")
	public ResponseEntity downloadFile(@PathVariable int fileId) throws Throwable {
		Files dbFile = fileStorageService.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "/attachment; filename=\"" + dbFile.getName() + "\"")
				.body(new ByteArrayResource(dbFile.getFilecontent()));
	}

	@GetMapping("/allfile")
	public List<Files> getAllFiles() throws Throwable {
		return (List<Files>) fileStorageService.getAllFile();
	}

}
