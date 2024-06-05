package me.romo.guestnote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.romo.guestnote.note.Note;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddNoteRequest {

    private String name;
    private String content;

    public Note toEntity() {
        return Note.builder()
                .name(name)
                .content(content)
                .build();
    }
}
