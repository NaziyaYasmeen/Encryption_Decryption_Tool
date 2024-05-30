package com.inc.decryptiontool.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inc.decryptiontool.service.DecryptionService;

@RestController
public class DecryptionController {

    @Autowired
    private DecryptionService decryptionService;

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedInput) {
        return decryptionService.decrypt(encryptedInput);
    }
}
