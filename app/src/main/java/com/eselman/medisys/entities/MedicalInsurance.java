package com.eselman.medisys.entities;

import java.io.Serializable;

/**
 * Created by Evangelina Selman on 29/01/2017.
 */

public class MedicalInsurance implements Serializable{
    private Long id;
    private String description;
    private String affiliateNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAffiliateNumber() {
        return affiliateNumber;
    }

    public void setAffiliateNumber(String affiliateNumber) {
        this.affiliateNumber = affiliateNumber;
    }
}
