package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/home/credentials")
    public String createOrUpdateCredential(Authentication authentication, Credential credential, Model model) {
        if(credential.getCredentialid() == null){
            Integer userId = userService.getUser((authentication.getName())).getUserId();
            credential.setUserid(userId);
            credentialService.createCredential(credential);
        }else{
            credentialService.updateCredential(credential);
        }
        model.addAttribute("success",true);
        return "redirect:/result";
    }

    @GetMapping("/home/credentials/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }


}
