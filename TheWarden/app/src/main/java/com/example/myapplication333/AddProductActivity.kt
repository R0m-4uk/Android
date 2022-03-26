package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.add_a_product.*
import kotlinx.android.synthetic.main.add_a_room.*

class AddProductActivity : AppCompatActivity() {

    lateinit var db : MyDbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_a_product)
        val s = intent.extras?.getString("message")
        numRoom.setText("Addition to Room №$s")
        numRoom.isEnabled = false
        db = MyDbManager(this)
        fbProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent
                intent = Intent(applicationContext, SearchActivity::class.java)
                startActivity(intent)
            }
        })

        comp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                db.openDb()
                if (nameProduct.text.toString().trim() == "" || articleProduct.text.toString().trim() == "" || priceProduct.text.toString().trim() == "") {
                    Toast.makeText(this@AddProductActivity, "Empty fields detected", Toast.LENGTH_SHORT).show()
                } else {
                        if (db.checkArticle(articleProduct.text.toString().trim())) {
                            Toast.makeText(this@AddProductActivity, "The article '${articleProduct.text}' already exists", Toast.LENGTH_SHORT).show()
                        } else {
                            db.insertToDbProduct(
                                s!!.trim(),
                                nameProduct.text.toString().trim(),
                                articleProduct.text.toString().trim(),
                                priceProduct.text.toString().trim()
                            )
                            val intent: Intent
                            intent = Intent(applicationContext, SearchActivity::class.java)
                            startActivity(intent)
                        }

                }
            }
        })
    }
}