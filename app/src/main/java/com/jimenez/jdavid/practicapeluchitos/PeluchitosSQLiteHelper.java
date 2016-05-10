package com.jimenez.jdavid.practicapeluchitos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by juand_000 on 5/7/2016.
 */
public class PeluchitosSQLiteHelper extends SQLiteOpenHelper{

    private static final String DATA_BASE_NAME="PeluchitosDB";
    private static final int DATA_VERSION=1;

    String sqlCreate = "CREATE TABLE Peluchitos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad INTEGER, valor INTEGER)";

    String tabla = "CREATE TABLE VentasPeluchitos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantidad INTEGER, total INTEGER)";

    public PeluchitosSQLiteHelper(Context context){
        super(context, DATA_BASE_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(tabla);
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS Peluchitos");

        db.execSQL("DROP TABLE IF EXISTS VentasPeluchitos");
        db.execSQL(tabla);
        db.execSQL(sqlCreate);

    }



}
