package com.example.petadoption

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class EditorActivity : AppCompatActivity() {
    private lateinit var viewModel:PetViewModel
    val CAMERA_REQUEST=1000
   private lateinit var image:Bitmap

    var mGender:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setUpSpinner()

        //setting up image
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PetViewModel::class.java)
       val cameraButton :ImageButton=findViewById(R.id.camera)
        cameraButton.setOnClickListener{
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==CAMERA_REQUEST){
           image = data!!.extras!!.get("data") as Bitmap
            val petImageView:ImageView=findViewById(R.id.EditorPetImageView)
            petImageView.setImageBitmap(image)
            petImageView.visibility=View.VISIBLE

        }

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
                    mGender= when (selection) {
                        getString(R.string.gender_male) -> 1 //Male
                        getString(R.string.gender_female) -> 2 //Female
                        else -> 0//other
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

    fun onSave(item: MenuItem) {
        val petname:EditText=findViewById(R.id.nameEditTExt)
        val petGender:Spinner=findViewById(R.id.genderSpinner)
        val petWeight:EditText=findViewById(R.id.weightEditText)
        val petBreed:EditText=findViewById(R.id.breedEditText)


//        if(petname.text.toString()!=""){
//            viewModel.insert(PetEntity(petname.text.toString(),"ohjiuh",petGender.selectedItem.toString()))
//            val intent=Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }
//        else {
//            Toast.makeText(this, "Empty name", Toast.LENGTH_SHORT).show()
//        }
        if(petname.text.toString()==""){
            Toast.makeText(this, "name is empty", Toast.LENGTH_SHORT).show()
        }
        else if(petWeight.text.toString()==""){
            Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
        }
        else if(petBreed.text.toString()==""){
            Toast.makeText(this,"breed is emptu",Toast.LENGTH_SHORT).show()
        }
        else {
            viewModel.insert(PetEntity(petname.text.toString(),"ohjiuh",
                petGender.selectedItem.toString(),petWeight.text.toString(),petBreed.text.toString(),image))
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}