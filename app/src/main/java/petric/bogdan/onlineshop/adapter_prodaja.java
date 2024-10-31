package petric.bogdan.onlineshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter_prodaja extends BaseAdapter {

    private Context context;
    private ArrayList<ProdaniArtikli> nizPodataka;

    public adapter_prodaja(Context context) {
        this.context = context;
        nizPodataka = new ArrayList<ProdaniArtikli>();
    }




    @Override
    public int getCount() {
        return nizPodataka.size();
        //return 0;
    }


    @Override
    public Object getItem(int i) {
        ProdaniArtikli podatak = null;
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
            view = inflater.inflate(R.layout.prodani_artikli, null);

            viewHolder = new ViewHolder();
            viewHolder.naziv = view.findViewById(R.id.status);
            viewHolder.cena = view.findViewById(R.id.price);
            viewHolder.datum = view.findViewById(R.id.date);

            view.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) view.getTag();
        }


        ProdaniArtikli podatak = (ProdaniArtikli) getItem(i);
        viewHolder.naziv.setText(podatak.getNaziv());
        viewHolder.cena.setText(podatak.getCena());
        viewHolder.datum.setText(podatak.getDatum());

        return view;
    }


    private class ViewHolder {

        TextView cena;
        TextView naziv;
        TextView datum;
    }


    public void add(ProdaniArtikli podatak) {
        nizPodataka.add(podatak);
        notifyDataSetChanged();
    }


    public void removeByIndex(int i) {
        nizPodataka.remove(i);
        notifyDataSetChanged();
    }

    public void removeByValue(ProdaniArtikli m) {
        nizPodataka.remove(m);
        notifyDataSetChanged();
    }

    public String getNaziv(ProdaniArtikli m) {
        return m.getNaziv();
    }

    public void obrisi(){
        nizPodataka.clear();
    }

    public String getNameFromPos(int position) {
        return nizPodataka.get(position).getNaziv();
    }


}
