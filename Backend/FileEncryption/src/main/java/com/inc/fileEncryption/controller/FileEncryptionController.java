package com.inc.fileEncryption.controller;

import com.inc.fileEncryption.service.FileEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class FileEncryptionController {

    @Autowired
    private FileEncryptService fileEncryptService;

    @PostMapping("/encryptFile")
    public ResponseEntity<String> encryptFile(@RequestParam("file") MultipartFile file) {
        try {
            fileEncryptService.encryptFile(file);
            return ResponseEntity.ok("File has been encrypted and saved");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error encrypting file: " + e.getMessage());
        }
    }
}

