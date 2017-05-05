package com.example.windows.debcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Windows on 1/13/2017.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "person.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts " + "(password string primary key,name text,email text, address text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);

    }

    public boolean insertContact(String name, String email, String address,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(CONTACTS_COLUMN_NAME,name);
            contentValues.put(CONTACTS_COLUMN_EMAIL, email);
            contentValues.put(CONTACTS_COLUMN_ADDRESS, address);
            contentValues.put(CONTACTS_COLUMN_PASSWORD, password);

            db.insert("contacts", null, contentValues);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(db!=null)
                db.close();
        }
        return true;
    }
    public Cursor getData(int password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where password=" + password+ "", null);
        return res;
    }

    public ArrayList<Person_details> getAllContacts() {
        ArrayList<Person_details> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        try {
            Cursor res = db.rawQuery("select * from contacts", null);

            res.moveToFirst();

            while (res.isAfterLast() == false) {
                Person_details s = new Person_details();
                s.setPassword(res.getString(res.getColumnIndex(CONTACTS_COLUMN_PASSWORD)));
                s.setName(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
                s.setEmail(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
                s.setAddress(res.getString(res.getColumnIndex(CONTACTS_COLUMN_ADDRESS)));
                array_list.add(s);
                res.moveToNext();
            }
            res.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(db!=null)
            db.close();
        }
        return array_list;
    }


    public String getAllTags(String a) {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + "contacts" + " where email = '" +a+ "'" , null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("passowrd"));

            } while (c.moveToNext());
        }
        return str;
    }
}
