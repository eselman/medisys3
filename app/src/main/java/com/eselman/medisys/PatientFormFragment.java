package com.eselman.medisys;

import android.os.Bundle;import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

public class PatientFormFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView;
        Patient patient = (Patient)getActivity().getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);

        if(patient != null && patient.getId() != null){
            rootView = inflater.inflate(R.layout.fragment_patient_form, container, false);
            loadPatientInfo(patient, rootView);
        } else {
            rootView = inflater.inflate(R.layout.fragment_patient, container, false);

        }

        return rootView;
    }

    private void loadPatientInfo(Patient patient, View view){
        TextView patientIdType = (TextView) view.findViewById(R.id.patientIdType);
        TextView patientIdNumber = (TextView) view.findViewById(R.id.patientIdNumber);
        TextView patientPhoneNumber = (TextView) view.findViewById(R.id.patientPhoneNumber);
        TextView patientMobilePhone = (TextView) view.findViewById(R.id.patientMobilePhone);
        TextView patientBirthDate = (TextView) view.findViewById(R.id.patientBirthDate);
        TextView patientAge = (TextView) view.findViewById(R.id.patientAge);
        TextView patientAddress= (TextView) view.findViewById(R.id.patientAddress);

        patientIdType.setText(patient.getIdentificationType().getCode());
        patientIdNumber.setText(patient.getIdentificationNumber());
        patientPhoneNumber.setText(patient.getPhoneNumber());
        patientMobilePhone.setText(patient.getMobilePhone());
        patientBirthDate.setText(patient.getBirthDateStr());
        patientAge.setText(patient.getPatientAge());
        patientAddress.setText(patient.getFullAddress());
    }
}
