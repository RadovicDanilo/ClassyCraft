package main.java.raf.dsw.classycraft.app.model.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private final SystemEvent systemEvent;
    private final MessageType messageType;
    private final String text;
    private final LocalDateTime timestamp;

    public Message(SystemEvent systemEvent, MessageType messageType, String text) {
        this.systemEvent = systemEvent;
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
    public SystemEvent getSystemEvent() {
        return systemEvent;
    }

    @Override
    public String toString() {
        String timestamp = getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        return "["+this.messageType+"][" + this.messageType + "][" + timestamp + "] " + this.text;
    }
}
