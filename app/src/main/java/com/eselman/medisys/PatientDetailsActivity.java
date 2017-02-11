package com.eselman.medisys;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eselman.medisys.adapters.PatientViewPagerAdapter;
import com.eselman.medisys.entities.Patient;
import com.eselman.medisys.helpers.Constants;

public class PatientDetailsActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private FloatingActionButton patientFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Patient patient = (Patient) getIntent().getBundleExtra(Constants.PATIENT_EXTRA).getSerializable(Constants.PATIENT_BUNDLE);
        setActionBarTitle(patient);

        ViewPager viewPager = (ViewPager) findViewById(R.id.patientsViewPager);
        setupViewPager(viewPager, patient);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.patientsTabs);
        tabs.setupWithViewPager(viewPager);

        patientFloatingButton = (FloatingActionButton) findViewById(R.id.patientFloatingBtn);
    }

    private void setActionBarTitle(Patient patient){
        if(patient.getId() != null){
            getSupportActionBar().setTitle(patient.getLastName() + ", " + patient.getFirstName());
        } else {
            getSupportActionBar().setTitle(getResources().getString(R.string.add_patient_title));
        }
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager, Patient patient) {
        PatientViewPagerAdapter adapter = new PatientViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PatientFormFragment(), getResources().getString(R.string.patient_form_label));
        adapter.addFragment(new PatientInsurancesFragment(), getResources().getString(R.string.patient_insurances_label));
        adapter.addFragment(new PatientHistoryFragment(), getResources().getString(R.string.patient_history_label));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    // Override OnPageChangeListener methods
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        switch (position) {
            case 0:
                patientFloatingButton.setVisibility(View.VISIBLE);
                break;
            case 1:
                patientFloatingButton.setVisibility(View.GONE);
                break;
            case 2:
                patientFloatingButton.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}