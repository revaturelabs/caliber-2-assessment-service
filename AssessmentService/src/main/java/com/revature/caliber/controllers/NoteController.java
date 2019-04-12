package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Note;
import com.revature.caliber.services.NoteService;

@RestController
@CrossOrigin("*")
public class NoteController {
    
    private Logger log = Logger.getLogger("NoteController.class");
    
    @Autowired
    private NoteService ns;
    
    @GetMapping("/all/note/all")
    public List<Note> findAllNotes(){
        log.debug("Inside getAllNotes");
        return ns.findAllNotes();
    }
    
    @GetMapping("/all/note/{id}")
    public Note findNoteById(@PathVariable("id") Integer id){
        log.debug("Inside findNoteById");
        return ns.findNoteById(id);
    }
    
}