package com.yello.emitter.user.presentation.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yello.emitter.R
import com.yello.emitter.helper.OnItemAdapterClickListener
import com.yello.emitter.user.domain.model.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class UserAdapter(private val mCallBack: OnItemAdapterClickListener<User>) :
    RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private val mList = mutableListOf<User>()

    fun addUser(item: User) {
        mList.add(item)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) =
        holder.bindView(mList[position])

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: User) {
            val address = "${item.address.suite}, ${item.address.street}, ${item.address.city}"
            itemView.tvName.text = item.name
            itemView.tvUsername.text = item.username
            itemView.tvEmail.text = item.email
            itemView.tvPhone.text = item.phone
            itemView.tvAddress.text = address
            itemView.tvCompany.text = item.company.name

            itemView.ivMap.setOnClickListener {
                val uri =
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=${item.address.geo.lat},${item.address.geo.lng}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                it.context.startActivity(intent)
            }

            itemView.setOnClickListener { mCallBack.onItemAdapterClicked(item) }
        }
    }
}