package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Note>> findAllNotes(){
        log.debug("Inside getAllNotes");
        List<Note> temp = ns.findAllNotes();
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/note/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable("id") Integer id){
        log.debug("Inside findNoteById");
        Note temp = ns.findNoteById(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
}