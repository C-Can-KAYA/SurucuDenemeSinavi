package cihatcankaya1654137.srcdenemesinavi;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main3Activity extends AppCompatActivity {
    public  int dogru=0;
    public  int bos=0;
    public String  acekic;
    public String  bcekic;
    public  String sakla;
    public String yazi;
    public  String btnn;
    public  String ark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        String dizi[];
        ArrayList<String> renk = intent.getStringArrayListExtra("renkler");//Diğer sayfadan gelen değeri listeye atıyor
        ArrayAdapter<String> veriAdaptoru4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, renk);//değeri veri adaptorüne işliyoruz
        ArrayList<String> Dcs = intent.getStringArrayListExtra("Dcs");//Diğer sayfadan gelen değeri listeye atıyor
        ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Dcs);//değeri veri adaptorüne işliyoruz
        ArrayList<String> OptikForm = intent.getStringArrayListExtra("OptikForm");//Diğer sayfadan gelen değeri listeye atıyor
        ArrayAdapter<String> veriAdaptoru1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, OptikForm);//değeri veri adaptorüne işliyoruz
        btnn=renk.get(1);
        ark=renk.get(2);
        yazi=renk.get(3);
        LinearLayoutCompat view6 = (LinearLayoutCompat) findViewById(R.id.son);
        view6.setBackgroundColor(Color.parseColor(ark));
        Button dgrbtn=(Button)findViewById(R.id.dogrubuton);
        dgrbtn.setTextColor(Color.parseColor(yazi));
        dgrbtn.setBackgroundColor(Color.parseColor(btnn));
        Button ynlsbtn=(Button)findViewById(R.id.yanlisbuton);
        ynlsbtn.setTextColor(Color.parseColor(yazi));
        ynlsbtn.setBackgroundColor(Color.parseColor(btnn));
        Button pnbtn=(Button)findViewById(R.id.puanbtn);
        pnbtn.setTextColor(Color.parseColor(yazi));
        pnbtn.setBackgroundColor(Color.parseColor(btnn));
        ListView dnk=(ListView) findViewById(R.id.denek);
        dizi = new String[Dcs.size()];
        for (int i = 0; i <Dcs.size(); i++)
        {       String a = veriAdaptoru1.getItem(i);
            String acek[] = a.split(".Cevabın: ");
            acekic=acek[1].trim();
            String b=veriAdaptoru.getItem(i);
            b=b.trim();
            String bcek[]=b.split("Cevap:");
            bcekic=bcek[1].trim();
            if (acekic.equals(bcekic))
            {
                dogru=dogru+1;
            }
            if (acekic.equals("Boş"))
            {
                bos=bos+1;
            }
            dizi[i]=(i+1)+".Soruda senin Cevabın:"+acekic+"  Doğru Cevap:"+bcekic;
        }
        ArrayAdapter<String> veriAdaptoru3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, dizi)  {
        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            View view = super.getView(position, convertView, parent);


            TextView tv = (TextView) view.findViewById(android.R.id.text1);

            tv.setTextColor(Color.parseColor(yazi));

            return view;
        }
    };//değeri veri adaptorüne işliyoruz
        dnk.setAdapter(veriAdaptoru3);
        int yanlis=(Dcs.size())-dogru-bos;
        float puan=100/Dcs.size();
        dgrbtn.setText("Doğru sayısı:"+dogru);
        ynlsbtn.setText("Yanlis sayısı:"+yanlis);
        pnbtn.setText("Puanın:"+(puan*dogru));
    }
}
