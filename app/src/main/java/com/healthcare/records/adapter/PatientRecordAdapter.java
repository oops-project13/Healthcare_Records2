package com.healthcare.records.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.healthcare.records.R;
import com.healthcare.records.database.entity.PatientRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying patient records in a RecyclerView.
 */
public class PatientRecordAdapter extends RecyclerView.Adapter<PatientRecordAdapter.RecordViewHolder> {

    private List<PatientRecord> records;
    private final Context context;
    private final boolean isHospital;
    private final OnRecordClickListener listener;

    /**
     * Interface for handling record item clicks
     */
    public interface OnRecordClickListener {
        void onRecordClick(PatientRecord record);
    }

    /**
     * Constructor for the adapter
     * 
     * @param context The application context
     * @param isHospital True if hospital view, false if patient view
     * @param listener Listener for record item clicks
     */
    public PatientRecordAdapter(Context context, boolean isHospital, OnRecordClickListener listener) {
        this.context = context;
        this.isHospital = isHospital;
        this.listener = listener;
        this.records = new ArrayList<>();
    }

    /**
     * Updates the records list and refreshes the view
     */
    public void setRecords(List<PatientRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_patient_record, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        PatientRecord record = records.get(position);
        
        // Set the displayed text based on whether this is hospital or patient view
        if (isHospital) {
            holder.tvPrimaryInfo.setText(record.getPatientName());
            holder.tvSecondaryInfo.setText("Aadhar: " + record.getPatientId().substring(0, 8) + "...");
        } else {
            holder.tvPrimaryInfo.setText(record.getHospitalName());
            holder.tvSecondaryInfo.setText("Hospital");
        }
        
        holder.tvDiagnosis.setText(record.getDiagnosis());
        holder.tvDate.setText(record.getDate());
        
        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onRecordClick(record);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    /**
     * ViewHolder class for patient record items
     */
    static class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPrimaryInfo;
        private final TextView tvSecondaryInfo;
        private final TextView tvDiagnosis;
        private final TextView tvDate;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrimaryInfo = itemView.findViewById(R.id.tvPrimaryInfo);
            tvSecondaryInfo = itemView.findViewById(R.id.tvSecondaryInfo);
            tvDiagnosis = itemView.findViewById(R.id.tvDiagnosis);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}
