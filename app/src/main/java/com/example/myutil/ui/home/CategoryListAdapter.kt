package com.example.myutil.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myutil.R
import com.example.myutil.databinding.TabHomeCategorylistLayoutBinding

class CategoryListAdapter: Adapter<CategoryListAdapter.CategoryListVH>() {

    private var listItems = listOf<Int>()
    private var selectPos=0
    interface OnCategoryClickListener{
        fun onCategoryClick(v: View, pos :Int, nameResId:Int)
    }

    var onCategoryClickListener : OnCategoryClickListener?=null

    fun setOnCategoryListener(onCategoryClickListener: OnCategoryClickListener){
        this.onCategoryClickListener=onCategoryClickListener
    }
    inner class CategoryListVH(private val binding: TabHomeCategorylistLayoutBinding): ViewHolder(binding.root) {
        fun bind(nameResId: Int, pos: Int) {
            val name = itemView.resources.getString(nameResId)
            binding.tvCategoryName.text = name

            when(pos) {
                selectPos -> {
                    binding.tvCategoryName.setTextColor(itemView.context.getColor(R.color.gray_25))
                    binding.llCategoryBg.background = itemView.context.getDrawable(R.drawable.bg_border_radius_8_gray850)
                }
                else -> {
                    binding.tvCategoryName.setTextColor(itemView.context.getColor(R.color.gray_850))
                    binding.llCategoryBg.background = itemView.context.getDrawable(R.drawable.bg_border_radius_8_c_gray25_s_custom)
                }
            }

            itemView.setOnClickListener {
                onCategoryClickListener?.onCategoryClick(itemView, pos, nameResId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tab_home_categorylist_layout, parent, false)
        return CategoryListVH(TabHomeCategorylistLayoutBinding.bind(view))
    }

    override fun getItemCount() = listItems.count()

    override fun onBindViewHolder(holder: CategoryListVH, position: Int) {
        if (holder.bindingAdapterPosition == RecyclerView.NO_POSITION) return
        holder.bind(listItems[holder.bindingAdapterPosition], position)
    }

    fun setListItem(items : List<Int>){
        this.listItems=items
    }
    fun getSelectId()=listItems[selectPos]

    fun selectCategoryPos(selectPos :Int){
        val oldPos=this.selectPos
        this.selectPos=selectPos
        //데이터 변경 확인을 위한 코드
        if(oldPos-1>=0){
            notifyItemChanged(oldPos-1)
        }
        if(oldPos-1!=selectPos+1){
            notifyItemChanged(selectPos+1)
        }
        if(selectPos-1>=0){
            notifyItemChanged(selectPos-1)
        }
        if (selectPos-1!=oldPos+1){
            notifyItemChanged(oldPos+1)
        }
        notifyItemChanged(oldPos)
        notifyItemChanged(selectPos)
    }

}