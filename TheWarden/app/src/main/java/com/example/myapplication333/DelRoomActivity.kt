package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.del_a_product.delRoomButton
import kotlinx.android.synthetic.main.del_a_room.*

class DelRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.del_a_room)

        deleteText.setText("Delete Room")
        deleteText.isEnabled = false

        val db = MyDbManager(this)
        db.openDb()
        fbDelRoom.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                db.closeDb()
                val intent : Intent
                intent = Intent(applicationContext, SearchActivity::class.java)
                startActivity(intent)
            }
        })

        delRoomButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (delNumRoom.text.toString().trim() == "") {
                    Toast.makeText(this@DelRoomActivity, "Empty field detected", Toast.LENGTH_SHORT).show()
                } else {
                    if (db.checkRoom(delNumRoom.text.toString().trim())) {
                        db.delNumProduct(delNumRoom.text.toString().trim())
                        db.delNumRoom(delNumRoom.text.toString().trim())
                        db.closeDb()
                        Toast.makeText(this@DelRoomActivity, "Room №${delNumRoom.text} deleted", Toast.LENGTH_SHORT).show()
                        val intent : Intent
                        intent = Intent(applicationContext, SearchActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@DelRoomActivity, "Room №${delNumRoom.text} doesn't exist", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}