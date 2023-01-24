package org.mbds.nfctag;

import androidx.appcompat.app.AppCompatActivity;
 

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class postapi extends AppCompatActivity {
    public EditText matiere, justife , matricule;
    private Button postDataBtn;
    private ProgressBar loadingPB;
    private TextView responseTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postapi);
        responseTV = findViewById(R.id.idTVResponse);
        matiere = findViewById(R.id.matiere);
        justife = findViewById(R.id.justife);
        matricule = findViewById(R.id.matricule);
        postDataBtn=findViewById(R.id.idBtnPost);
        loadingPB = findViewById(R.id.idLoadingPB);
        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                //if (matiere.getText().toString().isEmpty() && justife.getText().toString().isEmpty() && matricule.getText().toString().isEmpty()) {
                //   Toast.makeText(postapi.this, "Remplire toute les champs", Toast.LENGTH_SHORT).show();
                // return;
                //}

                // calling a method to post the data and passing our name and job.
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                 if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    //your codes here
                    try
                    {
                        URL url = new URL("https://httpbin.org/ip");
                        URLConnection connection = url.openConnection();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(
                                        connection.getInputStream()));
                        String line = null;
                        StringBuilder content = new StringBuilder();
                        while((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        System.out.println(content);
                    }
                    catch (IOException i )
                    {

                    }
                }



            }
        });}
  }


