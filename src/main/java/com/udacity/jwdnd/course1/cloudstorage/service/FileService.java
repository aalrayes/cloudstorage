package com.udacity.jwdnd.course1.cloudstorage.service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserService userService;
    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public int createFile(File file){
        return fileMapper.insert(file);
    }

    public boolean isFilenameAvailable(int userid,String filename){
        return fileMapper.getFileByName(userid, filename) == null;
    }

    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }

    public File getFile(Integer fileId) {
        return fileMapper.getFile(fileId);
    }

    public List<File> getAllFiles(Integer userid) {
        return fileMapper.getAllFiles(userid);
    }
}

