package com.example.estudoandroid2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.estudoandroid2020.Drives.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.pass);
        e3=(EditText)findViewById(R.id.cpass);
        b1=(Button)findViewById(R.id.register);

        b2=(Button)findViewById(R.id.bt_login_register_screen);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this,ScreenLogin.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                if(s1.equals("")|| s2.equals("")|| s3.equals("")){
                    Toast.makeText(getApplicationContext(), "Campos vazios ",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(s2.equals(s3)){
                        Boolean checkmail = db.checkmail(s1);
                        if (checkmail){
                            Boolean insert = db.insert(s1,s2);
                            if(insert){
                                Toast.makeText(getApplicationContext(),"Registrado com Sucesso",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Email ja Registrado",Toast.LENGTH_SHORT ).show();
                        }
                    }
                        Toast.makeText(getApplicationContext(), "Senhas Diferentes ",Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }
}