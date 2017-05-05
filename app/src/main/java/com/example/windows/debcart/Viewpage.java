package com.example.windows.debcart;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Windows on 3/27/2017.
 */
public class Viewpage extends ListActivity {
    private ArrayList<String> arraylist = new ArrayList<>();
    private SQLiteDatabase newDB;
    private DbHelper dbHelper;
    private String CONTACTS_TABLE_NAME = dbHelper.CONTACTS_TABLE_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        show();
        quary();
    }

    private void quary() {
        TextView tView = new TextView(this);
        TextView ttView = new TextView(this);
        // tView.setText("This data is retrieved from the database and only 4 " +
        // "of the results are displayed");
        getListView().addHeaderView(tView);
        getListView().addHeaderView(ttView);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, arraylist));

        getListView().setTextFilterEnabled(true);

    }

    private void show() {

        try {
            DbHelper dbHelper = new DbHelper(getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT email, name,address FROM " +
                    CONTACTS_TABLE_NAME, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String email = c.getString(c.getColumnIndex("email"));
                        String address = c.getString(c.getColumnIndex("address"));
                        String namee = c.getString(c.getColumnIndex("name"));
                        arraylist.add("Email: " + email);
                        arraylist.add("Name: " + namee);
                        arraylist.add("ADDRESS: "+address);
                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");

        }
//      finally {
//          if (newDB !=null)
//              newDB.execSQL("DELETE FROM " + CONTACTS_TABLE_NAME);
//          newDB.close();
//
//       }

    }
}
