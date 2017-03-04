package com.eselman.medisys;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.eselman.medisys.adapters.CountiesAdapter;
import com.eselman.medisys.adapters.DepartmentsAdapter;
import com.eselman.medisys.adapters.IdTypesAdapter;
import com.eselman.medisys.adapters.TownsAdapter;
import com.eselman.medisys.clients.DepartmentsClientTask;
import com.eselman.medisys.clients.IdTypesClientTask;
import com.eselman.medisys.clients.CountiesClientTask;
import com.eselman.medisys.clients.TownsClientTask;
import com.eselman.medisys.entities.Address;
import com.eselman.medisys.entities.County;
import com.eselman.medisys.entities.Department;
import com.eselman.medisys.entities.IdentificationType;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.entities.Town;
import com.eselman.medisys.helpers.Constants;

import org.joda.time.LocalDateTime;

import java.util.Calendar;
import java.util.List;

public class EditPatientFragment extends Fragment implements IdTypesClientTask.Callback, TownsClientTask.Callback,
        DepartmentsClientTask.Callback, CountiesClientTask.Callback {
    private EditText patientFirstNameText;
    private EditText patientLastNameText;
    private Spinner idTypesSpinner;
    private EditText patientIdNumberText;
    private EditText patientBirthdayText;
    private EditText patientPhoneText;
    private EditText patientMobilePhoneText;
    private EditText patientStreetText;
    private EditText patientStreetNumberText;
    private EditText patientFloorText;
    private EditText patientApartmentText;
    private Spinner townsSpinner;
    private Spinner departmentsSpinner;
    private Spinner countiesSpinner;

    private List<IdentificationType> identificationTypeList;
    private List<County> countiesList;
    private List<Department> departmentsList;
    private List<Town> townsList;
    private Patient patient;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_patient, container, false);
        patientFirstNameText = (EditText)rootView.findViewById(R.id.patientFirstNameText);
        patientLastNameText = (EditText)rootView.findViewById(R.id.patientLastNameText);
        idTypesSpinner = (Spinner)rootView.findViewById(R.id.patientIdTypeSpinner);
        patientIdNumberText = (EditText)rootView.findViewById(R.id.patientIdNumberText);
        patientBirthdayText = (EditText) rootView.findViewById(R.id.patientBirthdayText);
        patientPhoneText = (EditText) rootView.findViewById(R.id.patientPhoneText);
        patientMobilePhoneText = (EditText) rootView.findViewById(R.id.patientMobilePhoneText);
        patientStreetText = (EditText) rootView.findViewById(R.id.patientStreetText);
        patientStreetNumberText = (EditText) rootView.findViewById(R.id.patientStreetNumberText);
        patientFloorText = (EditText) rootView.findViewById(R.id.patientFloorText);
        patientApartmentText = (EditText) rootView.findViewById(R.id.patientApartmentText);
        townsSpinner = (Spinner) rootView.findViewById(R.id.patientTownSpinner);
        departmentsSpinner = (Spinner) rootView.findViewById(R.id.patientDepartmentSpinner);
        countiesSpinner = (Spinner) rootView.findViewById(R.id.patientProvinceSpinner);

        patient = (Patient)getActivity().getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        if(patient != null && patient.getId() != null){
            loadPatientInfo();
        }

        patientBirthdayText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar birthdayDate = Calendar.getInstance();

                if(patient != null && patient.getBirthDate() != null) {
                    birthdayDate.setTime(patient.getBirthDate().toDate());
                }

                int year = birthdayDate.get(Calendar.YEAR);
                int month = birthdayDate.get(Calendar.MONTH);
                int day = birthdayDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog birthdayDatePicker = new DatePickerDialog(getActivity(), R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        birthdayDate.set(selectedYear, selectedMonth, selectedDay);
                        LocalDateTime patientBirthDate = new LocalDateTime(birthdayDate.getTime());
                        patient.setBirthDate(patientBirthDate);
                        patientBirthdayText.setText(patient.getBirthDateStr());
                    }
                },year, month, day);
                 birthdayDatePicker.show();
            }
        });

        // Async Task to fill identification types spinner.
        final IdTypesClientTask idTypesClient = new IdTypesClientTask(this, getActivity());
        idTypesClient.execute();

        // Async Task to fill provinces spinner.
        final CountiesClientTask countiesClient = new CountiesClientTask(this, getActivity(), Constants.DEFAULT_COUNTRY);
        countiesClient.execute();

        return rootView;
    }

    @Override
    public void getIdentificationTypesCallback(Object idTypes) {
        identificationTypeList = (List<IdentificationType>)idTypes;
        IdTypesAdapter idTypesAdapter = new IdTypesAdapter(getActivity(), identificationTypeList);
        idTypesSpinner.setAdapter(idTypesAdapter);

        if(patient != null && identificationTypeList != null && !identificationTypeList.isEmpty()){
            for (int i=0; i < identificationTypeList.size(); i++) {
                IdentificationType idType = identificationTypeList.get(i);
                if(idType.getCode().equals(patient.getIdentificationType().getCode())){
                    idTypesSpinner.setSelection(i);
                    break;
                }
            }
        }
    }

    @Override
    public void getCountiesCallback(Object counties) {
        countiesList = (List<County>)counties;
        CountiesAdapter countiesAdapter = new CountiesAdapter(getActivity(), countiesList);
        countiesSpinner.setAdapter(countiesAdapter);

        if(patient != null && patient.getAddress()!=null && countiesList != null && !countiesList.isEmpty()){
            for (int i=0; i < countiesList.size(); i++) {
                County county = countiesList.get(i);
                if(county.getCode().equals(patient.getAddress().getCounty().getCode())){
                    countiesSpinner.setSelection(i);
                    getDepartments(county.getCode());
                    break;
                }
            }
        }
    }

    @Override
    public void getDepartmentsCallback(Object departments) {
        departmentsList = (List<Department>)departments;
        DepartmentsAdapter departmentsAdapter = new DepartmentsAdapter(getActivity(), departmentsList);
        departmentsSpinner.setAdapter(departmentsAdapter);

        if(patient != null && patient.getAddress()!=null && departmentsList != null && !departmentsList.isEmpty()){
            for (int i=0; i < departmentsList.size(); i++) {
                Department department = departmentsList.get(i);
                if(department.getCode().equals(patient.getAddress().getDepartment().getCode())){
                    departmentsSpinner.setSelection(i);
                    getTowns(department.getCode());
                    break;
                }
            }
        }
    }

    @Override
    public void getTownsCallback(Object towns) {
        townsList = (List<Town>)towns;
        TownsAdapter townsAdapter = new TownsAdapter(getActivity(), townsList);
        townsSpinner.setAdapter(townsAdapter);

        if(patient != null && patient.getAddress()!=null && townsList != null && !townsList.isEmpty()){
            for (int i=0; i < townsList.size(); i++) {
                Town town = townsList.get(i);
                if(town.getCode().equals(patient.getAddress().getTown().getCode())){
                    townsSpinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void loadPatientInfo(){
        patientFirstNameText.setText(patient.getFirstName());
        patientLastNameText.setText(patient.getLastName());
        patientIdNumberText.setText(patient.getIdentificationNumber());
        patientBirthdayText.setText(patient.getBirthDateStr());
        patientPhoneText.setText(patient.getPhoneNumber());
        patientMobilePhoneText.setText(patient.getMobilePhone());

        if(patient.getAddress() != null){
            Address address = patient.getAddress();
            patientStreetText.setText(address.getStreet());
            patientStreetNumberText.setText(address.getNumber());
            patientFloorText.setText(address.getFloor());
            patientApartmentText.setText(address.getApartment());
        }
    }

    private void getDepartments(String countyCode) {
        // Async Task to fill departments spinner.
        final DepartmentsClientTask departmentsClientTask = new DepartmentsClientTask(this, getActivity(), countyCode);
        departmentsClientTask.execute();
    }

    private void getTowns(String departmentCode) {
        // Async Task to fill towns spinner.
        final TownsClientTask townsClient = new TownsClientTask(this, getActivity(), departmentCode);
        townsClient.execute();
    }
}
