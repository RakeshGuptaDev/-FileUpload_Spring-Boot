package com.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileupload.entity.Files;

@Repository
public interface FileRepo extends JpaRepository<Files,Integer> {
	
	
}