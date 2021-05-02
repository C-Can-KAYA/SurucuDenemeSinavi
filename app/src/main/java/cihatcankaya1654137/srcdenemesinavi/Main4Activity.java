package cihatcankaya1654137.srcdenemesinavi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.se.omapi.Reader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Scanner;

public class Main4Activity extends AppCompatActivity {
public String btnrenk;
public String yzrenk;
public String arkarenk;
public String dgr;
    public List<String> rnk=null;//Sınav sorularını saklayacak olan sorular

    private static DatabaseAccess databaseAccess;//Veri tabanı bağlantısı
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();//Veri tabanını açıyor
        rnk = databaseAccess.renkal();//Sorular listesinin içine gelen senedeki sınavı sorusunu saklıyor
        btnrenk=rnk.get(1);
        arkarenk=rnk.get(2);
        yzrenk=rnk.get(3);
        dgr=rnk.get(4);
        Button b=(Button)findViewById(R.id.button);
        Button b1=(Button)findViewById(R.id.button1);
        Button b2=(Button)findViewById(R.id.button2);
        Button b3=(Button)findViewById(R.id.button3);
        Button b4=(Button)findViewById(R.id.button4);
        Button b5=(Button)findViewById(R.id.button5);
        Button b6=(Button)findViewById(R.id.button6);
        Button b7=(Button)findViewById(R.id.button7);
        Button b8=(Button)findViewById(R.id.button8);
        Button b9=(Button)findViewById(R.id.button9);
        Button b10=(Button)findViewById(R.id.button10);
        Button b11=(Button)findViewById(R.id.button11);
        Button b12=(Button)findViewById(R.id.button12);
        Button b13=(Button)findViewById(R.id.button13);
        Button b14=(Button)findViewById(R.id.button14);
        Button b15=(Button)findViewById(R.id.button15);
        Button b16=(Button)findViewById(R.id.button16);
        Button b17=(Button)findViewById(R.id.button18);
        Button b18=(Button)findViewById(R.id.button19);
        Button b19=(Button)findViewById(R.id.button20);
        Button b20=(Button)findViewById(R.id.button21);
        Button b21=(Button)findViewById(R.id.button22);
        Button b22=(Button)findViewById(R.id.button23);
        Button kyt=(Button)findViewById(R.id.kaydet);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                btnrenk="#8B0000";
            }
        });
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                btnrenk="#FF4500";
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                btnrenk="#FFFFFF";
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                btnrenk="#206040";
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                btnrenk="#a9a9a9";
            }
        });
        b5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                btnrenk="#4682B4";
            }
        });
        b6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                yzrenk="#8B0000";
            }
        });
        b7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                yzrenk="#FF4500";
            }
        });
        b8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                yzrenk="#FFFFFF";
            }
        });
        b9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                yzrenk="#206040";
            }
        });
        b10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                yzrenk="#a9a9a9";
            }
        });
        b11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                yzrenk="#4682B4";
            }
        });
        b12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                arkarenk="#8B0000";
            }
        });
        b13.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                arkarenk="#FF4500";
            }
        });
        b14.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                arkarenk="#FFFFFF";
            }
        });
        b15.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                arkarenk="#206040";
            }
        });
        b16.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                arkarenk="#a9a9a9";
            }
        });
        b17.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dgr="#8B0000";
            }
        });
        b18.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dgr="#FF4500";
            }
        });
        b19.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dgr="#FFFFFF";
            }
        });
        b20.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dgr="#206040";
            }
        });
        b21.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dgr="#a9a9a9";
            }
        });
        b22.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                dgr="#4682B4";
            }
        });
        kyt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                databaseAccess.renkver(arkarenk,btnrenk,yzrenk,dgr);
                databaseAccess.close();
                Intent intent=new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intent);

               }
        });
    }
}
