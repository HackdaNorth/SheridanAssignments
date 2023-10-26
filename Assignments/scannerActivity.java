package com.example.sageLiquid2.ui.scanner;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;

import com.example.sageLiquid2.R;

import com.google.zxing.Result;

public class scannerActivity extends Fragment {

    private CodeScanner mCodeScanner;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnEdit;
    private Button btnFind;
    private TextView viewCode;
    private CodeScannerView scannerView;
    private String scannedCode;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA}, 101);
        }
        View root = inflater.inflate(R.layout.fragment_scanner, container, false);

        //Get objects in fragment
        scannerView = root.findViewById(R.id.scanner_view);
        viewCode = root.findViewById(R.id.viewCode);

        btnAdd = root.findViewById(R.id.btnAdd);
        btnRemove = root.findViewById(R.id.btnRemove);
        btnFind = root.findViewById(R.id.btnFind);

        //Scanner Code
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                        scannedCode = result.getText();
                        viewCode.setText(scannedCode);
                    }
                });
            }
        });
        //add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (scannedCode == null) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(getActivity(), "Please scan something.", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle result = new Bundle();
                    result.putString("code", scannedCode);
                    getParentFragmentManager().setFragmentResult("scannedCodeText",result);

                    NavController nc = Navigation.findNavController(getView());
                    nc.navigate(R.id.addItem_Fragment);
                }
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the
                // edittext fields are empty or not.
                if (scannedCode == null) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(getActivity(), "Please scan something.", Toast.LENGTH_SHORT).show();
                } else {

                    Bundle result = new Bundle();
                    result.putString("code", scannedCode);
                    getParentFragmentManager().setFragmentResult("scannedCodeText",result);

                    NavController nc = Navigation.findNavController(getView());
                    nc.navigate(R.id.removeItem_Fragment);
                }
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking weather the
                // edittext fields are empty or not.
                if (scannedCode == null) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(getActivity(), "Please scan something.", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle result = new Bundle();
                    result.putString("code", scannedCode);
                    getParentFragmentManager().setFragmentResult("scannedCodeText",result);
                    NavController nc = Navigation.findNavController(getView());
                    nc.navigate(R.id.findItem_Fragment);
                }
            }
        });


        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }




}