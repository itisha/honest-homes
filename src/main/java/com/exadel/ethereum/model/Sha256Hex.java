package com.exadel.ethereum.model;

public class Sha256Hex {

    private Long id;
    private String sha256Hex;

    public Sha256Hex(Long id, String sha256Hex) {
        this.id = id;
        this.sha256Hex = sha256Hex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
