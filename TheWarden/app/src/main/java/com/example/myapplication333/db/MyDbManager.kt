package com.example.myapplication333.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db : SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun closeDb() {
        myDbHelper.close()
    }

/********************************************************************************************/

    @SuppressLint("Range")
    fun checkRoom(number : String) : Boolean {
        val query = "SELECT * FROM ${MyDbClass.TABLE_ROOM}"
        val cursor : Cursor = db!!.rawQuery(query,null)
        while (cursor.moveToNext()) {
            if (number == cursor.getString(cursor.getColumnIndex("number_room"))) {
                return true
            }
        }
        cursor.close()
        return false
    }



    @SuppressLint("Range")
    fun checkLoginPassword(login : String, password : String) : Boolean
    {
        val query = "SELECT * FROM ${MyDbClass.TABLE_STAFF}"
        val cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (login == cursor.getString(cursor.getColumnIndex("login")) && password == cursor.getString(cursor.getColumnIndex("password")))
                return true
            //Log.d("INFd: ", "login = $login, cursor = ${cursor.getString(cursor.getColumnIndex("login"))}")

        }
        cursor.close()
        return false
    }

    @SuppressLint("Range")
    fun checkLoginPhone(phone : String, login : String) : Boolean {
        val query = "SELECT * FROM ${MyDbClass.TABLE_STAFF}"
        val cursor : Cursor = db!!.rawQuery(query, null)
        while (cursor.moveToNext()) {
            if (login == cursor.getString(cursor.getColumnIndex("login")) || phone == cursor.getString(cursor.getColumnIndex("phone"))) {
                return true
            }
        }
        cursor.close()
        return false
    }

    @SuppressLint("Range")
    fun checkArticle(article : String) : Boolean {
        val query = "SELECT * FROM ${MyDbClass.TABLE_PRODUCT}"
        val cursor : Cursor = db!!.rawQuery(query, null)
        while(cursor.moveToNext()) {
            if (article == cursor.getString(cursor.getColumnIndex("article_number"))) {
                return true
            }
        }
        cursor.close()
        return false
    }

    @SuppressLint("Range")
    fun checkName(name : String) : Boolean {
        val query = "SELECT * FROM ${MyDbClass.TABLE_PRODUCT}"
        val cursor : Cursor = db!!.rawQuery(query, null)
        while(cursor.moveToNext()) {
            if (name == cursor.getString(cursor.getColumnIndex("name_product"))) {
                return true
            }
        }
        cursor.close()
        return false
    }

/********************************************************************************************/
// Запись в бд
    fun insertToDbStaff(name : String, phone : String, login : String, password : String) {
        val values = ContentValues().apply {
            put(MyDbClass.STAFF_COLUMN_NAME, name)
            put(MyDbClass.STAFF_COLUMN_PHONE, phone)
            put(MyDbClass.STAFF_COLUMN_LOGIN, login)
            put(MyDbClass.STAFF_COLUMN_PASSWORD, password)
        }
        db?.insert(MyDbClass.TABLE_STAFF,null,values)
    }

    fun insertToDbRoom(number : String, theme : String, volume : String) {
        val values = ContentValues().apply {
            put(MyDbClass.ROOM_COLUMN_NUMBER, number)
            put(MyDbClass.ROOM_COLUMN_THEME, theme)
            put(MyDbClass.ROOM_COLUMN_VOLUME, volume)
        }
        db?.insert(MyDbClass.TABLE_ROOM,null,values)
    }

    fun insertToDbProduct(number : String , name : String, article_number : String, price : String) {
        val values = ContentValues().apply {
            put(MyDbClass.PRODUCT_COLUMN_ROOM_ID, number)
            put(MyDbClass.PRODUCT_COLUMN_NAME, name)
            put(MyDbClass.PRODUCT_COLUMN_ARTICLE_NUMBER, article_number)
            put(MyDbClass.PRODUCT_COLUMN_PRICE, price)
        }
        db?.insert(MyDbClass.TABLE_PRODUCT,null,values)
    }
/********************************************************************************************/

    @SuppressLint("Recycle")
    fun getCountProduct(s : String): Int {
        val query = "SELECT * FROM ${MyDbClass.TABLE_PRODUCT} WHERE id_room = $s"
        val cursor : Cursor = db!!.rawQuery(query,null)
        return cursor.count
    }

    @SuppressLint("Range")
    fun getRooms() : List<String> {
        val list : ArrayList<String> = ArrayList()
        val query = "SELECT * FROM ${MyDbClass.TABLE_ROOM}"
        val cursor = db!!.rawQuery(query,null)
        while (cursor.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndex(MyDbClass.ROOM_COLUMN_NUMBER)))
        }
        cursor.close()
        return list
    }

    @SuppressLint("Range")
    fun getAllDataProduct(s : String) : List<ProductObj> {

        val list : ArrayList<ProductObj> = ArrayList()
        val query = "SELECT * FROM ${MyDbClass.TABLE_PRODUCT} WHERE id_room = $s"
        val cursor = db!!.rawQuery(query,null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val po = ProductObj(cursor.getString(cursor.getColumnIndex(MyDbClass.PRODUCT_COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(MyDbClass.PRODUCT_COLUMN_ARTICLE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(MyDbClass.PRODUCT_COLUMN_PRICE)))
                    list.add(po)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        return list
    }

    @SuppressLint("Range")
    fun getRoomData(s: String?) : List<String> {
        val l : ArrayList<String> = ArrayList()
        val query = "SELECT * FROM ${MyDbClass.TABLE_ROOM}"
        val cursor = db!!.rawQuery(query,null)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (s == cursor.getString(cursor.getColumnIndex(MyDbClass.ROOM_COLUMN_NUMBER))) {
                    l.add(cursor.getString(cursor.getColumnIndex(MyDbClass.ROOM_COLUMN_NUMBER)))
                    l.add(cursor.getString(cursor.getColumnIndex(MyDbClass.ROOM_COLUMN_THEME)))
                    l.add(cursor.getString(cursor.getColumnIndex(MyDbClass.ROOM_COLUMN_VOLUME)))
                }
            }
        }
        cursor.close()
        return l
    }

/********************************************************************************************/

    fun delArticleProduct(article : String) {
        val query = "DELETE FROM ${MyDbClass.TABLE_PRODUCT} WHERE article_number = '$article'"
        db!!.execSQL(query)
    }

    fun delNumProduct(number : String) {
        val query = "DELETE FROM ${MyDbClass.TABLE_PRODUCT} WHERE id_room = '$number'"
        db!!.execSQL(query)
    }

    fun delNumRoom(number : String) {
        val query = "DELETE FROM ${MyDbClass.TABLE_ROOM} WHERE number_room = '$number'"
        db!!.execSQL(query)
    }


}