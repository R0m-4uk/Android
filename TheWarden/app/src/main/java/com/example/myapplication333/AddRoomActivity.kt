package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.add_a_room.*

class AddRoomActivity : AppCompatActivity() {

    lateinit var db : MyDbManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_a_room)

        db = MyDbManager(this)
        fbRoom.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent
                intent = Intent(applicationContext, SearchActivity::class.java)
                startActivity(intent)
            }
        })

        next.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                db.openDb()
                if (roomNum.text.toString().trim() == "" && theme_.text.toString().trim() == "" && volume.text.toString().trim() == "") {
                    Toast.makeText(this@AddRoomActivity, "Empty fields detected", Toast.LENGTH_SHORT).show()
                } else {
                    if (db.checkRoom(roomNum.text.toString().trim())) {
                        Toast.makeText(this@AddRoomActivity, "Room №${roomNum.text} exists", Toast.LENGTH_SHORT).show()
                    } else {
                        db.insertToDbRoom(roomNum.text.toString().trim(),theme_.text.toString().trim(),volume.text.toString().trim())
                        val intent : Intent
                        intent = Intent(applicationContext, SearchActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}