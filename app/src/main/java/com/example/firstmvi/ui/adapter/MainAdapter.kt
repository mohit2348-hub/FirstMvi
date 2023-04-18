package com.example.firstmvi.ui.adapter

import android.view.LayoutInflater
import android.view.View.inflate
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.firstmvi.data.model.FakeDTO
import com.example.firstmvi.databinding.ActivityMainBinding
import com.example.firstmvi.databinding.RvitemsBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {


    val list = mutableListOf<FakeDTO>()


    fun addItems(list: List<FakeDTO>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val viewDataBinding: RvitemsBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RvitemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val binding = holder.viewDataBinding
        val item = this.list[position]

        binding.name.text = item.title
        binding.desc.text = item.body

    }

    override fun getItemCount(): Int {
        return this.list.size
    }

}