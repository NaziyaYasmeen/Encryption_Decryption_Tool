package com.inc.decryptiontool.service;
import java.util.Base64;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;




@Service
public class DecryptionService {
	private static final String SECRET_KEY = "D35102BFAD1E7878"; // Change this to your secret key
	 
    private static final Logger logger = Logger.getLogger(DecryptionService.class.getName());

	  public String decrypt(String encryptedInput) {
	        try {
	            
	            if (encryptedInput == null || encryptedInput.isEmpty()) {
	                return "Error: Encrypted input is empty";
	            }
	 
	            
	            byte[] decodedBytes = Base64.getDecoder().decode(encryptedInput);
	 
	            // Use the decoded input for decryption
	            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Use the appropriate cipher transformation
	            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
	            return new String(decryptedBytes);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "Error: " + e.getMessage();
	        }
	    }
	}