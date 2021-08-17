package com.usha.dlmachinetest.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.usha.dlmachinetest.AllMatchesFragment;
import com.usha.dlmachinetest.MainModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "addressdatabase";
    private static final String TABLE_NAME = "address";
    private static final String COLUMN_ID = "rowId";
    private static final String COLUMN_ADDRESS_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DISTANCE = "distance";
    private static final String COLUMN_FORMATTEDADD = "formattedAddress";
    private static final String COLUMN_IS_ADDRESS_SAVED = "isaddresssaved";

    SQLiteDatabase db;
    String CREATE_ADDRESS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY ,"
            + COLUMN_ADDRESS_ID + " TEXT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PHONE + " TEXT,"
            + COLUMN_DISTANCE + " TEXT,"
            + COLUMN_FORMATTEDADD + " TEXT,"
            + COLUMN_IS_ADDRESS_SAVED + " INT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADDRESS_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean addAddress(List<MainModel.Venue> venueArrayList) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rowInserted = 0;
        for(MainModel.Venue m : venueArrayList){
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID,(new Random().nextInt(1000-1)+1)+1);
            values.put(COLUMN_ADDRESS_ID, m.getId());
            values.put(COLUMN_NAME, m.getName());
            values.put(COLUMN_PHONE, m.getContact().getPhone());
            values.put(COLUMN_DISTANCE, m.getLocation().getDistance());
            values.put(COLUMN_FORMATTEDADD, m.getLocation().getFormattedAddress().toString());
            values.put(COLUMN_IS_ADDRESS_SAVED, m.getIsAddressSaved());
            rowInserted = db.insert(TABLE_NAME, null, values);
        }

//    values.put(KEY_ISFEATURED, data.get(i).isFeatured() ? 1 : 0);

        db.close();
        if (rowInserted != -1) {
            return true;
        } else {
            return false;
        }

    }
    public boolean updateAddress(MainModel.Venue m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,m.getRowId());
        values.put(COLUMN_ADDRESS_ID, m.getId());
        values.put(COLUMN_NAME, m.getName());
        values.put(COLUMN_PHONE, m.getContact().getPhone());
        values.put(COLUMN_DISTANCE, m.getLocation().getDistance());
        values.put(COLUMN_FORMATTEDADD, m.getLocation().getFormattedAddress().toString());
        values.put(COLUMN_IS_ADDRESS_SAVED, m.getIsAddressSaved());

        long row = db.update(TABLE_NAME, values, COLUMN_ADDRESS_ID + "='" +
                m.getId() + "'", null);
        if (row > -1) {
            Log.d("DBHelperTag" , "update successfully ");
        } else {
            Log.d("DBHelperTag" , "update Failed ");
        }
        return true;
    }

    public ArrayList<MainModel.Venue> getAllAddresses() {
        boolean flag_userdata = true;
        ArrayList<MainModel.Venue> userModels = new ArrayList<>();
        String selectQuery = "SELECT DISTINCT * FROM " + TABLE_NAME;
        try {

            Cursor c = this.getReadableDatabase()
                    .rawQuery(selectQuery, null);
            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    userModels.add(getAddressDetails(c));
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userModels;
    }

    public ArrayList<MainModel.Venue> getAllSavedAddresses() {
        ArrayList<MainModel.Venue> userModels = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT * FROM address WHERE isaddresssaved" +
                    " = '"+1+"'", null);

            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    userModels.add(getAddressDetails(c));
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userModels;
    }

    public boolean isTableExists(String tableName, boolean openDb) {
        if (openDb) {
            if (this.db == null || !this.db.isOpen()) {
                this.db = getReadableDatabase();
            }

            if (!this.db.isReadOnly()) {
                this.db.close();
                this.db = getReadableDatabase();
            }
        }

        Cursor cursor = this.getReadableDatabase().
                rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                return true;
            }
        }
        return false;

    }

    private static MainModel.Venue getAddressDetails(Cursor cursor) {


        MainModel.Location location = new MainModel.Location((
                cursor.getInt(cursor.getColumnIndex(COLUMN_DISTANCE))),
                Collections.singletonList(cursor.getString(cursor.getColumnIndex(COLUMN_FORMATTEDADD))));

        MainModel.Contact contact = new MainModel.Contact(
                cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));

//

        return new MainModel.Venue(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                contact,
                location,
                cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ADDRESS_SAVED)));


    }
}
