package me.romo.guestnote.websocket;

import lombok.RequiredArgsConstructor;
import me.romo.guestnote.GuestNoteApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(GuestNoteApplication.getWebSocketNewNoteHandler(), "/note/new").setAllowedOrigins("*");
    }
}
