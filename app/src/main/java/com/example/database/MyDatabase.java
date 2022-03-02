package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "cars_db";
    public static final int DB_VERSION = 1;

    public static final String TB_NAME_CAR = "car";
    public static final String COL_ID = "id";
    public static final String COL_MODEL = "model";
    public static final String COL_COLOR = "color";
    public static final String COL_DPL = "DPL";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TB_NAME_CAR + " (" + COL_ID + " Integer primary key autoincrement , " +
                " " + COL_MODEL + " Text , " + COL_COLOR + " text , " + COL_DPL + " real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME_CAR + " ");
        onCreate(db);
    }
    public boolean insertCar (Car car){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_MODEL,car.getModel());
        values.put(COL_COLOR,car.getColor());
        values.put(COL_DPL,car.getDPL()+"");

        long result = db.insert(TB_NAME_CAR,null,values);
        return result != -1;

    }
    public boolean updateCar (Car car){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_MODEL,car.getModel());
        values.put(COL_COLOR,car.getColor());
        values.put(COL_DPL,car.getDPL()+"");

        String[] args = {car.getId()+""};
        int result = db.update(TB_NAME_CAR,values,"id=?",args);
        return result > 0;
    }

    public long countCars(){
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,TB_NAME_CAR);
    }

    public boolean deleteCar (Car car){
        SQLiteDatabase db = getWritableDatabase();

        String[] args = {car.getId()+""};
        int result = db.delete(TB_NAME_CAR,"id=?",args);
        return result > 0;
    }

    public ArrayList<Car> getAllCars(){
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM "+TB_NAME_CAR,null );
        if (cursor != null && cursor.moveToFirst()){
            do {
//                int i = cursor.getColumnIndex(COL_ID);
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String model = cursor.getString(cursor.getColumnIndexOrThrow(COL_MODEL));
                String color = cursor.getString(cursor.getColumnIndexOrThrow(COL_COLOR));
                double dpl = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_DPL));

                Car car = new Car(id,model,color,dpl);
                cars.add(car);
            }while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }

    public ArrayList<Car> getCarById(String modelSearch){
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM "+TB_NAME_CAR+ " WHERE "+COL_MODEL+ " LIKE ?",new String[] {"%"+modelSearch+"%"} );
        if (cursor != null && cursor.moveToFirst()){
            do {
//                int i = cursor.getColumnIndex(COL_ID);
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String model = cursor.getString(cursor.getColumnIndexOrThrow(COL_MODEL));
                String color = cursor.getString(cursor.getColumnIndexOrThrow(COL_COLOR));
                double dpl = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_DPL));

                Car car = new Car(id,model,color,dpl);
                cars.add(car);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return cars;
    }
}
