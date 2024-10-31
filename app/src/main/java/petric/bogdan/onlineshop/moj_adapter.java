package petric.bogdan.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import petric.bogdan.onlineshop.Model;

import java.util.ArrayList;

public class moj_adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> nizPodataka;

    public moj_adapter(Context context) {
        this.context = context;
        nizPodataka = new ArrayList<Model>();
    }

    @Override
    public int getCount() {

        return nizPodataka.size();

    }



    @Override
    public Object getItem(int i) {
        Model podatak = null;
        try {
            podatak = nizPodataka.get(i);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return podatak;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.model, null);

            viewHolder = new ViewHolder();
            viewHolder.naziv = view.findViewById(R.id.tekst);
            viewHolder.slika = view.findViewById(R.id.slika);
            viewHolder.cena = view.findViewById(R.id.cena);
            viewHolder.dugme = view.findViewById(R.id.dodajdugme);

            view.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) view.getTag();
        }


        Model podatak = (Model) getItem(i);
        viewHolder.naziv.setText(podatak.getNaziv());
        viewHolder.cena.setText(podatak.getCena());
        viewHolder.slika.setImageDrawable(podatak.getSlika());

        viewHolder.dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, podatak.getNaziv() + " has been added", Toast.LENGTH_SHORT).show();
                OnlineShopDbHelper db = new OnlineShopDbHelper(context, "OnlineShop.db", null, 1);
                ProdaniArtikli prodaniArtikli = new ProdaniArtikli(podatak.getCena(), "WAITING FOR DELIVERY", "1.1.2024");
                db.insertSoldItem(prodaniArtikli);
                //db.close();
            }
        });

        return view;
    }

    private class ViewHolder {

        ImageView slika;
        TextView naziv;
        TextView cena;
        Button dugme;
    }


    public void add(Model podatak) {
        nizPodataka.add(podatak);
        notifyDataSetChanged();
    }


    public void removeByIndex(int i) {
        nizPodataka.remove(i);
        notifyDataSetChanged();
    }

    public void removeByValue(Model m) {
        nizPodataka.remove(m);
        notifyDataSetChanged();
    }

    public void obrisi(){
        nizPodataka.clear();
    }

    public String getNaziv(Model m) {
        return m.getNaziv();
    }

    public String getNameFromPos(int position) {
        return nizPodataka.get(position).getNaziv();
    }

}