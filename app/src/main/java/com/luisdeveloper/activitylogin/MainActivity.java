package com.luisdeveloper.activitylogin;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    EditText edit_email,edit_pass;

    HashMap<String, Persona> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);

        btn_login = findViewById(R.id.btn_login);

        createDummyListOfPeople();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = "";
                if(edit_email!=null){
                    email = edit_email.getText().toString().toLowerCase();
                }
                Persona currentPerson  = data.get(email);
                if(currentPerson != null){
                    Intent intent = new Intent(getApplicationContext(), perfiles.class);
                    intent.putExtra("PROFILE_DATA", (Parcelable) currentPerson);
                    startActivity(intent);
                }else{
                    showErrorDialog();
                    edit_email.setText("");
                    edit_pass.setText("");
                }
            }
        });

    }


    private void showErrorDialog(){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(R.string.dialog_body_message);
        dlgAlert.setTitle(R.string.dialog_title_message);
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }


    private void createDummyListOfPeople(){
        Persona persona = new Persona();
        persona.setName("Luis A. Sierra");
        persona.setEmail("luisalbertosierraalcantara@gmail.com");
        persona.setAbout("I'm a Software Developer specialized on Android Development.");
        persona.setProjects(300);
        persona.setStars(500);
        persona.setRepos(250);

        data  = new HashMap<>();
        data.put("luisalbertosierraalcantara@gmail.com", persona);
    }

}
