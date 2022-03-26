package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.edit_a_product.*

class EditProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_a_product)
        val s = intent.extras?.getString("message")
        val db = MyDbManager(this)
        db.openDb()

        editArticleProduct.setText("$s")
        editArticleProduct.isEnabled = false

        fbEditProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent
                intent = Intent(applicationContext, EditProductStart::class.java)
                startActivity(intent)
            }
        })

        edit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (editNameProduct.text.toString().trim() == "" || editArticleProduct.text.toString().trim() == "" || editNumRoom.text.toString().trim() == "" || editPriceProduct.text.toString().trim() == "") {
                    Toast.makeText(this@EditProductActivity, "Empty fields detected", Toast.LENGTH_SHORT).show()
                } else {
                    if (db.checkRoom(editNumRoom.text.toString().trim())) {
                            db.delArticleProduct(s!!.trim())
                            db.insertToDbProduct(
                                editNumRoom.text.toString().trim(),
                                editNameProduct.text.toString().trim(),
                                editArticleProduct.text.toString().trim(),
                                editPriceProduct.text.toString().trim())
                            db.closeDb()
                            Toast.makeText(this@EditProductActivity, "Successful editing!", Toast.LENGTH_SHORT).show()
                            val intent : Intent
                            intent = Intent(applicationContext, SearchActivity::class.java)
                            startActivity(intent)

                    } else {
                        Toast.makeText(this@EditProductActivity, "Room №${editNumRoom.text} doesn't exist", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })


    }
}