package petric.bogdan.onlineshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View v =  inflater.inflate(R.layout.fragment_register, container, false);
        Button t = v.findViewById(R.id.loginfragmentButton2);
        EditText usernameEditText = v.findViewById(R.id.usernameedittext);
        EditText passwordEditText = v.findViewById(R.id.passwordedittext);
        EditText emailEditText = v.findViewById(R.id.emailedittext);




        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                //String id = "1";
                try {


                    OnlineShopDbHelper dbHelper = new OnlineShopDbHelper(getContext(), "OnlineShop.db", null, 1);
                    User usr = new User(username, email, password);
                    dbHelper.insertUser(usr);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Log the error or show a Toast message to indicate the error
                }

                // Assuming login is successful, navigate to PurchaseActivity
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("USERNAME_EXTRA", username);
                startActivity(intent);

            }
        });
        return v;
    }
}