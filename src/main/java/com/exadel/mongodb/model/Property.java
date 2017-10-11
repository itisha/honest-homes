package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document(collection = "property")
public class Property {

    @Id
    private String id;
    private String landlordId;
    private String description;
    private List<String> imageUrls = new LinkedList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(String landlordId) {
        this.landlordId = landlordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public boolean addImageUrl(String imageUrl) {
        return imageUrls.add(imageUrl);
    }

    @Override
    public String toString() {
        return "Property{" +
                "id='" + id + '\'' +
                ", landlordId='" + landlordId + '\'' +
                ", description='" + description + '\'' +
                ", imageUrls=" + imageUrls +
                '}';
    }
}
