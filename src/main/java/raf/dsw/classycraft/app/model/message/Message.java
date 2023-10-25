package main.java.raf.dsw.classycraft.app.model.message;

import java.time.LocalDateTime;

public class Message {
    private MessageType messageType;
    private String text;
    private LocalDateTime timestamp;

    public Message(MessageType messageType,String text) {
        this.messageType = messageType;
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
