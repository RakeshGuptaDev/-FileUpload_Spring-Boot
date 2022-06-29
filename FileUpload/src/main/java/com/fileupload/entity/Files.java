package com.fileupload.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class Files {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;

	private String type;

	//CLOB – Character Large Object will store large text data
	//BLOB – Binary Large Object is for storing binary data like images, audio, or video
	@Lob
	//@Column(name = "photo", columnDefinition="BLOB")
	private byte[] filecontent;
	
	
	//Blob image;   ---BLOB mapped to java.sql.Blob
	//byte[] image = new byte[] {1, 2, 3};		-----Persisting a java.sql.Blob entity
	public Files() {
		super();
	}
	

	public Files(String name, String type, byte[] filecontent) {
		super();
		this.name = name;
		this.type = type;
		this.filecontent = filecontent;
	}



	public Files(int id, String name, String type, byte[] filecontent) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.filecontent = filecontent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(byte[] filecontent) {
		this.filecontent = filecontent;
	}
	
	

}
