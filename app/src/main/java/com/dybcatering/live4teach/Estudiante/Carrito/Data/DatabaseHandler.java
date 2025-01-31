package com.dybcatering.live4teach.Estudiante.Carrito.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.dybcatering.live4teach.Estudiante.Carrito.Util.Constants;
import com.dybcatering.live4teach.Estudiante.Carrito.Model.Grocery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.dybcatering.live4teach.Estudiante.Carrito.Util.Constants.KEY_GROCERY_ITEM;
import static com.dybcatering.live4teach.Estudiante.Carrito.Util.Constants.TABLE_NAME;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GROCERY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY," + KEY_GROCERY_ITEM + " TEXT,"
                + Constants.KEY_QTY_NUMBER + " TEXT,"
                + Constants.KEY_IMAGEN+ " TEXT,"
                + Constants.KEY_DATE_NAME + " LONG);";

        db.execSQL(CREATE_GROCERY_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);

    }

    /**
     * CRUD OPERATIONS: Create, Read, Update, Delete Methods
     */

    //Add Grocery
    public void addGrocery(Grocery grocery) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_IMAGEN, grocery.getImagen());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());

        //Insert the row
        db.insert(Constants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");

    }


    //Get a Grocery
    public Grocery getGrocery(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID,
                        KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER, Constants.KEY_IMAGEN, Constants.KEY_DATE_NAME},
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();


        Grocery grocery = new Grocery();
        grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
        grocery.setName(cursor.getString(cursor.getColumnIndex(KEY_GROCERY_ITEM)));
        grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));
        grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_IMAGEN)));

        //convert timestamp to something readable
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
        String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                .getTime());

        grocery.setDateItemAdded(formatedDate);


        return grocery;
    }


    //Get all Groceries
    public List<Grocery> getAllGroceries() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Grocery> groceryList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID, KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER,
                Constants.KEY_IMAGEN,
                Constants.KEY_DATE_NAME}, null, null, null, null, Constants.KEY_DATE_NAME + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Grocery grocery = new Grocery();
                grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                grocery.setName(cursor.getString(cursor.getColumnIndex(KEY_GROCERY_ITEM)));
                grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));
                grocery.setImagen(cursor.getString(cursor.getColumnIndex(Constants.KEY_IMAGEN)));

                //convert timestamp to something readable
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formatedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                        .getTime());

                grocery.setDateItemAdded(formatedDate);

                // Add to the groceryList
                groceryList.add(grocery);

            } while (cursor.moveToNext());
        }

        return groceryList;
    }


    //Updated Grocery
    public int updateGrocery(Grocery grocery) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_IMAGEN, grocery.getImagen());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());//get system time


        //update row
        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[]{String.valueOf(grocery.getId())});
    }


    //Delete Grocery
    public void deleteGrocery(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(id)});

        db.close();

    }


    //Get count
    public int getGroceriesCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        //Toast.makeText(ctx, (CharSequence) cursor, Toast.LENGTH_SHORT).show();
        return cursor.getCount();
    }


    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        Toast.makeText(ctx, (int) count, Toast.LENGTH_SHORT).show();
        db.close();
        return count;
    }

    public int contar(String valor){
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE "+ KEY_GROCERY_ITEM + "=" + "'"+valor+"'" ;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            total = cursor.getInt(0);
        }
        db.close();

        return total;
    }

    public int contartotal(){
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = " SELECT COUNT(*) FROM " + TABLE_NAME ;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            total = cursor.getInt(0);
        }

        db.close();
        return total;
    }

}
