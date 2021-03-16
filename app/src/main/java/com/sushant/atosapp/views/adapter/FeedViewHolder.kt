package com.sushant.atosapp.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sushant.atosapp.databinding.ListItemBinding
import com.sushant.atosapp.model.FeedItem

class FeedViewHolder(var mListItemBinding : ListItemBinding) : BaseViewHolder<FeedItem>(mListItemBinding){
    override fun bind(model: FeedItem, position: Int, listenerI: ItemAdapter.IAdapterItemListener<FeedItem>?) {
        super.bind(model, position, listenerI)
        mListItemBinding.item = model
    }

    companion object{
        fun getInstance(parent: ViewGroup) : FeedViewHolder{
            val binding =ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FeedViewHolder(binding)
        }
    }
}