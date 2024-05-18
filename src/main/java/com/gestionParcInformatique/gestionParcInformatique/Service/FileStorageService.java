package com.gestionParcInformatique.gestionParcInformatique.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import java.util.Date;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    public FileStorageService() {
        this.fileStorageLocation = Paths.get("Storage")
        .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            System.err.println("Couldn't create the directory: " + this.fileStorageLocation.toString());
        }
    }

    public String storeFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileName = "";

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Filename contains invalid path sequence " + fileName);
            }

            String fileExtension = "";
            int dotIndex = originalFileName.lastIndexOf(".");
            if (dotIndex >= 0) {
                fileExtension = originalFileName.substring(dotIndex);
                fileName = originalFileName.substring(0, dotIndex);
            } else {
                fileName = originalFileName;
            }

            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String newFileName = fileName + "_" + timeStamp + fileExtension;

            Path destination = this.fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), destination);
            return newFileName;
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);

        }
    }

    public Path getFileStorageLocation(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }
}
