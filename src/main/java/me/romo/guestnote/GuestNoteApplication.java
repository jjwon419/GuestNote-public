package me.romo.guestnote;

import lombok.Getter;
import me.romo.guestnote.websocket.WebSocketNewNoteHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuestNoteApplication {

    @Getter
    private static final WebSocketNewNoteHandler webSocketNewNoteHandler = new WebSocketNewNoteHandler();

    public static void main(String[] args) {
        SpringApplication.run(GuestNoteApplication.class, args);
    }

}
