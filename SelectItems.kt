package com.example.sample01

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

// Menuデータクラス
data class OrderItem(
    val id: String,
    val name: String,
    var quantity: Int
)

class SelectItems : AppCompatActivity() {
    /* 注文データ格納リストを作成 */
    private var orderList = mutableListOf<OrderItem>()
    private lateinit var orderItems: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_items)

        /* オブジェクト=id を割り当て */
        val addMenu1: Button = findViewById(R.id.BTN_menu1)
        val addMenu2: Button = findViewById(R.id.BTN_menu2)
        val addMenu3: Button = findViewById(R.id.BTN_menu3)
        // ListViewを取得
        val lvOrderItems: ListView = findViewById(R.id.LV_orderItems)

        /* ListViewに反映 */
        orderItems = ArrayAdapter(this@SelectItems, android.R.layout.simple_list_item_1, getOrderItemsDisplayList())
        lvOrderItems.adapter = orderItems

        /* 注文追加 */
        addMenu1.setOnClickListener{ addItemToOrder(OrderItem("0001","唐揚げ",1))}
        addMenu2.setOnClickListener{ addItemToOrder(OrderItem("0002","焼き鳥",1))}
        addMenu3.setOnClickListener{ addItemToOrder(OrderItem("0003","寿司",1))}
    }

//region 表示用のリストを作成:getOrderItemsDisplayList
    private fun getOrderItemsDisplayList(): List<String> {
        return orderList.map{ "${it.name} | ${it.quantity}"}
    }
// endregion
//region 注文追加:addItemToOrder
private fun addItemToOrder(newItem: OrderItem) {
        val existingItem = orderList.find{ it.id == newItem.id}
        if( existingItem != null) {
            // 既存のIDなら数量のみ増やす
            existingItem.quantity += newItem.quantity
        } else {
            // 新規IDなら新規登録
            orderList.add(newItem)
        }
        updateListView()
    }
// endregion
//region ListViewの更新:updateListView
    private fun updateListView() {
        orderItems.clear()
        orderItems.addAll(getOrderItemsDisplayList())
        orderItems.notifyDataSetChanged()
    }
// endregion
}