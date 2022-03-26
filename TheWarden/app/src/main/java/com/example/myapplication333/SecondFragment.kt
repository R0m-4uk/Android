package com.example.myapplication333

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication333.db.MyDbManager

class SecondFragment : Fragment(), View.OnClickListener {

    lateinit var myDbManager : MyDbManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment2_main, container, false)

    lateinit var button : Button
    lateinit var edit1 : EditText
    lateinit var edit2 : EditText
    lateinit var edit3 : EditText
    lateinit var edit4 : EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDbManager = MyDbManager(activity?.applicationContext!!)
        button = view.findViewById(R.id.wow_button)
        button.setOnClickListener(this)
        edit1 = view.findViewById(R.id.Name)
        edit2 = view.findViewById(R.id.phone)
        edit3 = view.findViewById(R.id.register_login)
        edit4 = view.findViewById(R.id.register_password)
    }

    override fun onClick(p0: View?) {
        myDbManager.openDb()

        if (edit1.text.toString().trim() == "" || edit2.text.toString().trim() == "" || edit3.text.toString().trim() == "" || edit4.text.toString().trim() == "") {
            val toast : Toast = Toast.makeText(context, "Empty fields detected", Toast.LENGTH_SHORT)
            toast.gravity
            toast.show()
        } else {
            if (myDbManager.checkLoginPhone(edit2.text.toString().trim(),edit3.text.toString().trim())) {
                val toast : Toast = Toast.makeText(context, "An entry with such a phone or login already exists", Toast.LENGTH_SHORT)
                toast.gravity
                toast.show()
            } else {
                myDbManager.insertToDbStaff(
                    edit1.text.toString().trim(),
                    edit2.text.toString().trim(),
                    edit3.text.toString().trim(),
                    edit4.text.toString().trim()
                )
                val i: Intent
                i = Intent(requireContext(), SearchActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}
