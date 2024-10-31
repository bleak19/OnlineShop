package petric.bogdan.onlineshop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        String name = getIntent().getStringExtra("USERNAME_EXTRA");
        String popust = getIntent().getStringExtra("POPUST_EXTRA");
                TextView tekst = findViewById(R.id.imekategorije);
        tekst.setText(name);
        Button backbutton = findViewById(R.id.backButton);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        ListView lista2 = findViewById(R.id.lista2);
        moj_adapter adapter = new moj_adapter(this);
        lista2.setAdapter(adapter);



        Button b = findViewById(R.id.dodajdugme);

        OnlineShopDbHelper db = new OnlineShopDbHelper(this, "OnlineShop.db", null, 1);
        // items = new Model[100];
        Model[] items = db.readItems();

        if (items != null && (items.length != 0)) {

            for (Model m : items) {

                int img = m.getSlikaId();
                String ime = m.getNaziv();
                String cena = m.getCena();
                Drawable slika = ContextCompat.getDrawable(this, img);
                String kategorija = m.getIme_kategorije();

                if(name == null){
                    String[] snizene_cene = cena.split(" ");
                    double snizena_cena = Double.parseDouble(snizene_cene[0]);
                    snizena_cena = 0.8 * snizena_cena;
                    String str_snizena_cena = Double.toString(snizena_cena);
                    Model temp = new Model(ime, slika, str_snizena_cena, b);
                    //adapter.add(temp);



                }else {

                    if (kategorija.equals(name)) {

                        if(popust.equals("popust")){

                            JNIexample jni = new JNIexample();

                            String[] snizene_cene = cena.split(" ");
                            //double snizena_cena = Double.parseDouble(snizene_cene[0]);
                            //snizena_cena = 0.8 * snizena_cena;
                            int snizena_cena = Integer.parseInt(snizene_cene[0]);
                            int nova_snizena_cena = jni.smanjiCenu(snizena_cena);
                            //String str_snizena_cena = Double.toString(snizena_cena);
                            //String str_snizena_cena = Integer.toString(snizena_cena);
                            String str_snizena_cena = Integer.toString(nova_snizena_cena);
                            Model temp = new Model(ime, slika, str_snizena_cena, b);
                            adapter.add(temp);

                        }else {

                            Model temp = new Model(ime, slika, cena, b);
                            adapter.add(temp);
                        }

                    }
                }

            }


        }

        else {

            Log.d("Greska", "Lista modela items je prazna!");
        }
    }
}