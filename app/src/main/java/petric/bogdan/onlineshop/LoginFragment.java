package petric.bogdan.onlineshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        Button t = v.findViewById(R.id.loginfragmentButton1);
        EditText usernameEditText = v.findViewById(R.id.fragmentloginetusr);
        EditText passwordEditText = v.findViewById(R.id.fragmentloginetpass);



        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                try {

                    OnlineShopDbHelper dbHelper = new OnlineShopDbHelper(getContext(), "OnlineShop.db", null, 1);
                    User[] korisnici = dbHelper.readUsers();

                    for (User user : korisnici) {

                        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            intent.putExtra("USERNAME_EXTRA", username);
                            startActivity(intent);

                        }

                    }
                    // Assuming login is successful, navigate to PurchaseActivity
                }catch (Exception e) {
                    e.printStackTrace();
                    // Log the error or show a Toast message to indicate the error
                }

            }
        });






        return v;
    }
}