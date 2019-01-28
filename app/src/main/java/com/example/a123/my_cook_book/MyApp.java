package com.example.a123.my_cook_book;

import android.app.Application;
import com.example.a123.my_cook_book.models.*;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(this, "receipt-db");
        TakeDb.setDbOpenHelper(dbOpenHelper);
    }
}
