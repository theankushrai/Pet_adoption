package com.example.petadoption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class EditorActivity : AppCompatActivity() {

    var mGender:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setUpSpinner()
    }

    private fun setUpSpinner() {
        val genderSpinner:Spinner= findViewById(R.id.genderSpinner)

        //Setting up the adapter
        val genderSpinnerAdapter=ArrayAdapter.createFromResource(
            this,
            R.array.genderArray,
            R.layout.support_simple_spinner_dropdown_item
            )
        genderSpinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        genderSpinner.adapter=genderSpinnerAdapter

        //on item selected listener
        genderSpinner.onItemSelectedListener= object :AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selection:String=parent?.getItemAtPosition(position) as String
                if(!TextUtils.isEmpty(selection)){
                    mGender=when{
                        selection==getString(R.string.gender_male)->1 //Male
                        selection==getString(R.string.gender_female)->2 //Female
                        else->0//other
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                mGender=0
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }
    }

    //attaching menu withh the layout
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editor,menu)
        return true
    }

    //on selecting a item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save->return true
        }
        return super.onOptionsItemSelected(item)
    }
}