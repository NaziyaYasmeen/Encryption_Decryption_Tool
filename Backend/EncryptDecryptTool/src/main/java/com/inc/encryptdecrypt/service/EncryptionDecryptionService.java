package com.inc.encryptdecrypt.service;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EncryptionDecryptionService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = Logger.getLogger(EncryptionDecryptionService.class.getName());

    private static final String ENCRYPTION_SERVICE_URL = "http://localhost:8081/encrypt";
    private static final String DECRYPTION_SERVICE_URL = "http://localhost:8082/decrypt";
    private static final String FILE_ENCRYPTION_SERVICE_URL = "http://localhost:8083/encryptFile";
    private static final String FILE_DECRYPTION_SERVICE_URL = "http://localhost:8084/decryptFile";

    public String encrypt(String input) {
        logger.log(Level.INFO, "Encrypting input: " + input);
        String response = restTemplate.postForObject(ENCRYPTION_SERVICE_URL, input, String.class);
        logger.log(Level.INFO, "Encrypted response: " + response);
        return response;
    }

    public String decrypt(String encryptedInput) {
        logger.log(Level.INFO, "Decrypting input: " + encryptedInput);
        String response = restTemplate.postForObject(DECRYPTION_SERVICE_URL, encryptedInput, String.class);
        logger.log(Level.INFO, "Decrypted response: " + response);
        return response;
    }

    public Resource encryptFile(MultipartFile file) {
        logger.log(Level.INFO, "Encrypting file: " + file.getOriginalFilename());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Resource> response = restTemplate.postForEntity(FILE_ENCRYPTION_SERVICE_URL, requestEntity, Resource.class);
        logger.log(Level.INFO, "File encrypted");

        return response.getBody();
    }

    public Resource decryptFile(MultipartFile file) {
        logger.log(Level.INFO, "Decrypting file: " + file.getOriginalFilename());

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Resource> response = restTemplate.postForEntity(FILE_DECRYPTION_SERVICE_URL, requestEntity, Resource.class);
        logger.log(Level.INFO, "File decrypted");

        return response.getBody();
    }
}
