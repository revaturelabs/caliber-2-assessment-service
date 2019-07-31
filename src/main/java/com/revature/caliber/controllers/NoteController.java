package com.revature.caliber.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Note;
import com.revature.caliber.dto.NoteDTO;
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
    
    @GetMapping("/all/note/trainee/{id}")
    public ResponseEntity<List<Note>> findNotesByTrainee(@PathVariable("id") Integer id){
        log.debug("Inside findNotesByTrainee");
        List<Note> temp = ns.findNotesByTraineeId(id);
        if(temp == null) return new ResponseEntity<>(temp, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
    @GetMapping("/all/note/batch/{id}")
    public ResponseEntity<List<Note>> findNotesByBatch(@PathVariable("id") Integer id, @RequestParam(name="week", required=false) Integer weekNum){
        log.debug("Inside findNotesByBatch");
        List<Note> temp = null;
        
        if(weekNum != null) temp = ns.findNotesByBatchIdAndWeekNumber(id, weekNum);
        else temp = ns.findNotesByBatchId(id);
        
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
    
    @PostMapping(value = "/all/note/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public ResponseEntity<Note> createNote(@Valid @RequestBody NoteDTO n) {
		log.debug("Saving new note:" + n);
		Note temp = ns.createNote(n);
		if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(temp, HttpStatus.CREATED);
	}
    
    @PutMapping(value="all/note/update", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Note> updateNote(@Valid @RequestBody NoteDTO note) {
      log.debug("Updating Note: " + note);
      Note temp = ns.updateNote(note);
      if(temp == null) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @DeleteMapping(value="all/note/delete", consumes=MediaType.APPLICATION_JSON_VALUE)
    @Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
    public ResponseEntity<Boolean> deleteLocation(@Valid @RequestBody NoteDTO note) {
      Boolean temp = ns.deleteNote(note);
      if(!temp) return new ResponseEntity<>(temp, HttpStatus.BAD_REQUEST);
      return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    
}