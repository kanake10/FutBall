package com.example.futball.presentation.adapter

import com.example.futball.domain.models.Players
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futball.databinding.PlayerItemBinding

//adapt the diff util recyclerview

class FutBallAdapter : RecyclerView.Adapter<FutBallAdapter.MyViewHolder>() {

    private var listener :((Players)->Unit)?=null

    var list = mutableListOf<Players>()

    fun setContentList(list: MutableList<Players>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: PlayerItemBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FutBallAdapter.MyViewHolder {
        val binding =
            PlayerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l:(Players)->Unit){
        listener= l
    }

    override fun onBindViewHolder(holder: FutBallAdapter.MyViewHolder, position: Int) {
        holder.viewHolder.players = this.list[position]
        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }
    override fun getItemCount(): Int {
        return this.list.size
    }
}