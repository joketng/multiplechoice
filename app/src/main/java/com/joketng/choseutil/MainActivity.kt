package com.joketng.choseutil

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.joketng.choseadapter.ChoseAdapter
import com.joketng.choseutil.bean.MyChoseBean
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_custom.view.*

class MainActivity : AppCompatActivity() {
    val listContent = mutableListOf<MyChoseBean>()
    lateinit var choseAdapter: ChoseAdapter<MyChoseBean>
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        repeat(20){
            listContent.add(MyChoseBean("item$it", "$it"))
        }
        rv_chose.layoutManager = LinearLayoutManager(this)
        choseAdapter = object :ChoseAdapter<MyChoseBean>(this, listContent){
            override fun bindData(holder: MyViewHolder) {
                holder.itemView.tv_text.text = listContent[holder.layoutPosition].title
            }

            override fun createCustomView(layout: LinearLayout) {
                LayoutInflater.from(context).inflate(R.layout.item_custom, layout, true)
            }
        }
        rv_chose.adapter  = choseAdapter.setOnNotSelectedListener {
            it.itemView.img_check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.vector_drawable_check_box_off))
        }.setOnIsSelectedListener {
            it.itemView.img_check.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.vector_drawable_check_box_on))
        }.setOnClickListener {
            Toast.makeText(context,"${it.adapterPosition}",Toast.LENGTH_SHORT).show()
        }.setSingleChose(false)

        btn_single_chose.setOnClickListener {
            choseAdapter.setSingleChose(true)
        }

        btn_multiple_chose.setOnClickListener {
            choseAdapter.setSingleChose(false)
        }

        btn_invert_chose.setOnClickListener {
            choseAdapter.setSelectInvert()
        }

        btn_all_chose.setOnClickListener {
            choseAdapter.setSelectAll()
        }

        btn_remove_chose.setOnClickListener {
            choseAdapter.removeSelected()
        }


    }
}
