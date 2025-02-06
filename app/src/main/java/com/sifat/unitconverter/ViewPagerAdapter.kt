package com.sifat.unitconverter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    private val quotesList = listOf(
        "জীবনে সত্যিকার সুখ তখনই মেলে, যখন আমরা অন্যদের সুখে অবদান রাখতে পারি।",
        "আপনি যদি একটি লক্ষ্য স্থির করেন এবং সেটির দিকে কাজ করেন, তবে তা অর্জন করা সম্ভব।",
        "সফলতার গোপন হলো, প্রথমে নিজের বিশ্বাসে বিশ্বাস করা।",
        "যে কষ্টের মধ্যে আপনি নিজের স্বপ্নের দিকে এগিয়ে যাচ্ছেন, সেটাই আপনার শক্তি।",
        "আপনার সময় সীমিত, তাই অন্যের জীবন যাপন করে সময় নষ্ট করবেন না।",
        "প্রত্যেকটি দিনই নতুন সুযোগের হাতছানি নিয়ে আসে, সেই সুযোগগুলো কাজে লাগান।",
        "কষ্ট ছাড়া অর্জন হয় না, তাই কষ্টকে ভালোবাসুন এবং স্বপ্নের পথে এগিয়ে যান।",
        "যত বড় স্বপ্ন দেখবেন, আপনার পথও ততই কঠিন হবে। কিন্তু সেই পথই আপনাকে শক্তিশালী করে তুলবে।",
        "আপনার দৃষ্টিভঙ্গি যদি ইতিবাচক হয়, তবে কঠিন পরিস্থিতিও সহজ হয়ে যায়।",
        "আপনি যা বিশ্বাস করেন, তা-ই আপনি অর্জন করতে পারেন। বিশ্বাসই সফলতার মূল চাবিকাঠি।"
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
