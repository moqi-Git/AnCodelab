package com.moqi.bitmaplab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moqi.bitmaplab.databinding.ItemHistoryBinding
import com.moqi.bitmaplab.model.PictureModel

typealias OnItemClickListener = ((PictureModel) -> Unit)
typealias OnItemLongClickListener = ((PictureModel) -> Unit)

class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null
    var onItemLongClickListener: OnItemLongClickListener? = null

    private val mList = ArrayList<PictureModel>()

    class HistoryViewHolder(private val vb: ItemHistoryBinding): RecyclerView.ViewHolder(vb.root){
        fun bindData(model: PictureModel){
            vb.tvPath.text = if(model.type in 10..99){
                model.path
            } else {
                model.name
            }
            vb.tvTitle.text = model.name
            if (model.type < 100){
                vb.tvIcon.text = "预"
            } else {
                vb.tvIcon.text = "自"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val vb = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(vb)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(mList[position])
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.invoke(mList[position])
            return@setOnLongClickListener onItemLongClickListener != null
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateContentList(list: List<PictureModel>){
        if (list.isEmpty()){
            return
        }
        mList.addAll(list)
        if (list.size == 1){
            notifyItemInserted(mList.size - 1)
        } else {
            notifyDataSetChanged()
        }
    }
}