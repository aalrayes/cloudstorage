package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int createNote(Note note) {
        return noteMapper.insert(new Note(null, note.getNotetitle(), note.getNotedescription(), note.getUserid()));
    }

    public Boolean isNoteTitleUsed(Integer userid,String notetitle) {
        return noteMapper.isUsed(userid, notetitle) == null;
    }

    public void deleteNote(Integer noteid) {
        noteMapper.delete(noteid);
    }

    public int updateNote(Note note) {
        return noteMapper.update(note);
    }

    public Note getNote(String noteid) {
        return noteMapper.getNote(noteid);
    }

    public List<Note> getAllNotes(Integer userid) {
        return noteMapper.getAllNotes(userid);
    }
}
