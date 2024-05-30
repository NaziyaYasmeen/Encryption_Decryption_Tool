package com.inc.fileDecryption.controller;



import com.inc.fileDecryption.service.FileDecryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileDecryptionController {

    @Autowired
    private FileDecryptionService fileDecryptionService;

    @PostMapping("/decryptFile")
    public ResponseEntity<String> decryptFile(@RequestParam("file") MultipartFile file) {
        try {
            fileDecryptionService.decryptFile(file);
            return ResponseEntity.ok("File has been decrypted and saved");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error decrypting file: " + e.getMessage());
        }
    }
}
