package com.inc.fileEncryption.service;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileEncryptService {
    private static final String SECRET_KEY = "D35102BFAD1E7878";
    
    public File encryptFile(MultipartFile file) throws IOException, Exception {
        byte[] data = file.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data);

        String originalFilePath = file.getOriginalFilename();
        String encryptedFilePath = Paths.get(System.getProperty("user.home"), "Downloads", "encrypted_" + originalFilePath).toString();
        
        // Create file object for the encrypted file
        File encryptedFile = new File(encryptedFilePath);
        
        // Write the encrypted bytes to the file
        try (FileOutputStream fos = new FileOutputStream(encryptedFile)) {
            fos.write(encryptedBytes);
        }
        
        return encryptedFile;
    }
}
