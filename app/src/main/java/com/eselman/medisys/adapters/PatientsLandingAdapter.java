package com.eselman.medisys.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eselman.medisys.R;
import com.eselman.medisys.entities.Patient;

import java.util.List;

/**
 * Created by Evangelina Selman on 28/01/2017.
 */
public class PatientsLandingAdapter extends RecyclerView.Adapter<PatientsLandingAdapter.ViewHolder> {
    private List<Patient> patients;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientNameTextView;
        TextView patientIdNumberTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            patientNameTextView = (TextView) itemView.findViewById(R.id.patientName);
            patientIdNumberTextView = (TextView) itemView.findViewById(R.id.patientIdNumber);
        }
    }

    public PatientsLandingAdapter(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public PatientsLandingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patiens_list_item, parent, false);
        return  new ViewHolder(view);
      }

    @Override
    public void onBindViewHolder(PatientsLandingAdapter.ViewHolder holder, int position) {
        Patient patient = patients.get(position);
        String patientName = patient.getLastName() + ", " + patient.getFirstName();
        holder.patientNameTextView.setText(patientName);
        holder.patientIdNumberTextView.setText(patient.getIdentificationNumber());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }
}
