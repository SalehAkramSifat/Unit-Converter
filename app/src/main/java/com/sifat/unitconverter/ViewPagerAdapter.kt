package com.sifat.unitconverter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    private val quotesList = listOf(
        "“The journey of a thousand miles begins with a single step.” – Lao Tzu",
        "“Take care of your body. It’s the only place you have to live.” – Jim Rohn",
        "“Keep your face always toward the sunshine—and shadows will fall behind you.” – Walt Whitman",
        "“Price is what you pay. Value is what you get.” – Warren Buffett"
    )

    inner class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val quoteTextView: TextView = itemView.findViewById(R.id.quoteText)

        fun bind(quote: String) {
            quoteTextView.text = quote
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_layout, parent, false)
        return PageViewHolder(view)
    }

    override fun getItemCount(): Int = quotesList.size

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(quotesList[position])
    }
}
