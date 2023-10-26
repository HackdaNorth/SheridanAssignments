package com.example.sageLiquid2;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sageLiquid2.Objects.Invoice;
import com.example.sageLiquid2.Objects.Product;
import com.example.sageLiquid2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AddItem_Fragment extends Fragment {

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
    String result;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();

    String product_gradeText = "Returns";
    String product_costNum = "0";
    String product_priceNum = "0";
    String product_retailText = "0";

    EditText product_nameText = null;
    EditText product_quantityText = null;
    EditText product_categoriesText = null;
    EditText product_locationText = null;
    EditText product_skuText = null;
    EditText product_colorText = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_add_item_, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("products");

        Button btnAdd = view.findViewById(R.id.btnAdd);

        getParentFragmentManager().setFragmentResultListener("scannedCodeText",
                this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        // We use a String here, but any type that can be put in a Bundle is supported
                        result = bundle.getString("code");
                        TextView tv = view.findViewById(R.id.scannedCodeText);
                        //Toast.makeText(getActivity(), "2nd"+result, Toast.LENGTH_LONG).show();
                        tv.setText(result);
                        // Do something with the result
                    }
                });
        btnAdd.setOnClickListener(view -> addItem(result));
        return view;
    }
    public void addItem(String product_id) {
        addDatatoFirebase(product_id);
    }

    private void addDatatoFirebase(String product_id) {

        product = new Product();
        //Toast.makeText(getActivity(), product_id, Toast.LENGTH_SHORT).show();
        product_nameText = view.findViewById(R.id.product_name);
        product_quantityText = view.findViewById(R.id.product_quantity);
        product_categoriesText = view.findViewById(R.id.product_categories);
        product_skuText = view.findViewById(R.id.product_sku);
        product_colorText = view.findViewById(R.id.product_color);
        product_locationText = view.findViewById(R.id.product_location);


        if ( !product_nameText.getText().toString().isEmpty()
                || !product_quantityText.getText().toString().isEmpty()
                || !product_skuText.getText().toString().isEmpty()
                || !product_colorText.getText().toString().isEmpty()
                || !product_categoriesText.getText().toString().isEmpty()
                || !product_locationText.getText().toString().isEmpty()) {

            Toast.makeText(getActivity()
                    ,product_nameText.getText().toString()
                            + "" + product_quantityText.getText().toString()
                            + "" + product_skuText.getText().toString()
                            + "" + product_colorText.getText().toString()
                            + "" + product_categoriesText.getText().toString()
                            + "" + product_locationText.getText().toString(),
                    Toast.LENGTH_SHORT).show();
            // below 3 lines of code is used to set
            // data in our object class.
            product.setProduct_id(product_id);
            product.setProduct_name(product_nameText
                    .getText().toString());
            product.setProduct_quantity(Integer.parseInt(product_quantityText
                    .getText().toString()));
            product.setProduct_retail(Double.parseDouble(product_retailText));
            product.setProduct_grade(product_gradeText);
            product.setProduct_cost(Double.parseDouble(product_costNum));
            product.setProduct_price(Double.parseDouble(product_priceNum));
            product.setProduct_categories(product_categoriesText
                    .getText().toString());
            product.setProduct_arrival(dtf.format(now));
            product.setProduct_color(product_colorText
                    .getText().toString());
            product.setProduct_sku(product_skuText
                    .getText().toString());
            product.setProduct_location(product_locationText
                    .getText().toString());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // inside the method of on Data change we are setting
                    // our object class to our database reference.
                    // data base reference will sends data to firebase.
                    databaseReference.child(product_id).setValue(product);
                    // after adding this data we are showing toast message.
                    Toast.makeText(getActivity(), "Product added", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // if the data is not added or it is cancelled then
                    // we are displaying a failure toast message.
                    Toast.makeText(getActivity(), "Fail to add Product " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } else  {
            // if the data is not added or it is cancelled then
            // we are displaying a failure toast message.
            Toast.makeText(getActivity(), "Input all fields correctly",
                    Toast.LENGTH_SHORT).show();
            product_nameText.setText("");
            product_quantityText.setText("");
            product_categoriesText.setText("");
            product_skuText.setText("");
            product_colorText.setText("");
        }
    }
}