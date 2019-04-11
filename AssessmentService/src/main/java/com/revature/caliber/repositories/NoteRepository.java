package com.revature.caliber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.caliber.beans.Note;

public interface NoteRepository extends JpaRepository<Note, Integer>{
	
}
