package com.inc.fileDecryption.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class FileDecryptionService {
    private static final String SECRET_KEY = "D35102BFAD1E7878"; 

    public File decryptFile(MultipartFile file) throws IOException, Exception {
        byte[] encryptedData = file.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedData);

        String originalFilePath = file.getOriginalFilename();
        String decryptedFilePath = Paths.get(System.getProperty("user.home"), "Downloads", "decrypted_" + originalFilePath).toString();

        File decryptedFile = new File(decryptedFilePath);
        try (FileOutputStream fos = new FileOutputStream(decryptedFile)) {
            fos.write(decryptedBytes);
        }

        return decryptedFile;
    }
}
