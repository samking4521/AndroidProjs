package com.example.audiorecorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GalleryActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var records: ArrayList<AudioRecord>
    private lateinit var myAdapter: Adapter
    private lateinit var db: AppDatabase
    private lateinit var searchInput: TextInputEditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var editBar: View
    private lateinit var btnClose: ImageButton
    private lateinit var btnSelectAll: ImageButton
    private lateinit var bottomSheet: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var btnRename: LinearLayout
    private lateinit var btnDelete: LinearLayout
    private lateinit var tvRename: TextView
    private lateinit var tvDelete: TextView
    private lateinit var buttonEdit: ImageButton
    private lateinit var buttonDelete: ImageButton

    private var allChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        recyclerView = findViewById(R.id.recyclerView)

        records = ArrayList()

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "audioRecords"
        ).build()

        myAdapter = Adapter(records, this)
        recyclerView.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(context)
        }

        fetchAll()

        btnRename = findViewById(R.id.btnEdit)
        btnDelete = findViewById(R.id.btnDelete)
        tvRename = findViewById(R.id.textViewEdit)
        tvDelete = findViewById(R.id.tvDel)
        buttonEdit = findViewById(R.id.imageButtonEdit)
        buttonDelete = findViewById(R.id.imageButtonDelete)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        editBar = findViewById(R.id.editBar)
        btnClose = findViewById(R.id.btnClose)
        btnSelectAll = findViewById(R.id.btnSelectAll)
        bottomSheet = findViewById(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        searchInput = findViewById(R.id.search_input)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var query = s.toString()
                searchDatabase(query)

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        btnClose.setOnClickListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            editBar.visibility = View.GONE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

            records.map {
                it.isChecked = false
            }
            myAdapter.setEditMode(false)
        }

        btnSelectAll.setOnClickListener {
            allChecked = !allChecked
            records.map {
                it.isChecked = allChecked
            }
            myAdapter.notifyDataSetChanged()

            if(allChecked){
                disableRename()
                enableDelete()
            }else{
                disableRename()
                disableDelete()
            }
        }

        btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete record?")

            val rbRecords = records.count{
                it.isChecked
            }
            builder.setMessage("Are you sure you want to delete $rbRecords record(s) ?")
            builder.setPositiveButton("Delete"){
                _, _ ->
                val toDelete = records.filter {
                    it.isChecked
                }.toTypedArray()
                GlobalScope.launch {
                    db.audioRecordDao().delete(toDelete)
                    runOnUiThread {
                        records.removeAll(toDelete)
                        myAdapter.notifyDataSetChanged()
                        leaveEditMode()
                    }
                }
            }

            builder.setNegativeButton("Cancel"){
                _, _ ->
                // it does nothing
            }

            val dialog = builder.create()
            dialog.show()
        }

        btnRename.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = this.layoutInflater.inflate(R.layout.rename_layout, null)
            builder.setView(dialogView)
            val dialog = builder.create()
            val record = records.filter {
                it.isChecked
            }.get(0)
            val textInput = dialogView.findViewById<TextInputEditText>(R.id.filenameInput)
            textInput.setText(record.filename)

            dialogView.findViewById<Button>(R.id.btnOk).setOnClickListener {
                    val input = textInput.text.toString()
                if(input.isEmpty()){
                    Toast.makeText(this, "A name is required", Toast.LENGTH_LONG).show()
                }else {
                    record.filename = input
                    GlobalScope.launch {
                        db.audioRecordDao().update(record)
                        runOnUiThread {
                            myAdapter.notifyItemChanged(records.indexOf(record))
                            dialog.dismiss()
                            leaveEditMode()
                        }
                    }
                }
            }

            dialogView.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                    dialog.dismiss()
            }


            dialog.show()
        }

    }

    private fun leaveEditMode(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)



        editBar.visibility = View.GONE
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        records.map {
            it.isChecked = false
        }
        myAdapter.setEditMode(false)
    }

    private fun disableRename(){
        btnRename.isClickable = false
        buttonEdit.backgroundTintList = ResourcesCompat.getColorStateList(resources, R.color.grayDark, theme)
        tvRename.setTextColor(ResourcesCompat.getColorStateList(resources, R.color.grayDark, theme))
    }

    private fun disableDelete(){
        btnDelete.isClickable = false
        buttonDelete.backgroundTintList = ResourcesCompat.getColorStateList(resources, R.color.grayDark, theme)
        tvDelete.setTextColor(ResourcesCompat.getColorStateList(resources, R.color.grayDark, theme))
    }

    private fun enableRename(){
        btnRename.isClickable = true
        buttonEdit.backgroundTintList = ResourcesCompat.getColorStateList(resources, R.color.grayDark, theme)
        tvRename.setTextColor(ResourcesCompat.getColorStateList(resources, R.color.grayDarkDisabled, theme))
    }

    private fun enableDelete(){
        btnDelete.isClickable = true
        buttonDelete.backgroundTintList = ResourcesCompat.getColorStateList(resources, R.color.playerColor, theme)
        tvDelete.setTextColor(ResourcesCompat.getColorStateList(resources, R.color.grayDarkDisabled, theme))
    }

    private fun searchDatabase(query: String) {
        GlobalScope.launch {
            records.clear()
            val queryResult = db.audioRecordDao().searchDatabase("%$query%")
            records.addAll(queryResult)

            runOnUiThread {
                myAdapter.notifyDataSetChanged()

            }

        }
    }

    private fun fetchAll() {
        GlobalScope.launch {
            records.clear()
            val queryResult = db.audioRecordDao().getAll()
            records.addAll(queryResult)

            myAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClickListener(position: Int) {
        val audioRecord = records[position]

        if(myAdapter.isEditMode()){
            records[position].isChecked = !records[position].isChecked
            myAdapter.notifyItemChanged(position)

            var rbSelected = records.count{it.isChecked}
            when(rbSelected){
                0 -> {
                    disableRename()
                    disableDelete()
                }
                1 -> {
                    enableDelete()
                    enableRename()
                }
                else -> {
                    disableRename()
                    enableDelete()
                }
            }
        }else{
            val intent = Intent(this, AudioPlayerActivity::class.java)
            intent.putExtra("filepath", audioRecord.filePath)
            intent.putExtra("filename", audioRecord.filename)
            startActivity(intent)
        }

    }

    override fun onItemLongClickListener(position: Int) {
        myAdapter.setEditMode(true)
        records[position].isChecked = !records[position].isChecked
        myAdapter.notifyItemChanged(position)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        if (myAdapter.isEditMode() && editBar.visibility === View.GONE) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)

            editBar.visibility = View.VISIBLE

            enableDelete()
            enableRename()

        }
    }

}
