package com.far.farmevents;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.far.farmevents.Globals.Functions;
import com.far.farmevents.Models.ModuleArea;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventLogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventLogFragment extends Fragment {


    MainActivity mainActivity;
    ModuleArea moduleArea;
    EditText etDate, etDescription;
    ImageView img;

    public EventLogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EventLogFragment newInstance(MainActivity mainActivity, ModuleArea moduleArea) {
        EventLogFragment fragment = new EventLogFragment();
        fragment.mainActivity = mainActivity;
        fragment.moduleArea = moduleArea;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_log, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgBack =  view.findViewById(R.id.imgBack);
        imgBack.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_close));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setMainMenu(moduleArea.getFarmModel());
            }
        });
        ImageView imgSave = view.findViewById(R.id.imgAction);
        imgSave.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_camera));
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    requestCameraPermission();
                }else{
                    takePicture();
                }
            }
        });

        ((TextView)view.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.event_log));

        ((TextView)view.findViewById(R.id.tvFarm)).setText(moduleArea.getFarmModel().getDescription());
        ((TextView)view.findViewById(R.id.tvDepartment)).setText(moduleArea.getDepartment().getDescription());
        ((TextView)view.findViewById(R.id.tvUnit)).setText(moduleArea.getUnitCode());

        etDate = view.findViewById(R.id.etDate);
        etDescription = view.findViewById(R.id.etDescription);
        img = view.findViewById(R.id.img);

        etDate.setText(Functions.formatDate(new Date()));

    }

    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        someActivityResultLauncher.launch(takePictureIntent);
    }

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent i = result.getData();
                        Bundle extras = i.getExtras();
                        if(extras != null){
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            img.setImageBitmap(imageBitmap);
                        }


                    }
                }
            });

    ActivityResultLauncher<Intent> cameraPermission = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent i = result.getData();
                        Bundle extras = i.getExtras();
                        if(extras != null){
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            img.setImageBitmap(imageBitmap);
                        }


                    }
                }
            });

    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(mainActivity, new String[] {Manifest.permission.CAMERA}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            } else {
                Toast.makeText(mainActivity, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
}