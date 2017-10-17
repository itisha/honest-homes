package com.exadel.ethereum.model;

public class Sha256Hex {

    private String feedbackId;
    private String sha256Hex;

    public Sha256Hex(String feedbackId, String sha256Hex) {
        this.feedbackId = feedbackId;
        this.sha256Hex = sha256Hex;
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
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
                "feedbackId='" + feedbackId + '\'' +
                ", sha256Hex='" + sha256Hex + '\'' +
                '}';
    }
}
