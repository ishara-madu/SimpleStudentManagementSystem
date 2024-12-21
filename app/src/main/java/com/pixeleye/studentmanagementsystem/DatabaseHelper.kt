package com.pixeleye.studentmanagementsystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "ContactDatabase"
        private const val DATABASE_VERSION = 1

        val TABLE_NAME = "contacts"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_PHONE = "phone"
        val COLUMN_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_PHONE TEXT, $COLUMN_IMAGE BLOB)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Add Contact
    fun addContact(contact: Contact): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, contact.name)
        values.put(COLUMN_PHONE, contact.phone)
        values.put(COLUMN_IMAGE, contact.imagePath)
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result
    }


}