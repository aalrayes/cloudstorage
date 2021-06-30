package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService){
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping("/home")
    public String getHome(Authentication authentication,Note note, File file, Credential credential, Model model ){

        Integer userId = userService.getUser(authentication.getName()).getUserId();
        List<Note> notesList = noteService.getAllNotes(userId);
        model.addAttribute("notes",notesList);
//        model.addAttribute("files",(noteService.getAllNotes(userId)));
//        model.addAttribute("credentials",(noteService.getAllNotes(userId)));

        return "home";
    }

    @PostMapping("/home/new-note")
    public String newNote(Note note, Model model,Authentication authentication){
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        note.setUserid(userId);
        noteService.createNote(note);
        return "redirect:/home";
    }

}
