package com.exadel.mongodb.model;

import org.springframework.data.annotation.Id;

public class ContractDetails {

    @Id
    private String id;
    private String contractAddress;

    public ContractDetails(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    @Override
    public String toString() {
        return "ContractDetails{" +
                "id='" + id + '\'' +
                ", contractAddress='" + contractAddress + '\'' +
                '}';
    }
}
