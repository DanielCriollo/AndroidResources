package com.e.market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.e.market.clases.connectionDB;

public class signup extends AppCompatActivity {
    EditText email, password, firstname, lastname;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        Button button = (Button) findViewById(R.id.signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register(view);
            }
        });
    }


    public void Register(View view) {
        connectionDB manager = new connectionDB(this, "data", null, 1);

        SQLiteDatabase market = manager.getWritableDatabase();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String Firstname = firstname.getText().toString();
        String Lastname = lastname.getText().toString();



        if (!Email.isEmpty() && !Password.isEmpty() && !Lastname.isEmpty() && !Firstname.isEmpty()) {
            Cursor row = market.rawQuery("SELECT email" +
                    " FROM users WHERE email=" +
                    Email, null);

            if (row.moveToFirst()) {
                ContentValues DATA = new ContentValues();


                //crea
                DATA.put("email", Email);
                DATA.put("password", Password);
                DATA.put("firstname", Firstname);
                DATA.put("lastnamel", Lastname);
                //validate : not repeat mail


                market.insert("users", null, DATA);
                Toast.makeText(this, "The user has been created!!", Toast.LENGTH_SHORT).show();
                market.close();
            } else {
                Toast.makeText(this, "You must complete all fields", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*public boolean validateEmail(View view) {
        connectionDB manager = new connectionDB(this, "data", null, 1);
        SQLiteDatabase market = manager.getWritableDatabase();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String Firstname = firstname.getText().toString();
        String Lastname = lastname.getText().toString();

        Cursor row = market.rawQuery("SELECT email " +
                "FROM Users WHERE email=" +
                Email, null);

        if (row.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }*/

}
