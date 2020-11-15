package com.example.zadankobmi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val history: ArrayList<HistoryRecord>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val historyItemLine1 = itemView.findViewById<TextView>(R.id.line1)
        val historyItemLine2 = itemView.findViewById<TextView>(R.id.line2)
        val historyItemLine3 = itemView.findViewById<TextView>(R.id.line3)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.history_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: HistoryAdapter.ViewHolder, position: Int) {
        val historyRow: HistoryRecord = history.get(position)
        val textView1 = viewHolder.historyItemLine1
        textView1.text = "${(position + 1).toString()} measurement:"
        val textView2 = viewHolder.historyItemLine2
        textView2.text = historyRow.getHistoryItemPart1()
        val textView3 = viewHolder.historyItemLine3
        textView3.text = historyRow.getHistoryItemPart2()

    }

    override fun getItemCount(): Int {
        return history.size
    }
}