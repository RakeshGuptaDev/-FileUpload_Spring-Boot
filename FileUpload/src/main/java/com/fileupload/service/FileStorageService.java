package com.fileupload.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.entity.Files;
import com.fileupload.exception.FileNotFoundException;
import com.fileupload.exception.FileStorageException;
import com.fileupload.repository.FileRepo;

@Service
public class FileStorageService {

	@Autowired
	private FileRepo fileRepo;

	public Files storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequenth " + fileName);
			}

			Files dbFile = new Files(fileName, file.getContentType(), file.getBytes());
			return (Files) fileRepo.save(dbFile);

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Files getFile(int fileId) throws Throwable {
		return (Files) fileRepo.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File you requested not found with id " + fileId));
	}

	public List<Files> getAllFile() throws Throwable {
		return (List<Files>) fileRepo.findAll();
	}
}