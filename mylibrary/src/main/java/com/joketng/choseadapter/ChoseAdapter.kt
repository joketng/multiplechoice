package com.joketng.choseadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.joketng.choseadapter.bean.BaseChoseBean

/**
 * @Description:
 * @Author:  joketng
 * @Email:  joketng@163.com
 * @Time:  2018/11/12
 */
abstract class ChoseAdapter<T : BaseChoseBean>@JvmOverloads constructor(val context: Context,
                                           private val itemList: MutableList<T>,
                                           private var isSingleChose: Boolean = true
                                            ) : RecyclerView.Adapter<ChoseAdapter.MyViewHolder>() {

    private var selectedCallBack: ((MyViewHolder) -> Unit)? = null
    private var notSelectedCallBack: ((MyViewHolder) -> Unit)? = null
    private var clickCallBack: ((MyViewHolder) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.adapter_chose, parent, false)
        val layout = itemView.findViewById<LinearLayout>(R.id.ll_item_view)
        createCustomView(layout)
        val holder = MyViewHolder(itemView)
        holder.itemView.setOnClickListener { view ->
            if (isSingleChose) {
                itemList.forEach {
                    it.selected = false
                }
            }
            itemList[holder.layoutPosition].selected = !itemList[holder.layoutPosition].selected
            clickCallBack?.invoke(holder)
            notifyDataSetChanged()
        }
        return holder
    }

    abstract fun createCustomView(layout:LinearLayout)

    abstract fun bindData(holder: MyViewHolder)

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        bindData(holder)
        if (itemList[position].selected) {
            selectedCallBack?.invoke(holder)
        } else {
            notSelectedCallBack?.invoke(holder)
        }
    }

    fun setOnIsSelectedListener(listener: ((MyViewHolder) -> Unit)): ChoseAdapter<out BaseChoseBean> {
        selectedCallBack = listener
        return this
    }

    fun setOnNotSelectedListener(listener: ((MyViewHolder) -> Unit)): ChoseAdapter<out BaseChoseBean> {
        notSelectedCallBack = listener
        return this
    }

    fun setOnClickListener(listener: ((MyViewHolder) -> Unit)): ChoseAdapter<out BaseChoseBean> {
        clickCallBack = listener
        return this
    }

    fun setSingleChose(chose: Boolean): ChoseAdapter<out BaseChoseBean>  {
        isSingleChose = chose
        return this
    }

    fun setSelectAll(){
        itemList.forEach {
            it.selected = true
        }
        notifyDataSetChanged()
    }

    fun setSelectInvert(){
        itemList.forEach {
            it.selected = !it.selected
        }
        notifyDataSetChanged()
    }

    fun removeSelected(){
        itemList.removeAll { it.selected }
        notifyDataSetChanged()
    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}