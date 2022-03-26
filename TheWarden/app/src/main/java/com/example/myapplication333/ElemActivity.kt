package com.example.myapplication333

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication333.adapter.MyAdapter
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.activity_elem.*

class ElemActivity : AppCompatActivity() {


    lateinit var rv : RecyclerView
    lateinit var myAdapter : MyAdapter
    lateinit var list : List<String>
    val db = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elem)
        val s = intent.extras?.getString("message")
        db.openDb()
        list = db.getRoomData(s)
        numberRoom.text = "Room №${db.getRoomData(s)[0]}"
        themeRoom.text = "The theme of the Room: ${db.getRoomData(s)[1]}"
        volumeRoom.text = "The volume of the Room: ${db.getRoomData(s)[2]}"
        Log.i("ListOfRoom: ", "List = ${list[0]}, ${list[1]}, ${list[2]}")
        rv = findViewById(R.id.resyclerView)
        Log.i("NumberRoom: ", s!!)

        setRecyclerView(s)

        fbAddProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent: Intent
                intent = Intent(applicationContext, AddProductActivity::class.java)
                intent.putExtra("message", s)
                startActivity(intent)
            }
        })

        fbDeleteProduct.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent: Intent
                intent = Intent(applicationContext, DelProductActivity::class.java)
                intent.putExtra("message", s)
                startActivity(intent)
            }
        })

        fbEditProduct_.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent: Intent
                intent = Intent(applicationContext, EditProductStart::class.java)
                startActivity(intent)
            }
        })
    }

    fun setRecyclerView(s : String?) {
        rv.layoutManager = LinearLayoutManager(this)
        myAdapter = MyAdapter(this,db.getCountProduct(s!!),db.getAllDataProduct(s))
        rv.adapter = myAdapter
    }
}