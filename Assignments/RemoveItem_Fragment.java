package com.example.sageLiquid2;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sageLiquid2.Objects.Invoice;
import com.example.sageLiquid2.Objects.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RemoveItem_Fragment extends Fragment {
    // creating a variable for our
    // Firebase Database.
    FirebaseDatabase firebaseDatabase;
    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
    // creating a variable for
    // our object class
    Product product;
    View view;


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    String result;
    String invoice_date;
    String invoice_due;


    String saleTypeProvided;
    RadioGroup saleType;
    RadioButton cashSale;
    RadioButton eTransfer;
    EditText itemQuantity;
    EditText itemAmount;
    CheckBox saleTerms;
    CheckBox priceChanged;

    String product_priceNEW;
    String product_price;

    EditText tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_remove_item_, container, false);
         firebaseDatabase = FirebaseDatabase.getInstance();

         databaseReference = firebaseDatabase.getReference("products");

         cashSale = view.findViewById(R.id.cashSale);
         eTransfer = view.findViewById(R.id.eTransfer);
         Button btnRemove = view.findViewById(R.id.btnRemove);
         itemQuantity = view.findViewById(R.id.itemQuantity);
         itemAmount = view.findViewById(R.id.itemAmount);
         saleTerms = view.findViewById(R.id.saleTerm);
         priceChanged = view.findViewById(R.id.priceChanged);
         invoice_date = dtf.format(now);
         invoice_due = dtf.format(now);


        cashSale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cashSale.isChecked()) {
                    saleTypeProvided = "Cash_Sale";
                }
            }
        });
        eTransfer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (eTransfer.isChecked()) {
                    saleTypeProvided = "E_Transfer";
                }
            }
        });


        priceChanged.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(priceChanged.isChecked()) {
                    itemAmount.setVisibility(View.VISIBLE);
                } else if (!priceChanged.isChecked()) {
                    itemAmount.setVisibility(View.INVISIBLE);
                }
            }
        });

        getParentFragmentManager().setFragmentResultListener("scannedCodeText",
                this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey,
                                                 @NonNull Bundle bundle) {
                        // We use a String here, but any
                        // type that can be put in a Bundle is supported
                        result = bundle.getString("code");
                        tv = view.findViewById(R.id.scannedCodeText);
                        tv.setText(result);
                    }
                });


        btnRemove.setOnClickListener(view -> removeItem(result));
        return view;
    }

    //call move function
    public void removeItem(String product_id) {
        moveDataFromFireBase(product_id);
    }

    //code for moving an item from products to sold products
    private void moveDataFromFireBase(String product_id) {
        Invoice inv = new Invoice();
        databaseReference.child(product_id).get().addOnCompleteListener(
                new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()) {
                        DataSnapshot ds = task.getResult();
                        String product_name = String.valueOf(ds.child("product_name")
                                .getValue());
                        String oldProduct_price = String.valueOf(ds.child("product_price")
                                .getValue());
                        if(priceChanged.isChecked()) {
                            product_priceNEW = itemAmount.getText().toString();
                            Double itNum = Double.parseDouble(itemQuantity.getText().toString());
                            Double prNum = Double.parseDouble(itemAmount.getText().toString());
                            Double itemA = itNum * prNum;
                            product_price = Double.toString(itemA);
                        } else  {
                            product_price = oldProduct_price.replace("$","");
                            Double itNum = Double.parseDouble(itemQuantity.getText().toString());
                            Double itemA = itNum * Double.parseDouble(product_price);
                            product_price = Double.toString(itemA);
                        }
                        inv.setInvoice_number(product_id);
                        inv.setCustomer(saleTypeProvided);
                        inv.setTerms(saleTerms.getText().toString());
                        inv.setInvoice_date(invoice_date);
                        inv.setDue_date(invoice_due);
                        inv.setItem(product_id);
                        inv.setItem_description(product_name);
                        inv.setItem_quantity(itemQuantity.getText().toString());
                        inv.setItem_rate("1");
                        inv.setItem_tax_code("HST ON");
                        inv.setItem_amount(product_price);
                        inv.setExc_of_tax("Exclusive of Tax");
                        databaseReference.child(product_id).removeValue();
                        databaseReference = firebaseDatabase.getReference("sold_products");
                        databaseReference.child(product_id).get().addOnCompleteListener(
                                new OnCompleteListener<DataSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                            if(task.isSuccessful()) {
                                                    Toast.makeText(getActivity(),
                                                            "Read data3 ",
                                                            Toast.LENGTH_SHORT).show();
                                                    databaseReference.child(product_id)
                                                            .setValue(inv);
                                            }
                                        }
                                    });
                        }
                    }
                }
            //}
        });
    }

}