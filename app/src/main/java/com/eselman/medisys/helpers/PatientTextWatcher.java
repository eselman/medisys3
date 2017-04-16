package com.eselman.medisys.helpers;

import android.text.Editable;
import android.text.TextWatcher;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.Patient;

/**
 * Created by eselman on 15/04/2017.
 */
public class PatientTextWatcher implements TextWatcher {
    private Patient patient;
    private int fieldId;

    public PatientTextWatcher(Patient patient, int fieldId){
        this.patient = patient;
        this.fieldId = fieldId;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        switch (fieldId) {
            case R.id.patientFirstNameText:
                patient.setFirstName(editable.toString());
                break;
            case R.id.patientLastNameText:
                patient.setLastName(editable.toString());
                break;
            case R.id.patientIdNumberText:
                patient.setIdentificationNumber(editable.toString());
                break;
            case R.id.patientPhoneText:
                patient.setPhoneNumber(editable.toString());
                break;
            case R.id.patientMobilePhoneText:
                patient.setMobilePhone(editable.toString());
                break;
            case R.id.patientStreetText:
                if(patient.getAddress() != null) {
                    patient.getAddress().setStreet(editable.toString());
                }
                break;
            case R.id.patientStreetNumberText:
                if(patient.getAddress() != null) {
                    patient.getAddress().setNumber(editable.toString());
                }
                break;
            default:
                break;
        }
    }
}