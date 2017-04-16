package com.eselman.medisys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.eselman.medisys.clients.SavePatientClientTask;
import com.eselman.medisys.entities.Country;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

public class EditPatientActivity extends AppCompatActivity implements SavePatientClientTask.Callback {
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        if(patient.getId() != null){
            getSupportActionBar().setTitle(patient.getLastName() + ", " + patient.getFirstName());
        }
        EditPatientFragment editPatientFragment = new EditPatientFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.patient_edit_fragment, editPatientFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_patient_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent patientIntent = new Intent(this, PatientDetailsActivity.class);
                Bundle patientBundle = new Bundle();
                patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
                patientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
                startActivity(patientIntent);
                return true;
            case R.id.action_edit_patient_save:
                Country country = new Country();
                country.setId(1L);
                patient.getAddress().setCountry(country);
                savePatient();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void savePatient(){
        SavePatientClientTask savePatientClientTask = new SavePatientClientTask(this, this, patient);
        savePatientClientTask.execute();
    }

    public void savePatientCallback(Patient patient){
        Intent patientIntent = new Intent(this, PatientDetailsActivity.class);
        Bundle patientBundle = new Bundle();
        patientBundle.putSerializable(Constants.PATIENT_BUNDLE, patient);
        patientIntent.putExtra(Constants.PATIENT_EXTRA, patientBundle);
        startActivity(patientIntent);
    }
}