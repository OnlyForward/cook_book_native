package com.example.a123.my_cook_book.models;

public class TakeDb {
    private static DbOpenHelper sDbOpenHelper;

    public static DbOpenHelper getDbOpenHelper() {
        return sDbOpenHelper;
    }

    public static void setDbOpenHelper(DbOpenHelper dbOpenHelper) {
        sDbOpenHelper = dbOpenHelper;
    }
}
