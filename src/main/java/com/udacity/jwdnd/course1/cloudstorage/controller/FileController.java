package com.udacity.jwdnd.course1.cloudstorage.controller;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/home/files")
    public String createFile(@RequestParam("fileUpload") MultipartFile fileUploaded, Model model, Authentication authentication) throws IOException {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        String filename = fileUploaded.getOriginalFilename();
        System.out.println(fileService.isFilenameAvailable(userId,filename));
        if(!fileService.isFilenameAvailable(userId,filename)){
            model.addAttribute("error","You can't upload a file with the same name as previous files");
            model.addAttribute("success",false);
            System.out.println("success:"+model.getAttribute("success"));
            System.out.println("error:"+model.getAttribute("error"));
            System.out.println("file no available");
            return "result";
        }else{
            System.out.println("new one");
            String filetype = fileUploaded.getContentType();
            String filesize = Long.toString(fileUploaded.getSize());
            byte[] fileData = new byte[124000];
            try{
                InputStream fis = fileUploaded.getInputStream();
                fis.read(fileData);
                fis.close();
            }catch(Exception e){
                e.getStackTrace();
            }
            fileService.createFile(new File(null,filename,filetype,filesize,userId,fileData));
            model.addAttribute("success",true);
            return "result";
        }

        }

    @GetMapping("/home/files/view/{fileId}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Integer fileId) {
        File f = fileService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(f.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + f.getFilename() + "\"")
                .body(new ByteArrayResource(f.getFiledata()));
    }

    @GetMapping("/home/files/{fileId}")
    public String deleteFile(@PathVariable Integer fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/result";
    }
}
