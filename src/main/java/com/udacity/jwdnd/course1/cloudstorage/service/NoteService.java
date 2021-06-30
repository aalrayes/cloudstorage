package com.udacity.jwdnd.course1.cloudstorage.service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }
    public int createNote(Note note) {
        return noteMapper.insert(new Note(null, note.getNotetitle(), note.getNotedescription(),note.getUserid()));
    }
    public void deleteNote(Integer noteid) {
        noteMapper.delete(noteid);
    }
    public int updateNote(Note note){
        return noteMapper.update(note);
    }
    public Note getNote(String noteid) {
        return noteMapper.getNote(noteid);
    }

    public List<Note> getAllNotes(Integer userid){
        return noteMapper.getAllNotes(userid);
    }
}
