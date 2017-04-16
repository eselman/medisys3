package com.eselman.medisys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.eselman.medisys.adapters.PatientsLandingAdapter;
import com.eselman.medisys.clients.PatientsClientTask;
import com.eselman.medisys.entities.Address;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;
import com.eselman.medisys.helpers.DividerItemDecoration;
import com.eselman.medisys.helpers.RecyclerTouchListener;
import java.util.List;

public class PatientsLandingFragment extends Fragment implements PatientsClientTask.Callback {
    private RecyclerView patientsRecyclerView;
    private RecyclerView.Adapter patientsLandingAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Patient> patients;
    private FloatingActionButton addPatientFloatingBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patients_landing, container, false);

        // Change toolbar title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.patients_menu_title));

       // Initialize Recycler View.
        patientsRecyclerView = (RecyclerView) rootView.findViewById(R.id.patientsRecyclerView);
        patientsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        layoutManager = new LinearLayoutManager(getActivity());
        patientsRecyclerView.setLayoutManager(layoutManager);

        patientsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
              patientsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Patient patient = patients.get(position);
                Intent patientDetailsIntent = new Intent(getActivity(), PatientDetailsActivity.class);
                Bundle patientBundle = new Bundle();
                patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                patientDetailsIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                startActivity(patientDetailsIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        addPatientFloatingBtn = (FloatingActionButton) rootView.findViewById(R.id.addPatienFloatingBtn);
        addPatientFloatingBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent addPatientIntent = new Intent(getActivity(), AddPatientActivity.class);
              Bundle patientBundle = new Bundle();
              Address patientAddress = new Address();
              Patient newPatient = new Patient();
              newPatient.setAddress(patientAddress);
              patientBundle.putSerializable(Constants.PATIENT_BUNDLE, newPatient);
              addPatientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
            startActivity(addPatientIntent);
          }
        });

        // Async Taks to call patients service.
        final PatientsClientTask patientsClient = new PatientsClientTask(this, getActivity());
        patientsClient.execute();

        return rootView;
    }

    @Override
    public void getPatientsListCallback(Object patientsList) {
        patients = (List<Patient>)patientsList;
        patientsLandingAdapter = new PatientsLandingAdapter(patients);
        patientsRecyclerView.setAdapter(patientsLandingAdapter);
    }
}