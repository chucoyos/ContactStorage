package com.example.contactstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextLast, editTextPhone;
    Button SearchButton, saveButton;
    public static String data = "data";
    public static final String name = "name";
    public static final String lastName = "lastName";
    public static final String phone = "phone";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextTextName);
        editTextLast = findViewById(R.id.editTextTextLast);
        editTextPhone = findViewById(R.id.editTextPhone);
        SearchButton = findViewById(R.id.searchButton);
        saveButton = findViewById(R.id.saveButton);
    }

    public void saveData(View view){
        data = editTextName.getText().toString().toLowerCase();
        preferences = getSharedPreferences(data, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(name, editTextName.getText().toString());
        editor.putString(lastName, editTextLast.getText().toString());
        editor.putString(phone, editTextPhone.getText().toString());
        editor.apply();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    public void search(View view){
        data = editTextName.getText().toString().toLowerCase();
        preferences = getSharedPreferences(data, Context.MODE_PRIVATE);
        String str1 = editTextName.getText().toString();
        String str2 = preferences.getString(name, "");

        if (TextUtils.isEmpty(editTextName.getText())){
            editTextName.setError("Please Search by Name");
            clearFields();
        } else if (str1.equalsIgnoreCase(str2)){
            editTextName.setText(preferences.getString(name, ""));
            editTextLast.setText(preferences.getString(lastName, ""));
            editTextPhone.setText(preferences.getString(phone, ""));
        } else {
            clearFields();
            Toast.makeText(this, "Record not Found", Toast.LENGTH_SHORT).show();
        }

    }

    private void clearFields(){
        editTextName.setText("");
        editTextLast.setText("");
        editTextPhone.setText("");
    }
}