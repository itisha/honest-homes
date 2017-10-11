package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document(collection = "customer")
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private List<String> ownedPropertyIds = new LinkedList<>();
    private List<String> visitedPropertyIds = new LinkedList<>();

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

    public List<String> getOwnedPropertyIds() {
        return ownedPropertyIds;
    }

    public void setOwnedPropertyIds(List<String> ownedPropertyIds) {
        this.ownedPropertyIds = ownedPropertyIds;
    }

    public boolean addOwnedPropertyId(String propertyId) {
        return ownedPropertyIds.add(propertyId);
    }

    public List<String> getVisitedPropertyIds() {
        return visitedPropertyIds;
    }

    public void setVisitedPropertyIds(List<String> visitedPropertyIds) {
        this.visitedPropertyIds = visitedPropertyIds;
    }

    public boolean addVisitedPropertyId(String propertyId) {
        return visitedPropertyIds.add(propertyId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ownedPropertyIds=" + ownedPropertyIds +
                ", visitedPropertyIds=" + visitedPropertyIds +
                '}';
    }
}
