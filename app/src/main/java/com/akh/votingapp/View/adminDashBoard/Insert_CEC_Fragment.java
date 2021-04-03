package com.akh.votingapp.View.adminDashBoard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.akh.votingapp.Model.CEC;
import com.akh.votingapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;


public class Insert_CEC_Fragment extends Fragment {
    private Spinner spinner;
    private EditText cec_name,cec_department,cec_positionInSchool,cec_email,cec_positionOfCEC,cec_description;
    private ImageView cec_img,img_button;
    private FloatingActionButton btnsave;
    private Insert_CEC_ViewModel cecViewModel;

    FirebaseUser fuser;
    private StorageTask uploadTask;
    private static final int IMAGE_REQUEST = 1;
    //uri to store file
    private Uri filePath;
    private Uri imageUri;
    //firebase objects
    private StorageReference storageReference;
    private DatabaseReference mDatabase;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cecViewModel = new ViewModelProvider(this).get(Insert_CEC_ViewModel.class);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_cec,container,false);
        spinner = (Spinner)view.findViewById(R.id.spinner_cec_position);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.cec_position, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        cec_name = view.findViewById(R.id.edt_cec_name);
        cec_department = view.findViewById(R.id.edt_department);
        cec_img = view.findViewById(R.id.img_cec);
        cec_description = view.findViewById(R.id.edt_description);
        cec_positionInSchool = view.findViewById(R.id.edt_position);
        cec_email = view.findViewById(R.id.edt_cec_email);
        btnsave = view.findViewById(R.id.btn_save_cec);

        storageReference = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference("CEC");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        cec_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                uploadFile();
                uploadImage(imageUri);
            }
        });
        return view;
    }
    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);

    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContext().getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        Log.i("ProfileFrg", "getFileExtension: " + mimeTypeMap);
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData()!=null) {

            imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .start(getActivity());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                cec_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                imageUri = result.getUri();
                uploadImage(imageUri);
            }
        } else if (uploadTask != null && uploadTask.isInProgress()) {
            Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
        } else {
        }
    }
    private void uploadImage(Uri imageUri) {
        String name = cec_name.getText().toString();
        String department = cec_department.getText().toString();
        String positionInSchool = cec_positionInSchool.getText().toString();
        String email = cec_email.getText().toString();
        String positionInCEC = spinner.getSelectedItem().toString();
        int cecType = spinner.getSelectedItemPosition();
        //TODO to get spinner index
        Toast.makeText(getContext(),cecType+"",Toast.LENGTH_SHORT).show();
        String description = cec_description.getText().toString();
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading");
        pd.show();
        //Log.i("Profile", "uploadImage: "+imageUri);
        if (imageUri != null && name!="" && department!="" && positionInSchool!="" && email!="" && description!="" ) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            uploadTask = fileReference.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = (Uri) task.getResult();
                        String mUri = downloadUri.toString();
                        CEC cec = new CEC(name,department,positionInSchool,email,positionInCEC,description, mUri, cecType);
                        cecViewModel.insertCECData(cec);
                        pd.dismiss();
                        ;
                    } else {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            });

        } else {
            Toast.makeText(getContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
}
