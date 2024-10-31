package petric.bogdan.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        ListView lista3 = findViewById(R.id.lista3);
        adapter_prodaja adapter = new adapter_prodaja(this);
        lista3.setAdapter(adapter);
        OnlineShopDbHelper db = new OnlineShopDbHelper(this, "OnlineShop.db", null, 1);

        ProdaniArtikli[] pa = db.readProdaneArtikle();

        if(pa != null) {
            for (ProdaniArtikli p : pa) {

                adapter.add(p);
            }
        }else{

            TextView emptyView = findViewById(R.id.emptyView);
            lista3.setEmptyView(emptyView);
        }


        /*adapter.add(new ProdaniArtikli("   1000", "   DELIVERED", "1.2.2021"));
        adapter.add(new ProdaniArtikli("   2000", "   DELIVERED", "1.1.2024"));
        adapter.add(new ProdaniArtikli("   1000", "   CANCELED", "1.2.2024"));
        adapter.add(new ProdaniArtikli("   1000", "   WAITING FOR DELIVERY", "2.2.2024"));
        adapter.add(new ProdaniArtikli("   1000", "   CANCELED", "5.2.2024"));
        adapter.add(new ProdaniArtikli("   1000", "   DELIVERED", "1.5.2024"));
        adapter.add(new ProdaniArtikli("   2000", "   DELIVERED", "1.2.2022"));
        adapter.add(new ProdaniArtikli("   1000", "   NOT DELIVERED", "1.2.2024"));
        adapter.add(new ProdaniArtikli("   5000", "   DELIVERED", "2.2.2024"));
        adapter.add(new ProdaniArtikli("   6000", "   NOT DELIVERED", "5.2.2024"));
        adapter.add(new ProdaniArtikli("   1000", "   DELIVERED", "1.4.2023"));
        adapter.add(new ProdaniArtikli("   2000", "   DELIVERED", "1.2.2024"));
        adapter.add(new ProdaniArtikli("   8000", "   NOT DELIVERED", "1.2.2024"));
        adapter.add(new ProdaniArtikli("   1000", "   DELIVERED", "2.7.2024"));
        adapter.add(new ProdaniArtikli("   9000", "   NOT DELIVERED", "5.5.2024"));

*/
        if(adapter.isEmpty()){
            TextView emptyView = findViewById(R.id.emptyView);
            lista3.setEmptyView(emptyView);
        }

    }
}