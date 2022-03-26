package com.example.myapplication333.db

class ProductObj(_name : String, _article : String, _price : String) {

    private val name : String
    private val article : String
    private val price : String

    init {
        name = _name
        article = _article
        price = _price
    }

    fun getName() : String {
        return name
    }

    fun getArticle() : String {
        return article
    }

    fun getPrice() : String {
        return price
    }

}