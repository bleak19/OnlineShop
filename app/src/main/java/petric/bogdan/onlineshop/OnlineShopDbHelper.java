package petric.bogdan.onlineshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class OnlineShopDbHelper extends SQLiteOpenHelper{

        public static final String USERS_TABLE = "users";
        public static final String COLUMN_USER_NAME = "username";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_ID = "id";

        public static final String ITEM_CATEGORY_TABLE = "items";
        public static final String COLUMN_NAME = "item";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_CATEGORY = "category";

        public static final String PURCHASE_HISTORY_TABLE = "purchase_history";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_PAID = "price";
        public static final String COLUMN_DATE = "date";




        public static final String CATEGORY_TABLE = "categories";
    //public static final String CATEGORY = "category";

        private Context context;

        public OnlineShopDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d("Database", "Creating database...");
            try {
                sqLiteDatabase.execSQL("CREATE TABLE " + USERS_TABLE +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_USER_NAME + " TEXT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_PASSWORD + " TEXT);");


                sqLiteDatabase.execSQL("CREATE TABLE " + ITEM_CATEGORY_TABLE +
                        " (" + COLUMN_NAME + " TEXT, " +
                        COLUMN_IMAGE + " INTEGER, " +
                        COLUMN_CATEGORY + " TEXT, " +
                        COLUMN_PRICE + " TEXT);");

                sqLiteDatabase.execSQL("CREATE TABLE " + PURCHASE_HISTORY_TABLE +
                        " (" + COLUMN_STATUS + " TEXT, " +
                        COLUMN_PAID + " TEXT, " +
                        COLUMN_DATE + " TEXT);");


                sqLiteDatabase.execSQL("CREATE TABLE " + CATEGORY_TABLE +
                        " (" + COLUMN_CATEGORY + " TEXT);");

                //fillTable(); // Pass the database object to fillTable method
                //fillCategories(sqLiteDatabase);
            } catch (SQLException e) {
                e.printStackTrace(); // Log the exception
                Log.e("Database", "Error filling table: " + e.getMessage());
                // Handle the exception or any cleanup code here
            }
        }


    @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS items");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS purchase_history");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS categories");
            onCreate(sqLiteDatabase);

        }

    private void fillCategories(SQLiteDatabase db) {
        String[] categories = {"Telefoni", "Punjaci", "Slusalice", "Maskice", "Zastitno staklo"};
        ContentValues values = new ContentValues();
        for (int i = 0; i < categories.length; i++) {
            values.put("name", categories[i]);
            db.insert("categories", null, values);
        }
    }

        public void insertUser(User user) {


            User usr = readUser(user.getEmail());

            if(usr == null) {
                SQLiteDatabase db = getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(COLUMN_USER_NAME, user.getUsername());
                values.put(COLUMN_EMAIL, user.getEmail());
                values.put(COLUMN_PASSWORD, user.getPassword());

                db.insert(USERS_TABLE, null, values);
                close();
            }
        }


    public void insertSoldItem(ProdaniArtikli soldItem) {



            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_STATUS, soldItem.getNaziv());
            values.put(COLUMN_PAID, soldItem.getCena());
            values.put(COLUMN_DATE, soldItem.getDatum());

            db.insert(PURCHASE_HISTORY_TABLE, null, values);
            close();
    }

    public ProdaniArtikli[] readProdaneArtikle(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(PURCHASE_HISTORY_TABLE, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            return null;
        }
        ProdaniArtikli[] pa = new ProdaniArtikli[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            pa[i++] = createProdaniArikl(cursor);
        }

        close();
        return pa;
    }

    private ProdaniArtikli createProdaniArikl(Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS));
        String cena = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAID));
        String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE));

        return new ProdaniArtikli(cena,name, date);
    }


    public void delete(String id) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(USERS_TABLE, COLUMN_ID + " =?", new String[] {id});
            close();
        }

        public User[] readUsers() {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(USERS_TABLE, null, null, null, null, null, null);

            if (cursor.getCount() <= 0) {
                return null;
            }
            User[] users = new User[cursor.getCount()];
            int i = 0;
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                users[i++] = createUser(cursor);
            }

            close();
            return users;
        }

        public User readUser(String email) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(USERS_TABLE, null, COLUMN_EMAIL + " =?", new String[] {email}, null, null, null);

            if (cursor.getCount() <= 0) {
                return null;
            }

            cursor.moveToFirst();

            User user = createUser(cursor);

            close();
            return user;
        }

        private User createUser(Cursor cursor) {
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            //String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));

            return new User(userName, email, password);
        }







    public void fillTable() {
        Log.d("FillTable", "Filling table...");
        SQLiteDatabase db = getWritableDatabase();

        Model[] items = {
                new Model("telefon1", R.drawable.telefon1,"Telefoni", "9000 RSD"),
                new Model("telefon2", R.drawable.telefon2,"Telefoni", "21000 RSD"),
                new Model("telefon3", R.drawable.telefon3,"Telefoni", "20000 RSD"),
                new Model("teelfon4", R.drawable.telefon4,"Telefoni", "9000 RSD"),
                new Model("telefon5", R.drawable.telefon5,"Telefoni", "9000 RSD"),
                new Model("telefon6", R.drawable.telefon6,"Telefoni", "15000 RSD"),
                new Model("telefon7", R.drawable.telefon7,"Telefoni", "9000 RSD"),
                new Model("telefon8", R.drawable.telefon8,"Telefoni", "9000 RSD"),
                new Model("telefon9", R.drawable.telefon9,"Telefoni", "7000 RSD"),
                new Model("telefon10", R.drawable.telefon10,"Telefoni", "22000 RSD"),
                new Model("telefon11", R.drawable.telefon11,"Telefoni", "9000 RSD"),
                new Model("telefon12", R.drawable.telefon12,"Telefoni", "9000 RSD"),
                new Model("telefon13", R.drawable.telefon13,"Telefoni", "11000 RSD"),
                new Model("telefon14", R.drawable.telefon14,"Telefoni", "9000 RSD"),
                new Model("telefon15", R.drawable.telefon15,"Telefoni", "10000 RSD"),
                new Model("punjac1", R.drawable.punjac1, "Punjaci", "2000 RSD"),
                new Model("punjac2", R.drawable.punjac2,"Punjaci", "1000 RSD"),
                new Model("punjac3", R.drawable.punjac3,"Punjaci", "500 RSD"),
                new Model("punjac4", R.drawable.punjac4,"Punjaci", "400 RSD"),
                new Model("punjac5", R.drawable.punjac5,"Punjaci", "3000 RSD"),
                new Model("punjac6", R.drawable.punjac6,"Punjaci", "15000 RSD"),
                new Model("punjac7", R.drawable.punjac7,"Punjaci", "1400 RSD"),
                new Model("punjac8", R.drawable.punjac8,"Punjaci", "700 RSD"),
                new Model("punjac9", R.drawable.punjac9,"Punjaci", "750 RSD"),
                new Model("punjac10", R.drawable.punjac10, "Punjaci","800 RSD"),
                new Model("punjac11", R.drawable.punjac11, "Punjaci","900 RSD"),
                new Model("punjac12", R.drawable.punjac12,"Punjaci", "980 RSD"),
                new Model("punjac13", R.drawable.punjac13,"Punjaci", "1100 RSD"),
                new Model("punjac14", R.drawable.punjac14,"Punjaci", "900 RSD"),
                new Model("punjac15", R.drawable.punjac15,"Punjaci", "1000 RSD"),
                new Model("slusalice1", R.drawable.slusalice1, "Slusalice", "9000 RSD"),
                new Model("slusalice2", R.drawable.slusalice2, "Slusalice", "21000 RSD"),
                new Model("slusalice3", R.drawable.slusalice3, "Slusalice", "20000 RSD"),
                new Model("slusalice4", R.drawable.slusalice4, "Slusalice", "9000 RSD"),
                new Model("slusalice5", R.drawable.slusalice5, "Slusalice", "9000 RSD"),
                new Model("slusalice6", R.drawable.slusalice6, "Slusalice", "15000 RSD"),
                new Model("slusalice7", R.drawable.slusalice7, "Slusalice", "9000 RSD"),
                new Model("slusalice8", R.drawable.slusalice8, "Slusalice", "9000 RSD"),
                new Model("slusalice9", R.drawable.slusalice9, "Slusalice", "7000 RSD"),
                new Model("slusalice10", R.drawable.slusalice10, "Slusalice", "22000 RSD"),
                new Model("slusalice11", R.drawable.slusalice11, "Slusalice", "9000 RSD"),
                new Model("slusalice12", R.drawable.slusalice12, "Slusalice", "9000 RSD"),
                new Model("slusalice13", R.drawable.slusalice13, "Slusalice", "11000 RSD"),
                new Model("slusalice14", R.drawable.slusalice14, "Slusalice", "9000 RSD"),
                new Model("slusalice15", R.drawable.slusalice15, "Slusalice", "10000 RSD"),
                new Model("maskica1", R.drawable.maskica1, "Maskice", "9000 RSD"),
                new Model("maska2", R.drawable.maskica2, "Maskice", "21000 RSD"),
                new Model("maska3", R.drawable.maskica3, "Maskice", "20000 RSD"),
                new Model("maska4", R.drawable.maskica4, "Maskice", "9000 RSD"),
                new Model("maska5", R.drawable.maskica5, "Maskice", "9000 RSD"),
                new Model("maskica6", R.drawable.maskica6, "Maskice", "15000 RSD"),
                new Model("maska7", R.drawable.maskica7, "Maskice", "9000 RSD"),
                new Model("maske8", R.drawable.maskica8, "Maskice", "9000 RSD"),
                new Model("maskice9", R.drawable.maskica9, "Maskice", "7000 RSD"),
                new Model("maska10", R.drawable.maskica10, "Maskice", "22000 RSD"),
                new Model("maska11", R.drawable.maskica11, "Maskice", "9000 RSD"),
                new Model("maska12", R.drawable.maskica12, "Maskice", "9000 RSD"),
                new Model("maska13", R.drawable.maskica13, "Maskice", "11000 RSD"),
                new Model("maska14", R.drawable.maskica14, "Maskice", "9000 RSD"),
                new Model("maska15", R.drawable.maskica15, "Maskice", "10000 RSD"),
                new Model("staklo1", R.drawable.staklo1, "Zastitno staklo", "9000 RSD"),
                new Model("staklo2", R.drawable.staklo2, "Zastitno staklo", "21000 RSD"),
                new Model("staklo3", R.drawable.staklo3, "Zastitno staklo", "20000 RSD"),
                new Model("staklo4", R.drawable.staklo4, "Zastitno staklo", "9000 RSD"),
                new Model("staklo5", R.drawable.staklo5, "Zastitno staklo", "9000 RSD"),
                new Model("staklo6", R.drawable.staklo6, "Zastitno staklo", "15000 RSD"),
                new Model("staklo7", R.drawable.staklo7, "Zastitno staklo", "9000 RSD"),
                new Model("staklo8", R.drawable.staklo8, "Zastitno staklo", "9000 RSD"),
                new Model("staklo9", R.drawable.staklo9, "Zastitno staklo", "7000 RSD"),
                new Model("staklo10", R.drawable.staklo10, "Zastitno staklo", "22000 RSD"),
                new Model("staklo11", R.drawable.staklo11, "Zastitno staklo", "9000 RSD"),
                new Model("staklo12", R.drawable.staklo12, "Zastitno staklo", "9000 RSD"),
                new Model("staklo13", R.drawable.staklo13, "Zastitno staklo", "11000 RSD"),
                new Model("staklo14", R.drawable.staklo14, "Zastitno staklo", "9000 RSD"),
                new Model("staklo15", R.drawable.staklo15, "Zastitno staklo", "10000 RSD")










        };


            for(Model m :items) {

                ContentValues values = new ContentValues();
                values.put("item", m.getNaziv());
                values.put("price", m.getCena());
                values.put("category", m.getIme_kategorije());
                values.put("image", m.getSlikaId());
                db.insert(ITEM_CATEGORY_TABLE, null, values);
            }
            db.close();
        Log.d("FillTable", "Table filled.");
        }





    public Model[] readItems() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(ITEM_CATEGORY_TABLE, null, null, null, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        Model[] items = new Model[cursor.getCount()];
        int i = 0;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            items[i++] = createModel(cursor);
        }

        cursor.close();
        //db.close();
        return items;
    }





    private Model createModel(Cursor cursor) {
        String itemName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
        int imageName = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE));
        String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
        String itemPrice = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE));


        return new Model(itemName, imageName, category, itemPrice);
    }






    }

