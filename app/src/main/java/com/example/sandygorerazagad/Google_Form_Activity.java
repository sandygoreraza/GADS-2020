package com.example.sandygorerazagad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Google_Form_Activity extends AppCompatActivity {
    private EditText lnast;
    private EditText fname;
    private EditText email;
    private EditText project_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_form);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_googleform);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lnast = findViewById(R.id.lname_input);
        fname = findViewById(R.id.fname_input);
        email = findViewById(R.id.editTextTextEmailAddress);
        project_url = findViewById(R.id.projectgithublink);


        findViewById(R.id.button_submit).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validateInput();
                    }
                }
        );




    }


    private void validateInput() { // Validate text input

        if (lnast.getText().toString().trim().length() == 0 && project_url.getText().toString().trim().length() == 0 && fname.getText().toString().trim().length() == 0 && email.getText().toString().trim().length() == 0) {
            lnast.setError("Enter your lastname!");
            project_url.setError("Enter your GitHub project Link!");
            fname.setError("Enter your first name!");
            Toast.makeText(this, "Please fill in the required fields!", Toast.LENGTH_LONG).show();
        } else {
            validateEmail();
        }
    }

    private void validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
            sendData();
        else {
            email.setError("Enter a valid email!");
            Toast.makeText(this, "Please fill in a Valid Email Address!", Toast.LENGTH_LONG).show();
        }
    }




    private void sendData() { // Send feedback to Google Spreadsheet if text input is valid

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/") //  google form URL
                .build();
        final Webservice googleFormWebService = retrofit.create(Webservice.class);

        String lastname = lnast.getText().toString();
        String firstname = fname.getText().toString();
        String emailInput = email.getText().toString();
        String projectlink = project_url.getText().toString();

        Call<Void> feedbackCall = googleFormWebService.feedbackSend(lastname, firstname, emailInput, projectlink);
        feedbackCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //  Log.d("XXX", "Submitted. " + response);
                // Toast.makeText(Google_Form_Activity.this,"Your feedback was submitted!",Toast.LENGTH_LONG).show();


                int responseCode = response.code();
                if (responseCode == 200) {
                    Toast.makeText(Google_Form_Activity.this, "Success", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alert = new AlertDialog.Builder(Google_Form_Activity.this);

                    LayoutInflater factory = LayoutInflater.from(Google_Form_Activity.this);
                    final View view = factory.inflate(R.layout.submit_success, null);
                    alert.setView(view);
                  //  alert.setIcon(R.drawable.confirmproj);
                    alert.setMessage("Submission Successful");
                    alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Clear all fields after submitting
                            lnast.setText("");
                            fname.setText("");
                            email.setText("");
                            project_url.setText("");
                        }
                    });
                    alert.show();
                }







            }


            public void onFailure(Call<Void> call, Throwable t) {

              //  Toast.makeText(Google_Form_Activity.this,"There was an error!",Toast.LENGTH_LONG).show();
                AlertDialog.Builder alert = new AlertDialog.Builder(Google_Form_Activity.this);

                LayoutInflater factory = LayoutInflater.from(Google_Form_Activity.this);
                final View view = factory.inflate(R.layout.submit_failure, null);
                alert.setView(view);
               // alert.setIcon(R.drawable.failure);
                alert.setMessage("Submission not Successful");
                alert.setIcon(R.drawable.confirmproj);
                alert.show();



            }
        });
    }/////send data  end






}