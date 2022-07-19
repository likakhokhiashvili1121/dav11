package com.example.dav11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dav11.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity(), View.OnClickListener {
    val studentList = mutableListOf<Student>()

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.insertData.setOnClickListener(this)
        binding.update.setOnClickListener(this)
        binding.remove.setOnClickListener(this)
        binding.updateAll.setOnClickListener(this)
        setContentView(binding.root)

        initData()
        binding.studentList.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter()
        binding.studentList.adapter = adapter
        adapter.submitList(studentList)



    }




    private fun initData() {
        for (i in 1..30) {
            studentList.add(Student("$i","student $i",(Math.random()*100).toInt()))
        }
    }

    override fun onClick(view: View?) {
        when (view?.id){
            R.id.remove->removeData()
            R.id.insertData->insertData()
            R.id.update->updateData()
            R.id.updateAll->updateAll()
        }
    }

    private fun updateAll() {
        val newList = mutableListOf<Student>()
        (1..10).forEach{i->
            newList.add(Student("$i","student $i",(Math.random()*100).toInt()))
        }
        adapter.submitList(newList)

    }

    /**amatebs 10 student*/
    private fun insertData() {
        val count = adapter.currentList.size
        val student = adapter.currentList[count - 1]

        val newList = mutableListOf<Student>()
        newList.addAll(adapter.currentList)

        (1..10).forEach{
            val newID = student.id .toInt() + it
            val name = "student $newID"
            val android = (Math.random()*100).toInt()
            newList.add(Student(newID.toString(),name,android))
        }

        adapter.submitList(newList)
    }

    /**updatebs pirvelis qulas*/
    private fun updateData() {

        val updateList = mutableListOf<Student>()
        updateList.addAll(adapter.currentList)

        updateList[0].apply {
            android = 99
        }

        adapter.submitList(updateList)
        adapter.notifyItemChanged(0)
    }

    /**shlis pirvelis qulas*/
    private fun removeData() {
        val updateList = mutableListOf<Student>()
        updateList.addAll(adapter.currentList)
        updateList.removeAt(0)
        adapter.submitList(updateList)
    }
}