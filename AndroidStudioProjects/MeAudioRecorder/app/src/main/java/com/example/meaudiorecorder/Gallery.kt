package com.example.meaudiorecorder

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.meaudiorecorder.RoomDb.AppDatabase
import com.example.meaudiorecorder.RoomDb.DbRecord
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Gallery : AppCompatActivity(), ListClickListeners {
    private lateinit var database: AppDatabase
    private var allRecords: ArrayList<DbRecord> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private var editState = false
    private lateinit var topBar: RelativeLayout
    private lateinit var listBottomSheet: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var delBtn: ImageView
    private lateinit var delBtnContainer: LinearLayout
    private lateinit var editBtnContainer: LinearLayout
    private lateinit var selectAll: ImageButton
    private lateinit var cancelEdit: ImageButton
    private lateinit var btnEditText: TextView
    private lateinit var btnDelTextView: TextView
    private lateinit var editBtn: ImageView
    private lateinit var searchBox: EditText
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var audioData: DbRecord

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AudioRecord"
        ).build()

        recyclerView = findViewById(R.id.recyclerView)
        topBar = findViewById(R.id.topbar)
        listBottomSheet = findViewById(R.id.listBottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(listBottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.isDraggable = false
        editBtnContainer = findViewById(R.id.editBtnCont)
        delBtnContainer = findViewById(R.id.delBtnCont)
        cancelEdit = findViewById(R.id.cancelEdit)
        selectAll = findViewById(R.id.selectAll)
        btnEditText = findViewById(R.id.btnEditText)
        btnDelTextView = findViewById(R.id.btnDelText)
        editBtn = findViewById(R.id.editBtn)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        searchBox = findViewById(R.id.searchBox)
        delBtn = findViewById(R.id.delBtn)
        adapter = Adapter(allRecords, this)
        recyclerView.layoutManager = LinearLayoutManager(this@Gallery)
        recyclerView.adapter = adapter


        searchBox.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterAudioData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        cancelEdit.setOnClickListener {
            closeEditMode()
            adapter.notifyDataSetChanged()
        }

        selectAll.setOnClickListener {
            val isChecked = allRecords.count { it.checked }
            if (isChecked == 0) {
                allRecords.map {
                    it.checked = true
                }
              enableDelEditBtn()

            } else if (isChecked >= 1 && isChecked < allRecords.size) {
                allRecords.map {
                    if (it.checked == false) {
                        it.checked = true
                    }
                }
               enableDelEditBtn()

            } else {
                allRecords.map {
                    it.checked = false
                }
              disableEditDelBtn()
            }

            adapter.notifyDataSetChanged()

        }

        editBtnContainer.setOnClickListener {
            showCustomEditDialog()
        }

        delBtnContainer.setOnClickListener {
            showAlertDialog()
        }

        fetchAll()

    }

    private fun filterAudioData(text: String){
           GlobalScope.launch{
               allRecords.clear()
               val records = database.audioDao().getAudioRecordByName("%$text%")
               allRecords.addAll(records)
               runOnUiThread {
                   adapter.notifyDataSetChanged()
               }
           }
    }

    private fun closeEditMode() {
        adapter.toggleEditState(false)
        adapter.toggleEditMode(false)
        editState = false
        topBar.visibility = View.GONE
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        allRecords.map {
            it.checked = false
        }
    }

    private fun hideKeyboard(textInput: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // Use current focus or textInput to hide the keyboard
        val view = textInput
        view.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            it.clearFocus() // Clear focus to prevent keyboard from reappearing
        }
    }

    private fun showCustomEditDialog() {
        val audioObj = allRecords.filter { it.checked }
        // Inflate the custom layout
        val dialogView = LayoutInflater.from(this).inflate(R.layout.edit_layout, null)

        // Access views from the custom layout
        val editTextView = dialogView.findViewById<EditText>(R.id.renameEditText)
        editTextView.setText(audioObj[0].fileName)
        val editCancelBtn = dialogView.findViewById<MaterialButton>(R.id.renameCancelBtn)
        val editSaveBtn = dialogView.findViewById<MaterialButton>(R.id.renameSaveBtn)

        // Build the dialog
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        editCancelBtn.setOnClickListener {
            dialog.dismiss()
        }


        editSaveBtn.setOnClickListener {
            val filename = editTextView.text.toString()
            GlobalScope.launch {
                audioObj[0].fileName = filename
                database.audioDao().updateRecord(audioObj[0])
                runOnUiThread {
                    hideKeyboard(editTextView)
                    Handler(Looper.getMainLooper()).postDelayed({
                        dialog.dismiss()
                        closeEditMode()
                        adapter.notifyDataSetChanged()
                    }, 100)

                }
            }
        }
        dialog.show()
    }

    private fun deleteRecords() {
        val toDelete = allRecords.filter { it.checked }
        GlobalScope.launch {
            toDelete.map {
                database.audioDao().deleteRecord(it)
            }
            runOnUiThread {
                allRecords.removeAll { it.checked }
                closeEditMode()
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun showAlertDialog() {
        val checkCount = allRecords.count { it.checked }
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete record?")
        builder.setMessage("Are you sure you want to delete $checkCount record(s)?")

        builder.setPositiveButton("DELETE") { dialog, _ ->
            // Handle positive button click
            deleteRecords()
            dialog.dismiss()
        }

        builder.setNegativeButton("CANCEL") { dialog, _ ->
            // Handle negative button click
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun fetchAll() {
        GlobalScope.launch {
            val records = database.audioDao().getAllRecords()
            // ✅ Update UI on main thread
            runOnUiThread {
                allRecords.clear()
                allRecords.addAll(records)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(position: Int) {
         audioData = allRecords[position]
        if(editState){
            if (audioData.checked) {
                audioData.checked = false
                adapter.notifyItemChanged(position)
                val numChecked = allRecords.count { it.checked }
                if (numChecked == 0) {
                   disableEditDelBtn()
                }
                if (numChecked == 1) {
                  enableEditBtn()
                }
            } else {
                audioData.checked = true
                adapter.notifyItemChanged(position)
                val numChecked = allRecords.count { it.checked }
                if (numChecked > 1) {
                   disableEditBtn()
                } else {
                   enableDelEditBtn()
                }


            }

        } else {
            val intent = Intent(this, AudioPlayer::class.java)

            intent.putExtra("fileName", audioData.fileName)
            intent.putExtra("duration", audioData.duration)
            intent.putExtra("filePath", audioData.filePath)

            startActivity(intent)
        }
    }

    override fun onLongClick(position: Int): Boolean {
        val audioData = allRecords[position]

        if (!editState) {
            adapter.toggleEditState(true)
            editState = true
            audioData.checked = true
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
            topBar.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            val numChecked = allRecords.count { it.checked }
           if(numChecked == 1) {
              enableDelEditBtn()
            }


            adapter.notifyDataSetChanged()
        } else {
            if (audioData.checked) {
                audioData.checked = false
                adapter.notifyItemChanged(position)
                val numChecked = allRecords.count { it.checked }
                if (numChecked == 0) {
                   disableEditDelBtn()
                }

                if (numChecked == 1) {
                   enableEditBtn()
                }
            } else {
                audioData.checked = true
                adapter.notifyItemChanged(position)
                val numChecked = allRecords.count { it.checked }
                if (numChecked > 1) {
                   disableEditBtn()
                } else {
                   enableDelEditBtn()
                }

            }

        }
        return true
    }

    private fun disableEditDelBtn(){
        delBtnContainer.isClickable = false
        delBtn.setColorFilter(
            ContextCompat.getColor(this, R.color.grayMid),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        btnDelTextView.setTextColor(ContextCompat.getColor(this, R.color.grayMid))

        editBtnContainer.isClickable = false
        editBtn.setColorFilter(
            ContextCompat.getColor(this, R.color.grayMid),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        btnEditText.setTextColor(ContextCompat.getColor(this, R.color.grayMid))
    }

    private fun enableEditBtn(){
        editBtnContainer.isClickable = true
        editBtn.setColorFilter(
            ContextCompat.getColor(this, R.color.grayDark),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        btnEditText.setTextColor(ContextCompat.getColor(this, R.color.grayDark))
    }

    private fun disableEditBtn(){
        editBtnContainer.isClickable = false
        editBtn.setColorFilter(
            ContextCompat.getColor(this, R.color.grayMid),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        btnEditText.setTextColor(ContextCompat.getColor(this, R.color.grayMid))
    }

    private fun enableDelEditBtn(){
        delBtnContainer.isClickable = true
        delBtn.setColorFilter(
            ContextCompat.getColor(this, R.color.red),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        btnDelTextView.setTextColor(Color.RED)

        editBtnContainer.isClickable = true
        editBtn.setColorFilter(
            ContextCompat.getColor(this, R.color.grayDark),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
        btnEditText.setTextColor(ContextCompat.getColor(this, R.color.grayDark))
    }
}




