package cihatcankaya1654137.srcdenemesinavi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class Main2Activity extends AppCompatActivity {
    //Veri tabanından gelecek değerleri başta null olarak tanımlı
   // public static List<String> Sinavs = null;//Sınavs string xmlden çekim yapıyor
    public List<String> Sorular=null;//Sınav sorularını saklayacak olan sorular
    public static List<String> C1s = null;//A şıkkı değeri
    public static List<String> C2s = null;//B şıkkı değeri
    public static List<String> C3s = null;//C şıkkı değeri
    public static List<String> C4s = null;//D şıkkı değeri
    public static List<String> DCs = null;//Doğru cevap değeri
    public String [] dizi=new String[0];//burada verilen değer saklanıcak
    public List<String> list_parent;//Soruların seçildiği kısımda başlığı oluşturuyor
    public ExpandListViewAdapter expand_adapter;//Class olaraj eklenen adaptör dahil ediliyor
    public HashMap<String, List<String>> list_child;//Sorular kısmının başlığının altındaki soruları saklıyor
    public ExpandableListView expandlist_view;
    public List<String> gs_list;
    private static DatabaseAccess databaseAccess;//Veri tabanı bağlantısı
    public String yazi;
    public String btnn;
    public String ark;
    public  String dgr;
    public List<String> rnk=null;//renkleri saklayacak olan liste
    public int AnlikSoru;//Gelen sorunun kaçıncı olduğunu saklıyor
    public int Soruilk;
    public TextView text;
    public Button C1;
    public Button C2;
    public Button C3;
    public Button C4;
    public Button gc;
    public Button gri;
    public Button btr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();//Veri tabanını açıyor
        rnk = databaseAccess.renkal();//Sorular listesinin içine gelen senedeki sınavı sorusunu saklıyor
        databaseAccess.close();//Veri tabanını kapatıyor
        btnn=rnk.get(1);
        ark=rnk.get(2);
        yazi=rnk.get(3);
        dgr=rnk.get(4);
        LinearLayoutCompat view01 = (LinearLayoutCompat) findViewById(R.id.view);
        view01.setBackgroundColor(Color.parseColor(ark));
        ExpandableListView bttn = (ExpandableListView) findViewById(R.id.expand_listview);
        bttn.setBackgroundColor(Color.parseColor(ark));
        TextView item = (TextView) findViewById(R.id.txt_parent);

        Intent intent = getIntent();//diğer sayfadan veri almak için kullanılan bir parça
        String SinavAdi = intent.getStringExtra(MainActivity.SinavAdi);//Diğer sayfadaki listviewde seçilen yılı bu sayfada almaya yarıyor
        expandlist_view = (ExpandableListView)findViewById(R.id.expand_listview);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();//Veri tabanını açıyor
        SinavAdi = SinavAdi.trim();//Boşluk siliyor
        Sorular = databaseAccess.getSoru(SinavAdi);//Sorular listesinin içine gelen senedeki sınavı sorusunu saklıyor
        C1s = databaseAccess.getC1(SinavAdi);//değeri saklıyor
        C2s = databaseAccess.getC2(SinavAdi);//değeri saklıyor
        C3s = databaseAccess.getC3(SinavAdi);//değeri saklıyor
        C4s = databaseAccess.getC4(SinavAdi);// değeri saklıyor
        DCs = databaseAccess.getDC(SinavAdi);//Doğru cevabı saklıyor
        final int SoruSayisi = DCs.size();//Doğru sayısının boyutu
       // Sinavs = databaseAccess.getSinav();//değerleri okumaya yarıyor
        databaseAccess.close();//Veri tabanını kapatıyor
        dizi=new String[SoruSayisi];//Dizinin boyutu düzenleniyor
        text = (TextView) findViewById(R.id.textView);//tasarımdaki texte değer veriyor
        C1 = (Button) findViewById(R.id.Cevap1);//tasarımdaki butona değer veriyor
        C2 = (Button) findViewById(R.id.Cevap2);//tasarımdaki butona değer veriyor
        C3 = (Button) findViewById(R.id.Cevap3);//tasarımdaki butona değer veriyor
        C4 = (Button) findViewById(R.id.Cevap4);//tasarımdaki butona değer veriyor
        gc=(Button) findViewById(R.id.gec);
       gri=(Button) findViewById(R.id.geri);
       gri.setText("<<");
        btr=(Button) findViewById(R.id.bitir);
        text.setTextColor(Color.parseColor(yazi));
        C1.setBackgroundColor(Color.parseColor(btnn));
        C1.setTextColor(Color.parseColor(yazi));
        C2.setBackgroundColor(Color.parseColor(btnn));
        C2.setTextColor(Color.parseColor(yazi));
        C3.setBackgroundColor(Color.parseColor(btnn));
        C3.setTextColor(Color.parseColor(yazi));
        C4.setBackgroundColor(Color.parseColor(btnn));
        C4.setTextColor(Color.parseColor(yazi));
        gc.setBackgroundColor(Color.parseColor(dgr));
        gc.setTextColor(Color.parseColor(yazi));
        gri.setBackgroundColor(Color.parseColor(dgr));
        gri.setTextColor(Color.parseColor(yazi));
        btr.setBackgroundColor(Color.parseColor(dgr));
        btr.setTextColor(Color.parseColor(yazi));

        if (AnlikSoru == DCs.size()-1)//anlık soru değeri ile doğruların boyutunun eşitliğini kontrol ediyor
        {
            CevapSayfası();//cevap sayfası fonksiyonuna yönlendiriyor
        }
//soruları şartsız olarak bir kere ekliyoruz ki ilk değerleri görmek için
        anlik(AnlikSoru);
        btr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                CevapSayfası();
            }
            });
        gc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                AnlikSoru++;
                if (AnlikSoru == DCs.size()) {
                    CevapSayfası();
                }
                anlik(AnlikSoru);
            }
            });
        gri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                AnlikSoru--;
                if (AnlikSoru == DCs.size()) {
                    CevapSayfası();
                }
                if (AnlikSoru<=0)
                {
                    AnlikSoru=0;
                }
                anlik(AnlikSoru);
            }
        });
//c1 değerine tılandığında içindeki işlemi gerçekleştiriyor
        C1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {


                    dizi[AnlikSoru] = "" + (AnlikSoru + 1) + ".Cevabın: A ";//cevabı dizinin içine saklıyor
                AnlikSoru++;
                anlik(AnlikSoru);

                if (AnlikSoru == DCs.size()) {
                    CevapSayfası();
                }

            }
            });
        C2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dizi[AnlikSoru]=""+(AnlikSoru+1)+".Cevabın: B ";
                AnlikSoru++;
                if (AnlikSoru==DCs.size())
                {
                    CevapSayfası();
                }

                anlik(AnlikSoru);
                }
            });
        C3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dizi[AnlikSoru]=""+(AnlikSoru+1)+".Cevabın: C ";
                AnlikSoru++;
                if (AnlikSoru==DCs.size())
                {
                    CevapSayfası();
                }

                anlik(AnlikSoru);
            }
        });
        C4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                dizi[AnlikSoru]=""+(AnlikSoru+1)+".Cevabın: D ";
                AnlikSoru++;
                if (AnlikSoru==DCs.size())
                {

                    CevapSayfası();
                }

                anlik(AnlikSoru);
            }
            });
        Hazırla(); // expandablelistview içeriğini hazırlamak için
        // Adapter sınıfımızı oluşturmak için başlıklardan oluşan listimizi ve onlara bağlı olan elemanlarımızı oluşturmak için HaspMap türünü yolluyoruz
        expand_adapter = new ExpandListViewAdapter(getApplicationContext(), list_parent, list_child);

        expandlist_view.setAdapter(expand_adapter);  // oluşturduğumuz adapter sınıfını set ediyoruz

        expandlist_view.setClickable(true);
        expandlist_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

             anlik(childPosition);
                return false;
            }
        });
        }
    public void anlik(int anlikSoru)
    {
        AnlikSoru=anlikSoru;
        String Soru = Sorular.get(AnlikSoru);
        if (Soru.indexOf("[image:") != -1) {
            int firstindex = Soru.indexOf("[image:");
            int lastindex = Soru.indexOf("]");
            String image = Soru.substring(firstindex, lastindex + 1);
            String name = "draw_" + image.substring(image.indexOf(":") + 1, image.length() - 1);

            name = name.toLowerCase().replace("-", "_").replace('-', '_').replace("-", "_");
            String Uzanti = name.substring(name.lastIndexOf('.'));
            String NewName = name.substring(0, name.lastIndexOf('.'));
            NewName = NewName.replace(".", "");
            if (Uzanti.equals(".gif")) {
                NewName = NewName + "_2";
            }
            int resId = getResources().getIdentifier(NewName, "drawable", Main2Activity.this.getPackageName());
            text.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
            Soru = Soru.replace("\n", "").replace("\n", "").replace(image, "");
            text.setText(Soru);
        }else{
            text.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            text.setText(Sorular.get(AnlikSoru));
        }

        Soru = C1s.get(AnlikSoru);
        if (Soru.indexOf("[image:") != -1) {
            int firstindex = Soru.indexOf("[image:");
            int lastindex = Soru.indexOf("]");
            String image = Soru.substring(firstindex, lastindex + 1);
            String name = "draw_" + image.substring(image.indexOf(":") + 1, image.length() - 1);

            name = name.toLowerCase().replace("-", "_").replace('-', '_').replace("-", "_");
            String Uzanti = name.substring(name.lastIndexOf('.'));
            String NewName = name.substring(0, name.lastIndexOf('.'));
            NewName = NewName.replace(".", "");
            if (Uzanti.equals(".gif")) {
                NewName = NewName + "_2";
            }
            int resId = getResources().getIdentifier(NewName, "drawable", Main2Activity.this.getPackageName());
            C1.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
            Soru = Soru.replace("\n", "").replace("\n", "").replace(image, "");
            C1.setText(Soru);
        }else {
            C1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            C1.setText(C1s.get(AnlikSoru));
        }

        Soru = C2s.get(AnlikSoru);
        if (Soru.indexOf("[image:") != -1) {
            int firstindex = Soru.indexOf("[image:");
            int lastindex = Soru.indexOf("]");
            String image = Soru.substring(firstindex, lastindex + 1);
            String name = "draw_" + image.substring(image.indexOf(":") + 1, image.length() - 1);

            name = name.toLowerCase().replace("-", "_").replace('-', '_').replace("-", "_");
            String Uzanti = name.substring(name.lastIndexOf('.'));
            String NewName = name.substring(0, name.lastIndexOf('.'));
            NewName = NewName.replace(".", "");
            if (Uzanti.equals(".gif")) {
                NewName = NewName + "_2";
            }
            int resId = getResources().getIdentifier(NewName, "drawable", Main2Activity.this.getPackageName());
            C2.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
            Soru = Soru.replace("\n", "").replace("\n", "").replace(image, "");
            C2.setText(Soru);
        }else {
            C2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            C2.setText(C2s.get(AnlikSoru));
        }

        Soru = C3s.get(AnlikSoru);
        if (Soru.indexOf("[image:") != -1) {
            int firstindex = Soru.indexOf("[image:");
            int lastindex = Soru.indexOf("]");
            String image = Soru.substring(firstindex, lastindex + 1);
            String name = "draw_" + image.substring(image.indexOf(":") + 1, image.length() - 1);

            name = name.toLowerCase().replace("-", "_").replace('-', '_').replace("-", "_");
            String Uzanti = name.substring(name.lastIndexOf('.'));
            String NewName = name.substring(0, name.lastIndexOf('.'));
            NewName = NewName.replace(".", "");
            if (Uzanti.equals(".gif")) {
                NewName = NewName + "_2";
            }
            int resId = getResources().getIdentifier(NewName, "drawable", Main2Activity.this.getPackageName());
            C3.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
            Soru = Soru.replace("\n", "").replace("\n", "").replace(image, "");
            C3.setText(Soru);
        }else {
            C3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            C3.setText(C3s.get(AnlikSoru));
        }

        Soru = C4s.get(AnlikSoru);
        if (Soru.indexOf("[image:") != -1) {
            int firstindex = Soru.indexOf("[image:");
            int lastindex = Soru.indexOf("]");
            String image = Soru.substring(firstindex, lastindex + 1);
            String name = "draw_" + image.substring(image.indexOf(":") + 1, image.length() - 1);

            name = name.toLowerCase().replace("-", "_").replace('-', '_').replace("-", "_");
            String Uzanti = name.substring(name.lastIndexOf('.'));
            String NewName = name.substring(0, name.lastIndexOf('.'));
            NewName = NewName.replace(".", "");
            if (Uzanti.equals(".gif")) {
                NewName = NewName + "_2";
            }
            int resId = getResources().getIdentifier(NewName, "drawable", Main2Activity.this.getPackageName());
            C4.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
            Soru = Soru.replace("\n", "").replace("\n", "").replace(image, "");
            C4.setText(Soru);
        }else {
            C4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            C4.setText(C4s.get(AnlikSoru));
        }
    }
    public void CevapSayfası(){
        AnlikSoru=0;//sıfırlamanın amacı geriye dönüş yapıldığı zaman konumu başa almak için
        List<String> OptikForm = new ArrayList<String>(Arrays.asList(dizi));//diziyi Optik form listesine saklıyoruz
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);//main2 den main3e geçiş yapmamızı sağlıyor
        intent.putStringArrayListExtra("Dcs",(ArrayList<String>)DCs);//Doğru cevapları göndermek için yarıyor
        intent.putStringArrayListExtra("OptikForm",(ArrayList<String>)OptikForm);//optik formu göndermeye yarıyor
        intent.putStringArrayListExtra("renkler",(ArrayList<String>)rnk);
        startActivity(intent);//gönderme işlemini başlatıyor
    }
    public void Hazırla()//tasarımda alt kısımdaki sorular değerini işlem yapmamızı sağlıyan fonksiyon yarıyor
    {
        list_parent = new ArrayList<String>();//list parentin içine liste koymaya yarıyor
        list_child = new HashMap<String, List<String>>();//child yani başlığın altındaki değerler listesini saklıyor
        list_parent.add("Sorular");//Başlık ekliyor
        gs_list = new ArrayList<String>();
        for(int i = 1; i<= DCs.size() ; i++){
            dizi[i-1]= ""+i+".Cevabın: Boş ";//Bütün değerlere boş yazdırıyor
            gs_list.add("Soru " +i);//Soru boyutu kadar soru oluşturuyor
        }
        list_child.put(list_parent.get(0),gs_list);//Sorular başlığını kapatıp açma olayı
    }
}