package com.luisdeveloper.activitylogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class perfiles extends AppCompatActivity {
    TextView textViewName, textViewAbout,textViewProjects,textViewRepos,textViewStars;
    Button btn_share;
    private final String PROFILE_DATA = "PROFILE_DATA";
    Persona currentPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiles);
        setTitle("Profile");

        textViewName = findViewById(R.id.tv_name);
        textViewAbout = findViewById(R.id.tv_desc);
        textViewProjects = findViewById(R.id.textViewProjects);
        textViewRepos = findViewById(R.id.textViewRepos);
        textViewStars = findViewById(R.id.textViewStars);
        btn_share = findViewById(R.id.btn_share);

        if(getIntent()!=null && getIntent().getExtras()!= null){
            currentPerson = getIntent().getParcelableExtra(PROFILE_DATA);
            if(currentPerson != null){
                textViewName.setText(currentPerson.getName());
                textViewAbout.setText(currentPerson.getAbout());
                textViewProjects.setText(String.valueOf(currentPerson.getProjects()));
                textViewRepos.setText(String.valueOf(currentPerson.getRepos()));
                textViewStars.setText(String.valueOf(currentPerson.getStars()));
            }
        }

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareProfileData(currentPerson);
            }
        });

    }

    private void shareProfileData(Persona persona){
        Intent sendIntent = new Intent();

        String textToSend = persona.getName() + "\n" + persona.getAbout();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, textToSend);
        sendIntent.setType("text/plain");

        startActivity(sendIntent);
    }

}
