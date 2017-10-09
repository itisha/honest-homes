package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String[] ownedPropertyIds;
    private String[] visitedPropertyIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getOwnedPropertyIds() {
        return ownedPropertyIds;
    }

    public void setOwnedPropertyIds(String[] ownedPropertyIds) {
        this.ownedPropertyIds = ownedPropertyIds;
    }

    public String[] getVisitedPropertyIds() {
        return visitedPropertyIds;
    }

    public void setVisitedPropertyIds(String[] visitedPropertyIds) {
        this.visitedPropertyIds = visitedPropertyIds;
    }
}
