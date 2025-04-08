package com.healthcare.records;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.healthcare.records.database.AppDatabase;
import com.healthcare.records.database.entity.PatientRecord;
import com.healthcare.records.database.entity.User;
import com.healthcare.records.util.AadharValidator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Activity for hospital staff to add a new patient record.
 */
public class AddRecordActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private static final int REQUEST_STORAGE_PERMISSION = 101;

    private EditText etPatientAadhar, etDiagnosis, etPrescription, etNotes, etDoctorContact;
    private EditText etDoctorName, etDoctorAvailability;
    private Button btnSave, btnTakePhoto, btnChooseImage, btnRotateLeft, btnRotateRight;
    private ProgressBar progressBar, pbSeverity;
    private TextView tvHospitalInfo, tvSeverityValue;
    private SeekBar sbSeverity;
    private ImageView ivRecordImage;
    private LinearLayout layoutImageRotation;
    private AppDatabase database;
    private String hospitalId;
    private String hospitalName;
    private String currentPhotoPath;
    private boolean hasImage = false;
    private int imageRotation = 0;
    private int severityScore = 1;
    private Bitmap currentImageBitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        // Set up action bar
        setTitle("Add Patient Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get hospital information from intent
        hospitalId = getIntent().getStringExtra("HOSPITAL_ID");
        hospitalName = getIntent().getStringExtra("HOSPITAL_NAME");

        // Initialize the database
        database = AppDatabase.getInstance(getApplicationContext());

        // Initialize UI components
        etPatientAadhar = findViewById(R.id.etPatientAadhar);
        etDiagnosis = findViewById(R.id.etDiagnosis);
        etPrescription = findViewById(R.id.etPrescription);
        etNotes = findViewById(R.id.etNotes);
        etDoctorContact = findViewById(R.id.etDoctorContact);
        etDoctorName = findViewById(R.id.etDoctorName);
        etDoctorAvailability = findViewById(R.id.etDoctorAvailability);
        
        btnSave = findViewById(R.id.btnSave);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        btnChooseImage = findViewById(R.id.btnChooseImage);
        btnRotateLeft = findViewById(R.id.btnRotateLeft);
        btnRotateRight = findViewById(R.id.btnRotateRight);
        
        sbSeverity = findViewById(R.id.sbSeverity);
        tvSeverityValue = findViewById(R.id.tvSeverityValue);
        pbSeverity = findViewById(R.id.pbSeverity);
        
        progressBar = findViewById(R.id.progressBar);
        tvHospitalInfo = findViewById(R.id.tvHospitalInfo);
        ivRecordImage = findViewById(R.id.ivRecordImage);
        layoutImageRotation = findViewById(R.id.layoutImageRotation);

        // Set hospital info
        tvHospitalInfo.setText("Hospital: " + hospitalName);

        // Set up severity slider
        sbSeverity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Progress is 0-9, but we want severity 1-10
                severityScore = progress + 1;
                tvSeverityValue.setText(String.valueOf(severityScore));
                
                // Update progress bar
                pbSeverity.setProgress(severityScore);
                
                // Change the color based on severity
                if (severityScore <= 3) {
                    pbSeverity.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50"))); // Green
                } else if (severityScore <= 7) {
                    pbSeverity.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FFC107"))); // Yellow
                } else {
                    pbSeverity.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#F44336"))); // Red
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed
            }
        });

        // Set up button click listeners
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateAndSaveRecord();
            }
        });
        
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCameraPermissionAndTakePhoto();
            }
        });
        
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStoragePermissionAndPickImage();
            }
        });
        
        btnRotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateImage(-90);
            }
        });
        
        btnRotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateImage(90);
            }
        });
    }

    /**
     * Rotate the image by the specified degrees
     */
    private void rotateImage(int degrees) {
        if (currentImageBitmap != null) {
            imageRotation = (imageRotation + degrees) % 360;
            if (imageRotation < 0) {
                imageRotation += 360;
            }
            
            Matrix matrix = new Matrix();
            matrix.postRotate(imageRotation);
            Bitmap rotatedBitmap = Bitmap.createBitmap(currentImageBitmap, 0, 0, 
                    currentImageBitmap.getWidth(), currentImageBitmap.getHeight(), matrix, true);
            
            ivRecordImage.setImageBitmap(rotatedBitmap);
        }
    }

    /**
     * Check for camera permission and take photo if granted
     */
    private void checkCameraPermissionAndTakePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            dispatchTakePictureIntent();
        }
    }

    /**
     * Check for storage permission and pick image if granted
     */
    private void checkStoragePermissionAndPickImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        } else {
            pickImageFromGallery();
        }
    }

    /**
     * Launch camera to take photo
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Error creating image file", Toast.LENGTH_SHORT).show();
            }
            
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.healthcare.records.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    /**
     * Create a file to store the image
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "RECORD_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * Launch gallery to pick an image
     */
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery();
            } else {
                Toast.makeText(this, "Storage permission is required to select images", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // Display the photo taken
                currentImageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
                if (currentImageBitmap != null) {
                    ivRecordImage.setImageBitmap(currentImageBitmap);
                    ivRecordImage.setVisibility(View.VISIBLE);
                    layoutImageRotation.setVisibility(View.VISIBLE);
                    hasImage = true;
                    imageRotation = 0; // Reset rotation for new image
                }
            } else if (requestCode == REQUEST_PICK_IMAGE && data != null) {
                try {
                    // Create a file to store the selected image
                    File photoFile = createImageFile();
                    Uri selectedImageUri = data.getData();
                    
                    // Copy the selected image to our app's storage
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    FileOutputStream fileOutputStream = new FileOutputStream(photoFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                    
                    // Display the selected image
                    currentImageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
                    if (currentImageBitmap != null) {
                        ivRecordImage.setImageBitmap(currentImageBitmap);
                        ivRecordImage.setVisibility(View.VISIBLE);
                        layoutImageRotation.setVisibility(View.VISIBLE);
                        hasImage = true;
                        imageRotation = 0; // Reset rotation for new image
                    }
                } catch (Exception e) {
                    Toast.makeText(this, "Error processing selected image", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Validates input and saves the patient record if valid
     */
    private void validateAndSaveRecord() {
        String patientAadhar = etPatientAadhar.getText().toString().trim();
        String diagnosis = etDiagnosis.getText().toString().trim();
        String prescription = etPrescription.getText().toString().trim();
        String notes = etNotes.getText().toString().trim();
        String doctorContact = etDoctorContact.getText().toString().trim();
        String doctorName = etDoctorName.getText().toString().trim();
        String doctorAvailability = etDoctorAvailability.getText().toString().trim();

        // Validate input
        if (patientAadhar.isEmpty() || diagnosis.isEmpty()) {
            Toast.makeText(this, "Patient Aadhar and Diagnosis are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!AadharValidator.isValidAadhar(patientAadhar)) {
            Toast.makeText(this, "Invalid Aadhar number. It must be 12 digits, not start with 0 or 1, and contain no letters.", Toast.LENGTH_LONG).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // Check if patient exists with the given Aadhar
        User patient = database.userDao().getUserByIdentifier(patientAadhar);
        
        if (patient == null || !patient.getRole().equals("patient")) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(AddRecordActivity.this, "No patient found with this Aadhar number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get current timestamp
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        
        // Create a new patient record
        String recordId = UUID.randomUUID().toString();
        PatientRecord record = new PatientRecord(
            recordId,
            patient.getUserId(),
            patient.getName(),
            hospitalId,
            hospitalName,
            diagnosis,
            prescription,
            notes,
            currentDate,
            doctorContact,
            hasImage ? currentPhotoPath : "",
            severityScore,
            doctorName,
            doctorAvailability,
            imageRotation
        );
        
        // Insert the record into the database
        database.patientRecordDao().insert(record);
        
        progressBar.setVisibility(View.GONE);
        Toast.makeText(AddRecordActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
