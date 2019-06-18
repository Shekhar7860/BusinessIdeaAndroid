package com.example.mac.androidtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IdeasListActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mDatabase;
    EditText forgot_pass_email;
    ImageView back_arrow;
    Button btn_reset_password;
    Context context;

    JSONArray itemSelectedJson = new JSONArray();
    public final String android_version_names[] = {
            "Business Idea 1",
            "Business Idea 2",
            "Business Idea 3",
            "Business Idea 4",
            "Business Idea 5",
            "Business Idea 6",
            "Business Idea 7",
            "Business Idea 8",
            "Business Idea 9",
            "Business Idea 10"
    };

    private final Integer android_image_urls[] = {
            R.drawable.business1, R.drawable.business2, R.drawable.business3, R.drawable.business4,
            R.drawable.business5, R.drawable.business6, R.drawable.business7, R.drawable.business8,
            R.drawable.business9, R.drawable.business10

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        context = IdeasListActivity.this;
       FirebaseApp.initializeApp(this);
        Intent intent = this.getIntent();
        String gameId = intent.getStringExtra("value");
        Log.w("myID", gameId);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("My Path");
//
//        myRef.setValue("My Name Is Khan");
//        for(int i=1; i<= 10; i++){
//        Map mParent = new HashMap();
//        mParent.put("Idea", "Business Idea" + " " +  i);
//        myRef.push().setValue(mParent);
//        }
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Object[] obj = new Object[] {  };
//                ArrayList<Object> newObj = new ArrayList<Object>(Arrays.asList(obj));
//
//              //  Log.d("value", "User name: " + dataSnapshot.getValue());
//                try {
//
//
//                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                    Log.v("value",""+ childDataSnapshot.getKey()); //displays the key for the node
//                    Log.v("value2",""+ childDataSnapshot.child("Idea").getValue());;//gives the value for given keyname
//                    ;
//                    newObj.add( childDataSnapshot.child("Idea").getValue() );
//                    itemSelectedJson.put(
//                            new JSONObject().put("name", childDataSnapshot.child("Idea").getValue())
//                    );
//                    //newObj.add(childDataSnapshot.child("Idea").getValue() );
//
//                }
//                Log.w("object", itemSelectedJson.toString());
//                }
//                catch (Exception e) {
//                }
//                }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("error", "Failed to read value.", error.toException());
//            }
//        });
      //  connectionDetector = new ConnectionDetector(context);
      //  btn_reset_password = (Button) findViewById(R.id.btn_reset_password);
      //  forgot_pass_email = (EditText) findViewById(R.id.forgot_pass_email);
        back_arrow = (ImageView) findViewById(R.id.back_arrow);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initViews();
        // setOnclickListener();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<AndroidVersion> androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        recyclerView.setAdapter(adapter);


    }
    private ArrayList<AndroidVersion> prepareData(){

        ArrayList<AndroidVersion> android_version = new ArrayList<>();

        for(int i=0;i<android_version_names.length;i++){
            AndroidVersion androidVersion = new AndroidVersion();
            androidVersion.setAndroid_version_name(android_version_names[i]);
            androidVersion.setAndroid_image_url(android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }


    private void setOnclickListener() {
        btn_reset_password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

//        if (!validateEmail()) {
//            return;
//        } else {
//
//            if (connectionDetector.isConnectingToInternet()) {
//                Forgotpassword();
//            } else {
//                Toast.makeText(context, "No internet", Toast.LENGTH_SHORT).show();
//            }
//
//
//        }

    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }



}