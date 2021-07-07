package com.udacity.jwdnd.course1.cloudstorage.service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public int createCredential(Credential credential) {
        return credentialMapper.insert(setCredentials(credential));
    }

    public int updateCredential(Credential credential){
        return credentialMapper.update(setCredentials(credential));
    }

    public Credential setCredentials(Credential credential){
        String encodedKey = encryptionService.getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return credential;
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.delete(credentialId);
    }

    public Credential getCredential(Integer credentialId) {
        return credentialMapper.findCredentials(credentialId);
    }

    public List<Credential> getAllCredentials(Integer userid) {
        return credentialMapper.getAllCredential(userid);
    }
}

