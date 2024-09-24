package com.example.a20240923mytrainingsession

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ExListAdapder(context: Context, exerciseList: MutableList<Exercise>) :
    ArrayAdapter<Exercise>(context, R.layout.exercise_item, exerciseList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val exercise = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.exercise_item, parent, false)
        }
        val exListNameTV = view?.findViewById<TextView>(R.id.exListNameTV)
        val exListTypeTV = view?.findViewById<TextView>(R.id.exListTypeTV)
        exListNameTV?.text = exercise?.name
        exListTypeTV?.text = exercise?.type
        return view!!
    }
}