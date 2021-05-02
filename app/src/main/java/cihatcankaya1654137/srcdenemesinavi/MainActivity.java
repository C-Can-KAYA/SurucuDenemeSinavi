package cihatcankaya1654137.srcdenemesinavi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String SinavAdi = null;
    public static String yazi;
    public static String btnn;
    public static String ark;
    public  static String dgr;
    private static DatabaseAccess databaseAccess;//Veri tabanı bağlantısı
    public List<String> rnk=null;//renkleri saklayacak olan liste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();//Veri tabanını açıyor
        rnk = databaseAccess.renkal();//Sorular listesinin içine gelen senedeki sınavı sorusunu saklıyor
        databaseAccess.close();//Veri tabanını kapatıyor
        btnn=rnk.get(1);
        ark=rnk.get(2);
        yazi=rnk.get(3);
        dgr=rnk.get(4);

        Button mybutton=(Button)findViewById(R.id.button2);
        final Button mybutton1=(Button)findViewById(R.id.button1);
        Button mybutton3=(Button)findViewById(R.id.button3);
        Button mybutton4=(Button)findViewById(R.id.ayar);

        mybutton4.setBackgroundColor(Color.parseColor(btnn));
        mybutton4.setTextColor(Color.parseColor(yazi));
        mybutton3.setBackgroundColor(Color.parseColor(btnn));
        mybutton3.setTextColor(Color.parseColor(yazi));
        mybutton1.setBackgroundColor(Color.parseColor(btnn));
        mybutton1.setTextColor(Color.parseColor(yazi));
        mybutton.setBackgroundColor(Color.parseColor(btnn));
        mybutton.setTextColor(Color.parseColor(yazi));
        ListView view1=findViewById(R.id.listview1);
       LinearLayoutCompat view01 = (LinearLayoutCompat) findViewById(R.id.view);
        view01.setBackgroundColor(Color.parseColor(ark));
     /*   LinearLayout view2 = (LinearLayout) findViewById(R.id.view1);
        view2.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view3 = (LinearLayout) findViewById(R.id.view2);
        view3.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view4 = (LinearLayout) findViewById(R.id.view3);
        view4.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view5 = (LinearLayout) findViewById(R.id.view4);
        view5.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view6 = (LinearLayout) findViewById(R.id.view5);
        view6.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view7 = (LinearLayout) findViewById(R.id.view6);
        view7.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view8 = (LinearLayout) findViewById(R.id.view7);
        view8.setBackgroundColor(Color.parseColor(ark));
        LinearLayout view9 = (LinearLayout) findViewById(R.id.view8);
        view9.setBackgroundColor(Color.parseColor(ark));*/


        final String[] Sinavs;
        Sinavs=view1.getResources().getStringArray(R.array.Sinavs);
        ArrayAdapter<String>veriAdaptoru=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Sinavs){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){

                View view = super.getView(position, convertView, parent);


                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                                tv.setTextColor(Color.parseColor(yazi));

                               return view;
            }
        };
        view1.setAdapter(veriAdaptoru);
        view1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mybutton1.setText(Sinavs[position]);
            }
        });
        mybutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        mybutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra(SinavAdi, mybutton1.getText());
                startActivity(intent);
            }
        });
        mybutton1.setText(Sinavs[0]);
        mybutton4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
            Intent intent=new Intent(MainActivity.this,Main4Activity.class);
            startActivity(intent);
            }
        });
    }
}

