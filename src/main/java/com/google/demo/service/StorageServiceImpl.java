package com.google.demo.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.demo.exception.StorageException;

@Service
public class StorageServiceImpl implements StorageService {

	@Value("${app.upload.path:images}")
	private String path;

	private Path rootLocation;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.rootLocation = Paths.get(path);
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			// TODO: handle exception
			throw new StorageException(e.getMessage());
		}

	}

	@Override
	public String store(MultipartFile multipartFile) {
		if (multipartFile == null || multipartFile.isEmpty())
			return null;

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		try {
			if (fileName.contains(".."))
				throw new StorageException("Path outside");

			fileName = UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
				return fileName;
			}

		} catch (IOException e) {
			throw new StorageException(e.getMessage());
		}
	}

}
