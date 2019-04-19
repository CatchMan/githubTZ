package com.catchman.githubtesttask.ui.userList.adapter

import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.catchman.domain.model.CommonUser
import com.catchman.githubtesttask.R
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private var onClickListener: View.OnClickListener) :
    RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {

    private var users: ArrayList<CommonUser> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        if (position in 0 until users.size) {
            holder.tvNick.text = users[position].login
            initImage(holder, position)
            holder.itemView.setOnClickListener(onClickListener)
        }
    }
    private fun initImage(holder: UsersViewHolder, position: Int){
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        Glide
            .with(holder.itemView.context)
            .load(users[position].avatar_url)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(holder.civImage);
    }

    override fun getItemCount() = users.size

    fun updateData(basicModels: List<CommonUser>){
        this.users = ArrayList(basicModels)
        notifyDataSetChanged()
    }

    fun addData(basicModels: List<CommonUser>){
        this.users.addAll(basicModels)
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNick = itemView.tvNick
        val civImage = itemView.civImage
    }
}