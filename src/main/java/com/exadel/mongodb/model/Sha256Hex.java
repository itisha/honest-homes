package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sha256hex")
public class Sha256Hex {

    @Id
    private String id;
    private String sha256Hex;

    public Sha256Hex(String sha256Hex) {
        this.sha256Hex = sha256Hex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSha256Hex() {
        return sha256Hex;
    }

    public void setSha256Hex(String sha256Hex) {
        this.sha256Hex = sha256Hex;
    }

    @Override
    public String toString() {
        return "Sha256Hex{" +
                "id='" + id + '\'' +
                ", sha256Hex='" + sha256Hex + '\'' +
                '}';
    }
}
