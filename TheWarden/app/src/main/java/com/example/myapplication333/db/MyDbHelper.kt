package com.example.myapplication333.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, MyDbClass.DB_NAME,
null, MyDbClass.DB_VERSION) {
// Создание таблицы
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(MyDbClass.CREATE_TABLE_STAFF)
        p0?.execSQL(MyDbClass.CREATE_TABLE_ROOM)
        p0?.execSQL(MyDbClass.CREATE_TABLE_PRODUCT)

        p0?.execSQL(MyDbClass.INITIAL_DATA_ROOM)
        p0?.execSQL(MyDbClass.INITIAL_DATA_STAFF)
        p0?.execSQL(MyDbClass.INITIAL_DATA_PRODUCT)
    }
// Обновление БД
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(MyDbClass.SQL_DELETE_TABLE_STAFF)
        p0?.execSQL(MyDbClass.SQL_DELETE_TABLE_ROOM)
        p0?.execSQL(MyDbClass.SQL_DELETE_TABLE_PRODUCT)
        onCreate(p0)
    }


}