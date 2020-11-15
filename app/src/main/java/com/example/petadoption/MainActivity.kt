package com.example.petadoption

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), PetsRVAdapter {
    lateinit var viewModel: PetViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView=findViewById(R.id.petRecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=PetRecyclerViewAdaapter(this,this)
        recyclerView.adapter=adapter

        //making an intent on floating action button
        val floatingInsertButton: FloatingActionButton = findViewById(R.id.floatinginsertButton)
        floatingInsertButton.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PetViewModel::class.java)
        viewModel.allpets.observe(this, Observer {list->
            list?.let {
                adapter.onUpdateList(it)
            }
        })
    }

    //inflating  menu from mainActivity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    //when we select a menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll -> return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun onDeleteAll(item: MenuItem) {
        viewModel.deleteAll()
        Toast.makeText(this, "Everything Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(petEntity: PetEntity) {
        viewModel.delete(petEntity)
    }
}

