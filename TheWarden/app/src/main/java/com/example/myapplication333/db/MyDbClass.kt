package com.example.myapplication333.db

object MyDbClass {
    const val TABLE_STAFF = "Staff"
    const val STAFF_COLUMN_ID = "staff_id"
    const val STAFF_COLUMN_NAME = "name_staff"
    const val  STAFF_COLUMN_PHONE = "phone"
    const val STAFF_COLUMN_LOGIN = "login"
    const val STAFF_COLUMN_PASSWORD = "password"

    const val TABLE_ROOM = "Room"
    const val ROOM_COLUMN_ID = "room_id"
    const val ROOM_COLUMN_NUMBER = "number_room"
    const val ROOM_COLUMN_THEME = "theme"
    const val ROOM_COLUMN_VOLUME = "volume"

    const val TABLE_PRODUCT = "Product"
    const val PRODUCT_COLUMN_ID = "product_id"
    const val PRODUCT_COLUMN_ROOM_ID = "id_room"
    const val PRODUCT_COLUMN_NAME = "name_product"
    const val PRODUCT_COLUMN_ARTICLE_NUMBER = "article_number"
    const val PRODUCT_COLUMN_PRICE = "price"

    const val DB_VERSION = 1
    const val DB_NAME = "MyWardenDb.db"

    // Запрос на создание таблицы
    const val CREATE_TABLE_STAFF = "CREATE TABLE IF NOT EXISTS $TABLE_STAFF (" +
            "$STAFF_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $STAFF_COLUMN_NAME TEXT, $STAFF_COLUMN_PHONE TEXT, " +
            "$STAFF_COLUMN_LOGIN TEXT, $STAFF_COLUMN_PASSWORD TEXT)"

    const val CREATE_TABLE_ROOM = "CREATE TABLE IF NOT EXISTS $TABLE_ROOM (" +
            "$ROOM_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ROOM_COLUMN_NUMBER, $ROOM_COLUMN_THEME TEXT, $ROOM_COLUMN_VOLUME INTEGER)"

    const val CREATE_TABLE_PRODUCT = "CREATE TABLE IF NOT EXISTS $TABLE_PRODUCT (" +
            "$PRODUCT_COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $PRODUCT_COLUMN_ROOM_ID INTEGER, " +
            "$PRODUCT_COLUMN_NAME TEXT, $PRODUCT_COLUMN_ARTICLE_NUMBER TEXT, $PRODUCT_COLUMN_PRICE INTEGER, FOREIGN KEY($PRODUCT_COLUMN_ROOM_ID) REFERENCES $TABLE_ROOM($ROOM_COLUMN_ID))"

    // Запрос на удаление таблицы
    const val SQL_DELETE_TABLE_STAFF = "DROP TABLE IF EXISTS $TABLE_STAFF"
    const val SQL_DELETE_TABLE_ROOM = "DROP TABLE IF EXISTS $TABLE_ROOM"
    const val SQL_DELETE_TABLE_PRODUCT = "DROP TABLE IF EXISTS $TABLE_PRODUCT"

    const val INITIAL_DATA_ROOM = "INSERT INTO $TABLE_ROOM" +
            "(ROOM_ID, NUMBER_ROOM, THEME, VOLUME) VALUES " +
            "(1, \"34\", \"Спорт-товары\", \"125\"), " +
            "(2, \"28\", \"Косметика\", \"140\"), " +
            "(3, \"90\", \"Игрушки\", \"200\")"

    const val INITIAL_DATA_STAFF = "INSERT INTO $TABLE_STAFF" +
            "(STAFF_ID, NAME_STAFF, PHONE, LOGIN, PASSWORD) VALUES " +
            "(1, \"Roman\", \"89689087734\", \"Rom4ik\", \"12345\")"

    const val INITIAL_DATA_PRODUCT = "INSERT INTO $TABLE_PRODUCT" +
            "(PRODUCT_ID, ID_ROOM, NAME_PRODUCT, ARTICLE_NUMBER, PRICE) VALUES " +
            "(1, \"34\", \"Гантели\", \"B72NS2\", \"3499\"), " +
            "(2, \"34\", \"Турник\", \"N9KO3S\", \"5000\"), " +
            "(3, \"90\", \"Мишка плюшевый\", \"M8S7FH\", \"6500\"), " +
            "(4, \"28\", \"Губная помада (красная)\", \"HO93N\", \"389\")"
}