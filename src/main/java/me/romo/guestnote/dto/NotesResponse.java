package me.romo.guestnote.dto;

import lombok.Getter;
import me.romo.guestnote.note.Note;

@Getter
public class NotesResponse {

    private final String name;
    private final String content;

    public NotesResponse(Note note){
        this.name = note.getName();
        this.content = note.getContent();
    }
}
