package com.exadel.model;

/**
 * @author Tsikhan Kuprevich
 * @since 10/5/2017
 */
public class Message {

    String name;
    String text;

    public Message(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
