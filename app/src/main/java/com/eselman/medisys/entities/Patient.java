package com.eselman.medisys.entities;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Years;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * Created by eselman on 04/02/2017.
 */

public class Patient implements Serializable {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mobilePhone;
    private String identificationNumber;
    private LocalDateTime birthDate;
    private Address address;
    private IdentificationType identificationType;
    private Set<MedicalInsurance> insurances;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<MedicalInsurance> getInsurances() {
        return insurances;
    }

    public void setInsurance(Set<MedicalInsurance> insurances) {
        this.insurances = insurances;
    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }

    public void setInsurances(Set<MedicalInsurance> insurances) {
        this.insurances = insurances;
    }

    public String getBirthDateStr(){
        return simpleDateFormat.format(getBirthDate().toDate());
    }

    public String getPatientAge(){
        LocalDate birthdate = getBirthDate().toLocalDate();
        LocalDate now = new LocalDate();
        Years age = Years.yearsBetween(birthdate, now);
        return Integer.valueOf(age.getYears()).toString();
    }

    public String getFullAddress(){
        StringBuilder addressBuilder = new StringBuilder("");

        if (this.getAddress() != null) {
            if(this.getAddress().getStreet() != null && !this.getAddress().getStreet().isEmpty()){
                addressBuilder.append(this.getAddress().getStreet());
            }

            if(this.getAddress().getNumber() != null && !this.getAddress().getNumber().isEmpty()){
                addressBuilder.append(" ");
                addressBuilder.append(this.getAddress().getNumber());
            }

            if(this.getAddress().getFloor() != null && !this.getAddress().getFloor().isEmpty()){
                addressBuilder.append(" ");
                addressBuilder.append(this.getAddress().getFloor());
            }

            if(this.getAddress().getApartment() != null && !this.getAddress().getApartment().isEmpty()){
                addressBuilder.append(" ");
                addressBuilder.append(this.getAddress().getApartment());
            }
        }

        return addressBuilder.toString();
    }
}
