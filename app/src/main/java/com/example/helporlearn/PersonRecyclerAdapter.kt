package com.example.helporlearn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonRecyclerAdapter(private var personlist: ArrayList<Instructor>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)

        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder){
           is PersonViewHolder ->{
               holder.bind(personlist[position])
           }
       }
    }

    override fun getItemCount(): Int {
        return personlist.size
    }
     fun setFilteredList(person: ArrayList<Instructor>){
        this.personlist = person
        notifyDataSetChanged()
    }
    class PersonViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        private var name: TextView = itemView.findViewById(R.id.textViewName)
        private var surname: TextView = itemView.findViewById(R.id.textViewSurname)
        private var email: TextView = itemView.findViewById(R.id.textViewEmail)
        private var number: TextView = itemView.findViewById(R.id.textViewNumber)
        private var subject: TextView = itemView.findViewById(R.id.textViewSubject)

        fun bind(person: Instructor){
            name.text = person.Name
            surname.text = person.Surname
            email.text = person.Email
            number.text = person.Number
            subject.text = person.Subject


        }

    }

}