package me.romo.guestnote.controller;

import lombok.RequiredArgsConstructor;
import me.romo.guestnote.GuestNoteApplication;
import me.romo.guestnote.dto.AddNoteRequest;
import me.romo.guestnote.dto.NotesResponse;
import me.romo.guestnote.note.Note;
import me.romo.guestnote.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoteApiController {

    private final NoteService noteService;

    @PostMapping("/note/add")
    public ResponseEntity<Note> addNote(@RequestBody AddNoteRequest request){
        Note savedNote = noteService.save(request);
        GuestNoteApplication.getWebSocketNewNoteHandler().sendNewNote(savedNote);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);

    }

    @GetMapping("/note/all")
    public ResponseEntity<List<NotesResponse>> findAllNotes(){
        List<NotesResponse> notes = noteService.findAll()
                .stream()
                .map(NotesResponse::new)
                .toList();

        return ResponseEntity.ok().body(notes);
    }
}
