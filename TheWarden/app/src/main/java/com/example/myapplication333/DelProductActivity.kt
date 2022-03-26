package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.del_a_product.*

class DelProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.del_a_product)
        val s = intent.extras?.getString("message")
        deleteNum.setText("Remove to Room №$s")
        deleteNum.isEnabled = false
        val db = MyDbManager(this)
        db.openDb()
        fbDel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                db.closeDb()
                val intent : Intent
                intent = Intent(applicationContext, SearchActivity::class.java)
                startActivity(intent)
            }
        })

        delRoomButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (delArticle.text.toString().trim() == "") {
                    Toast.makeText(this@DelProductActivity, "Empty field detected", Toast.LENGTH_SHORT).show()
                } else {
                    if (db.checkArticle(delArticle.text.toString().trim())) {
                        db.delArticleProduct(delArticle.text.toString().trim())
                        db.closeDb()
                        Toast.makeText(this@DelProductActivity, "${delArticle.text} deleted", Toast.LENGTH_SHORT).show()
                        val intent : Intent
                        intent = Intent(applicationContext, SearchActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@DelProductActivity, "There is no such product", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })

    }
}