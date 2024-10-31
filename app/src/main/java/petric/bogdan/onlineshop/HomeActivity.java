package petric.bogdan.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.res.ColorStateList;
import android.graphics.Color;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String username1 = getIntent().getStringExtra("USERNAME_EXTRA");
        String popust = getIntent().getStringExtra("POPUST_EXTRA");
        TextView usernameTextView = findViewById(R.id.user);
        usernameTextView.setText(username1);

        Button bagbutton = findViewById(R.id.bagButton);
        bagbutton.setEnabled(false);

        Button menubutton = findViewById(R.id.menuButton);
        menubutton.setEnabled(true);

        Button accbutton = findViewById(R.id.accountButton);
        Button homebutton = findViewById(R.id.homeButton);

        if(popust != null) {
            if (popust.equals("popust")) {

                Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.accfragment);
                Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.menufragment);
                //bagbutton.setVisibility(View.INVISIBLE);
                //menubutton.setVisibility(View.INVISIBLE);
                //accbutton.setVisibility(View.INVISIBLE);
                //homebutton.setVisibility(View.INVISIBLE);


                if (fragment1 != null) {
                    // Remove the fragment
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    //getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                }


                int color = Color.RED;
                ColorStateList colorStateList = ColorStateList.valueOf(color);

                menubutton.setBackgroundTintList(colorStateList);

                String str1 = "primer1";
                String str2 = "primer2";
                //menubutton.setBackgroundColor(getResources().getColor(R.color.boja, null));
                if (fragment2 == null) {
                    TextView textView1 = findViewById(R.id.user);
                    textView1.setVisibility(View.INVISIBLE);
                    TextView textView2 = findViewById(R.id.welcome);
                    textView2.setVisibility(View.INVISIBLE);
                    Bundle bundle = new Bundle();
                    bundle.putString("popust", popust);
                    menuFragment mf = menuFragment.newInstance(str1, str2);
                    mf.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.menufragment, mf)
                            .commit();
                }

            }
        }



        accbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.accfragment);
                Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.menufragment);


                if (fragment2 != null) {
                    // Remove the fragment
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                    //getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                }

                String str1 = "primer1";
                String str2 = "primer2";
                TextView textView1 = findViewById(R.id.user);
                textView1.setVisibility(View.INVISIBLE);
                TextView textView2 = findViewById(R.id.welcome);
                textView2.setVisibility(View.INVISIBLE);
                AccountFragment af = AccountFragment.newInstance(str1,str2);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.accfragment, af)
                        .commit();
            }
        });




        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.accfragment);
                Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.menufragment);


                if (fragment1 != null) {
                    // Remove the fragment
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    //getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                }

                if (fragment2 != null) {
                    // Remove the fragment
                    //getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                }
            }
        });




        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.accfragment);
                Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.menufragment);


                if (fragment1 != null) {
                    // Remove the fragment
                    getSupportFragmentManager().beginTransaction().remove(fragment1).commit();
                    //getSupportFragmentManager().beginTransaction().remove(fragment2).commit();
                }


                int color = Color.RED;
                ColorStateList colorStateList = ColorStateList.valueOf(color);

                menubutton.setBackgroundTintList(colorStateList);

                String str1 = "primer1";
                String str2 = "primer2";
                //menubutton.setBackgroundColor(getResources().getColor(R.color.boja, null));
                if(fragment2 == null) {
                    TextView textView1 = findViewById(R.id.user);
                    textView1.setVisibility(View.INVISIBLE);
                    TextView textView2 = findViewById(R.id.welcome);
                    textView2.setVisibility(View.INVISIBLE);
                    menuFragment mf = menuFragment.newInstance(str1, str2);
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.menufragment, mf)
                            .commit();
                }
            }
        });







    }
}