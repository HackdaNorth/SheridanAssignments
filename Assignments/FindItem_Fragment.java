package com.example.sageLiquid2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sageLiquid2.Objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FindItem_Fragment extends Fragment {

//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    View view;
    Product product;

    Button btnFind;
    String result;

    TextView product_nameText;
    TextView product_quantityText;
    TextView product_categoriesText;
    TextView product_locationText;
    TextView product_skuText;
    TextView product_colorText;
    TextView product_arrialDate;
    TextView product_soldDate;
    TextView product_costText;
    TextView product_priceText;
    TextView product_gradeText;
    TextView product_retailText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_find_item_, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("products");

        btnFind = view.findViewById(R.id.btnFind);

        getParentFragmentManager().setFragmentResultListener("scannedCodeText",
                this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        // We use a String here, but any type that can be put in a Bundle is supported
                        result = bundle.getString("code");
                        TextView tv = view.findViewById(R.id.tv_code);
                        //Toast.makeText(getActivity(), "2nd"+result, Toast.LENGTH_LONG).show();
                        tv.setText(result);
                        // Do something with the result
                    }
                });
        btnFind.setOnClickListener(view -> findItem(result));

        return view;
    }
    public void findItem(String product_id) {
        findDataFromFireBase(product_id);
    }

    private void findDataFromFireBase(String product_id) {

        product = new Product();


        product_nameText = view.findViewById(R.id.tv_name);
        product_quantityText = view.findViewById(R.id.tv_quantity);
        product_categoriesText = view.findViewById(R.id.tv_category);
        product_skuText = view.findViewById(R.id.tv_sku);
        product_colorText = view.findViewById(R.id.tv_color);
        product_locationText = view.findViewById(R.id.tv_location);
        //get all from fragment view
        //set all of them after
        product_priceText = view.findViewById(R.id.tv_price);
        product_arrialDate = view.findViewById(R.id.tv_arrival);
        product_soldDate = view.findViewById(R.id.tv_sold);
        product_costText = view.findViewById(R.id.tv_cost);
        product_gradeText = view.findViewById(R.id.tv_grade);
        product_retailText = view.findViewById(R.id.tv_retail);


//        btnFind.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               product =  databaseReference.child(product_id).get().toString();
//            }
//        });

//        btnFind.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // inside the method of on Data change we are setting
//                // our object class to our database reference.
//                // data base reference will sends data to firebase.
//                databaseReference.child(product_id).setValue(product);
//                // after adding this data we are showing toast message.
//                Toast.makeText(getActivity(), "Product added", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // if the data is not added or it is cancelled then
//                // we are displaying a failure toast message.
//                Toast.makeText(getActivity(), "Fail to add Product " + error, Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}