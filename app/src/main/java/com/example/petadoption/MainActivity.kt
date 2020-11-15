package com.example.petadoption

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //making an intent on floating action button
        val floatingInsertButton :FloatingActionButton=findViewById(R.id.floatinginsertButton)
        floatingInsertButton.setOnClickListener {
            val intent=Intent(this,EditorActivity::class.java)
            startActivity(intent)
        }
    }

    //inflating  menu from mainActivity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    //when we select a menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteAll->return true
        }
        return super.onOptionsItemSelected(item)
    }
}