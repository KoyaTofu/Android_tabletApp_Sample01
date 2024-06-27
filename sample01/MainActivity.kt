package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.Transliterator.Position
import android.media.MediaCodec.OutputFrame
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


data class OrderInfo(
    val orderNo: Int,
    val menuNo: Int,
    val name: String,
    val price: Int,
    var count: Int
)

class MainActivity : AppCompatActivity() {
    var orderInfo: MutableList<OrderInfo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region | orderInfo生成
        orderInfo.add( OrderInfo(
            orderNo = 1,
            menuNo = 16,
            name = "kuma",
            price = 100,
            count = 1
        ))
        orderInfo.add( OrderInfo(
            orderNo = 2,
            menuNo = 85,
            name = "semi",
            price = 890,
            count = 1
        ))
        orderInfo.add( OrderInfo(
            orderNo = 3,
            menuNo = 853,
            name = "kani",
            price = 1500,
            count = 1
        ))
        //endregion

        drawList()
    }

    private fun drawList() {
        val lstItem: ListView = findViewById(R.id.lstItem)
        // SimpleAdapter 第２引数 MutableListオブジェクトを生成
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        //region list生成
        for( orderItem in orderInfo) {
            menuList.add( mutableMapOf(
                "orderNo" to orderItem.orderNo,
                "name" to orderItem.name,
                "price" to orderItem.price,
                "count" to orderItem.count,
                ))
        }
        //endregion

        // SimpleAdapter 第４引数 from用データの用意
        val from: Array<String> = arrayOf( "orderNo", "name", "price", "count") //, "btnInc", "btnDec")
        // SimpleAdapter 第５引数 to用データの用意
        val to: IntArray = intArrayOf( R.id.txtOrderNo, R.id.txtName, R.id.txtPrice, R.id.txtCount) //, R.id.btnInc, R.id.btnDec)

        // SimpleAdapter 生成
        val adapter = CustomAdapter( this@MainActivity, menuList, R.layout.part_list, from, to)
        //adapter = SimpleAdapter(this@MainActivity, menuList, R.layout.part_list, from, to)
        // Adapter 登録
        lstItem.adapter = adapter
        //lstItem.onItemClickListener = ClickListener()
    }

    private inner class ClickListener(): AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        }
    }

    private inner class CustomAdapter( _context: Context, _list: List<Map<String,Any>>, _layout: Int, _from: Array<String>, _to: IntArray):
        SimpleAdapter( _context, _list, _layout, _from, _to) {

        val context = _context
        val layoutInfrater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val list = _list
        val layout = _layout

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            //category_rowのレイアウトをインフレイト
            val layoutView = layoutInfrater.inflate(layout, parent,false);

            //region 表示情報の設定
            // 注文番号の設定
            val txtOrderNo: TextView = layoutView.findViewById(R.id.txtOrderNo)
            txtOrderNo.text = list[position]["orderNo"].toString()

            // 商品名の設定
            val txtName: TextView = layoutView.findViewById(R.id.txtName)
            txtName.text = list[position]["name"].toString()

            // 商品単価の設定
            val txtPrice: TextView = layoutView.findViewById(R.id.txtPrice)
            txtPrice.text = list[position]["price"].toString()

            // 購入数量の設定
            val txtCount: TextView = layoutView.findViewById(R.id.txtCount)
            txtCount.text = list[position]["count"].toString()
            //endregion

            // 加算ボタンの設定
            val btnInc: Button = layoutView.findViewById(R.id.btnInc)
            btnInc.setOnClickListener {
                orderInfo[position].count += 1
                drawList()
            }
            val btnDec: Button = layoutView.findViewById(R.id.btnDec)
            btnDec.setOnClickListener {
                if( orderInfo[position].count > 0) {
                    orderInfo[position].count -= 1
                }
                if( orderInfo[position].count == 0) {
                    orderInfo.removeAt(position)
                }
                drawList()
            }
            return layoutView
        }
    }
}