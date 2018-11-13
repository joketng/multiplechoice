# multiplechoice
recyclerview about single choice and multiple choice
##screenshot

## Quick Setup
```
dependencies {
	    implementation 'com.joketng:choseutil:1.0.0'
}
```

## Usage
Add RecyclerView to your layout
```xml
    <android.support.v7.widget.RecyclerView
            android:id="@+id/rv_chose"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            />
```
Add LayoutManager
```
    rv_chose.layoutManager = LinearLayoutManager(context)
    //or set other LayoutManager
    
```
then set recyclerAdapter
```kotlin
rv_chose.adapter = object :ChoseAdapter<MyChoseBean>(this, listContent){
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
//            Toast.makeText(context,"${it.adapterPosition}",Toast.LENGTH_SHORT).show()
        }.setSingleChose(false)
```
you can add custom layout in method *createCustomView()*, and you can bind data by method *bindData()*,
the method *setOnNotSelectedListener()* is to set unselected drawable in your widget, the method *setOnIsSelectedListener* is to set selected drawable in your widget, method *setSingleChose*, it's state *true* represent singleChoice and *false* represent multipleChoice 
 



### License
choseutil by *joketng* is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).