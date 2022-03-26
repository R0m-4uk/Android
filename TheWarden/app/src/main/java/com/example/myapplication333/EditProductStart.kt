package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.edit_a_product.*
import kotlinx.android.synthetic.main.edit_product_start.*

class EditProductStart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_product_start)

        val db = MyDbManager(this)
        db.openDb()

        editNumber.setText("Edit by article")
        editNumber.isEnabled = false

        fbEdit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                db.closeDb()
                val intent : Intent
                intent = Intent(applicationContext, SearchActivity::class.java)
                startActivity(intent)
            }
        })

        editNext.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (editArticle.text.toString().trim() == "") {
                    Toast.makeText(this@EditProductStart, "Empty field detected", Toast.LENGTH_SHORT).show()
                } else {
                    if(db.checkArticle(editArticle.text.toString().trim())) {
                        val intent : Intent
                        intent = Intent(applicationContext, EditProductActivity::class.java)
                        val s = editArticle.text.toString().trim()
                        intent.putExtra("message", s)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@EditProductStart, "The article '${editArticle.text}' already exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

}