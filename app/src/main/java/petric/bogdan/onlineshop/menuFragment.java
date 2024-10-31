package petric.bogdan.onlineshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link menuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class menuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public menuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment menuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static menuFragment newInstance(String param1, String param2) {
        menuFragment fragment = new menuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        //String popust = null;

        String temp = getArguments().getString("popust");
        String popust;
        if(temp == null){
            popust = "nema popusta";
        }else{
            popust = temp;
        }
        //Log.d("POPUST JE", popust);


        //String ppopust = getArguments().getString("popust");
        /*
        if (getArguments() != null) {
            popust = getArguments().getString("popust");
        }

         */


        List<String> listaItema = new ArrayList<>();
        ListView lista = v.findViewById(R.id.lista);
        listaItema.add("Telefoni");
        listaItema.add("Punjaci");
        listaItema.add("Slusalice");
        listaItema.add("Maskice");
        listaItema.add("Zastitno staklo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaItema);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = listaItema.get(position);
                switch (selectedItem) {
                    case "Telefoni":
                        Intent intent1 = new Intent(getActivity(), ItemActivity.class);
                        String name1 = "Telefoni";
                        intent1.putExtra("USERNAME_EXTRA", name1);
                        intent1.putExtra("POPUST_EXTRA", popust);
                        getActivity().startActivity(intent1);
                        break;
                    case "Punjaci":
                        Intent intent2 = new Intent(getActivity(), ItemActivity.class);
                        String name2 = "Punjaci";
                        intent2.putExtra("USERNAME_EXTRA", name2);
                        intent2.putExtra("POPUST_EXTRA", popust);
                        getActivity().startActivity(intent2);
                        break;

                    case "Slusalice":
                        Intent intent3 = new Intent(getActivity(), ItemActivity.class);
                        String name3 = "Slusalice";
                        intent3.putExtra("USERNAME_EXTRA", name3);
                        intent3.putExtra("POPUST_EXTRA", popust);
                        getActivity().startActivity(intent3);

                        break;

                    case "Maskice":
                        Intent intent4 = new Intent(getActivity(), ItemActivity.class);
                        String name4 = "Maskice";
                        intent4.putExtra("USERNAME_EXTRA", name4);
                        intent4.putExtra("POPUST_EXTRA", popust);
                        getActivity().startActivity(intent4);

                        break;

                    case "Zastitno staklo":
                        Intent intent5 = new Intent(getActivity(), ItemActivity.class);
                        String name5 = "Zastitno staklo";
                        intent5.putExtra("USERNAME_EXTRA", name5);
                        intent5.putExtra("POPUST_EXTRA", popust);
                        getActivity().startActivity(intent5);

                        break;


                }
            }
        });
        return v;
    }
}