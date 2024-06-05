package me.romo.guestnote.service;

import lombok.RequiredArgsConstructor;
import me.romo.guestnote.dto.AddNoteRequest;
import me.romo.guestnote.note.Note;
import me.romo.guestnote.note.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public Note save(AddNoteRequest request){
        return noteRepository.save(request.toEntity());
    }

    public List<Note> findAll(){
        return noteRepository.findAll();
    }
}
