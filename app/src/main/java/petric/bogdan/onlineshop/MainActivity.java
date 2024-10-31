package petric.bogdan.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    OnlineShopDbHelper dbHelper;
    private IMyAidlInterface binder = null;
    private final String DB_NAME = "OnlineShop.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new OnlineShopDbHelper(this, DB_NAME, null, 1);

        dbHelper.fillTable();


        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, MainActivity.this, Context.BIND_AUTO_CREATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);


                String str1 = "primer1";
                String str2 = "primer2";
                LoginFragment lf = LoginFragment.newInstance(str1,str2);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.loginfragment, lf)
                        .commit();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                String str1 = "primer1";
                String str2 = "primer2";
                RegisterFragment rf = RegisterFragment.newInstance(str1,str2);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.loginfragment, rf)
                        .commit();


            }
        });
    }

    public OnlineShopDbHelper getDbHelper() {
        return dbHelper;
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        binder = IMyAidlInterface.Stub.asInterface(iBinder);
        try {
            int value = binder.getValue();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        binder = null;
    }
}