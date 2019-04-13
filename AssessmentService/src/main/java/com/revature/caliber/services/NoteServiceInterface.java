package com.revature.caliber.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Note;

@Service
public interface NoteServiceInterface {
	public List<Note> findAllNotes();
	public Note findNoteById(Integer id);
	public Note createNote(Note n);

}
