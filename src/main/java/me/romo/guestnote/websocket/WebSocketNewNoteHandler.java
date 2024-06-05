package me.romo.guestnote.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.romo.guestnote.dto.NotesResponse;
import me.romo.guestnote.note.Note;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketNewNoteHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("{} websocket connected", session.getId());
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("{} websocket disconnected", session.getId());
        sessions.remove(session);
    }

    public void sendNewNote(Note note) {
        NotesResponse notesResponse = new NotesResponse(note);

        ObjectMapper mapper = new ObjectMapper();

        try{
            String jsonStr = mapper.writeValueAsString(notesResponse);
            sessions.parallelStream().forEach((session -> {
                try {
                    session.sendMessage(new TextMessage(jsonStr));
                } catch (IOException e) {
                    log.error("failed to convert object to json");
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }));
        }catch (JsonProcessingException e){
            log.error("failed to convert object to json");
            log.error(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
}
