package com.inc.encryptdecrypt.controller;

import java.io.IOException;
import com.inc.encryptdecrypt.service.EncryptionDecryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;







@RestController
public class EncryptionDecryptionController {

    @Autowired
    private EncryptionDecryptionService encryptionDecryptionService;

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String input) {
        return encryptionDecryptionService.encrypt(input);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedInput) {
        return encryptionDecryptionService.decrypt(encryptedInput);
    }

    @PostMapping("/encrypt-file")
    public ResponseEntity<InputStreamResource> encryptFile(@RequestParam("file") MultipartFile file) throws IOException {
        Resource encryptedFile = encryptionDecryptionService.encryptFile(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=encrypted_" + file.getOriginalFilename());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(new InputStreamResource(encryptedFile.getInputStream()));
    }

    @PostMapping("/decrypt-file")
    public ResponseEntity<InputStreamResource> decryptFile(@RequestParam("file") MultipartFile file) throws IOException {
        Resource decryptedFile = encryptionDecryptionService.decryptFile(file);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=decrypted_" + file.getOriginalFilename());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(new InputStreamResource(decryptedFile.getInputStream()));
    }
}
