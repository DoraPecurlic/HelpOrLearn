package com.example.helporlearn


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList


class InstructorsListFragment : Fragment() {
    private lateinit var loadingBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var personAdapter: PersonRecyclerAdapter
    private lateinit var searchView: SearchView
    private  var personList = ArrayList<Instructor>()
    private  var database = Firebase.firestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instructors_list, container,false)

        loadingBar = view.findViewById(R.id.loadingBar)
        recyclerView = view.findViewById(R.id.recyclerView)
        personAdapter = PersonRecyclerAdapter(personList)
        recyclerView.apply {
            val manager = LinearLayoutManager(activity)
            recyclerView.layoutManager = manager
            recyclerView.setHasFixedSize(true)
            adapter = personAdapter
        }

        database.collection("1")
            .get()
            .addOnSuccessListener { result ->
                for(data in result.documents){
                    val person = data.toObject(Instructor::class.java)
                    if(person != null){
                        personList.add(person)
                    }

                }
                loadingBar.visibility=View.GONE
                personAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                loadingBar.visibility=View.GONE
                Toast.makeText(requireActivity()," failed", Toast.LENGTH_LONG).show()
            }


        searchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

        return view
    }

    private fun filterList(query: String?){
        if(query != null){
            val filteredList = ArrayList<Instructor>()
            for(i in personList){
                if(i.Subject!!.contains(query)){
                    filteredList.add(i)
                }
            }
            if(filteredList.isEmpty()){
                Toast.makeText(requireActivity(), "No instructors for entered subject", Toast.LENGTH_SHORT).show()
            }else{
                 personAdapter.setFilteredList(filteredList)
            }

        }


    }



}