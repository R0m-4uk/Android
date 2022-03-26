package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication333.db.MyDbManager

class FirstFragment : Fragment(), View.OnClickListener {

    lateinit var myDbManager : MyDbManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    private lateinit var button : Button
    private lateinit var edit1 : EditText
    private lateinit var edit2 : EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDbManager = MyDbManager(activity?.applicationContext!!)
        button = view.findViewById(R.id.nice_button)
        button.setOnClickListener(this)
        edit1 = view.findViewById(R.id.login)
        edit2 = view.findViewById(R.id.password)
    }

    override fun onClick(p0: View?) {
        myDbManager.openDb()
        if (edit1.text.toString().trim() == "" || edit2.text.toString().trim() == "") {
            Toast.makeText(activity?.applicationContext, "Empty fields detected", Toast.LENGTH_SHORT).show()
        } else {
            if(myDbManager.checkLoginPassword(edit1.text.toString().trim(), edit2.text.toString().trim())) {
                val i: Intent
                i = Intent(requireContext(), SearchActivity::class.java)
                startActivity(i)
        } else {
            Log.i("INFO: ", "login = ${edit1.text}, password = ${edit2.text}")
            Toast.makeText(activity?.applicationContext, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
        }

}