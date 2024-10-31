package petric.bogdan.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class Model {
    private String naziv;
    private String cena;
    private Drawable slika;
    private Button dugme;
    private String ime_kategorije;
    int slikaId;
    private byte[] slikaBytes; // Store image as byte array


    public Model(String naziv, Drawable slika, String cena, Button dugme) {
        this.naziv = naziv;
        this.cena = cena;
        this.slika = slika;
        this.dugme = dugme;
    }



        public Model(String naziv, int slikaId, String ime_kategorije, String cena) {
            this.naziv = naziv;
            this.cena = cena;
            this.ime_kategorije = ime_kategorije;
            this.slikaId = slikaId;
        }

    private byte[] drawableToByteArray(Drawable drawable) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // Convert byte array to Drawable
    private Drawable byteArrayToDrawable(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        return new BitmapDrawable(bitmap);
    }


    public Drawable getSlikaDrawable(Context context) {
        return ContextCompat.getDrawable(context, slikaId);
    }



    public Model() {
    }

    public String getIme_kategorije() {
        return ime_kategorije;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getCena() {
        return cena;
    }

    public Drawable getSlika() {
        return slika;
    }

    public int getSlikaId() {
        return slikaId;
    }

    public Button getDugme(){return dugme;}

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public void setSlika(Drawable slika) {
        this.slika = slika;
    }

    public void setDugme(Button dugme) {this.dugme = dugme;}


}