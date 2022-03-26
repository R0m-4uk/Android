package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication333.db.MyDbManager
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

     private lateinit var listView : ListView
     private lateinit var searchView : SearchView
     val db = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        db.openDb()
        val bestCities = db.getRooms()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bestCities)
        listView = findViewById(R.id.listView)
        listView.adapter = adapter

        listView.onItemClickListener = object: AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                var textv : TextView = p1 as TextView
                var str = textv.text.toString()
                Log.i("INFO: ", "itemClick: name = $str, position = $p2, id = $p3")
                    val intent = Intent(applicationContext,ElemActivity::class.java)
                    intent.putExtra("message",str)
                    startActivity(intent)
            }
        }

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Performs search when user hit the search button on the keyboard
//                if (bestCities.contains(p0)) {
//                    adapter.filter.filter(p0)
//                } else {
//                    Toast.makeText(this@MainActivity, "No match found", Toast.LENGTH_SHORT).show()
//                }
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                //Start filtering the list as user start entering the characters
                adapter.filter.filter(p0)
                return false
            }
        })

        fb.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent
                intent = Intent(applicationContext, AddRoomActivity::class.java)
                startActivity(intent)
            }
        })

        fbIcDel.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent : Intent
                intent = Intent(applicationContext, DelRoomActivity::class.java)
                startActivity(intent)
            }
        })

        db.closeDb()
    }

}