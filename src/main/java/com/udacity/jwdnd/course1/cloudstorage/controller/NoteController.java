package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/home/notes")
    public String createOrUpdateNote(Note note, Model model, Authentication authentication) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        if(note.getNoteid() == null){
            note.setUserid(userId);
            noteService.createNote(note);
        }else{
            noteService.updateNote(note);
        }
        model.addAttribute("success",true);
        return "redirect:/result";
    }

    @GetMapping("/home/notes/{noteid}")
    public String deleteNote(@PathVariable Integer noteid) {
        noteService.deleteNote(noteid);
        return "redirect:/result";
    }


}
