
開発環境：Android Studio
開発言語：Kotlin
用途：飲食店の卓上で使われるタブレットタイプの注文システム
データクラス：data class OrderItem( val id: String, val name: String, var quantity: Int )

 < xmlファイル_仕様 >
1. Activityは商品選択(MenuChoisAct )、注文確認(OrderComfirmAct)の２つ
　Activity1: MenuChoisAct ,  Activity2: OrderComfirmAct
2.  Viewはメイン(activity_main)、商品選択(menu_chois)、注文確認(order_comfirm)の３つ
　Activity1のView: activity_main, menu_chois
　Activity2のView: order_comfirm
　
3. View: activity_main のデザイン
　TextView: TXT_Screen1Title: "いらっしゃいませ"
　Button: BTN_TransScreen2: "商品選択画面へ": 画面遷移[menu_chois]
4. View: menu_chois のデザイン
　TextView: TXT_Screen2Title: "商品選択"
　Button: BTN_TransScreen1: "戻る": 画面遷移[activity_main]
　Button: BTN_TransScreen3: "注文": 画面遷移[order_comfirm]
　Button: BTN_Menu1: "唐揚げ"
　Button: BTN_Menu2: "焼き鳥"
　Button: BTN_Menu3: "照り焼き風お好み焼きドリア乗せジェノベーゼパスタ" (長いため3行で表示)
　Button: BTN_Clear: "全取り消し"
　RecyclerView: RV_OrderItems
5. View: order_comfirm のデザイン
　TextView: TXT_Screen2Title: "注文内容確"
　Button: BTN_TransScreen2: "戻る": 画面遷移[menu_chois]
　Button: BTN_AddMenu: "+"
　Button: BTN_SubMenu: "-"
　RecyclerView: RV_OrderItems

 < Activity_機能 >
Activity1: MenuChoisAct
1. BTN_TransScreen2, BTN_TransScreen1 の時は同Activity内での画面遷移のみに限る
2. BTN_Menu1押下時、OrderItem( "0001", "唐揚げ", 1) で orderList: MutableList<OrderItem> に追加する
   BTN_Menu2押下時、OrderItem( "0002", "焼き鳥", 1) で orderList: MutableList<OrderItem> に追加する
   BTN_Menu3押下時、OrderItem( "0003", "照り焼き風お好み焼きドリア乗せジェノベーゼパスタ", 1) で orderList: MutableList<OrderItem> に追加する
3. orderListの内容をリアルタイムでRecyclerViewに表示する
   表示内容「${orderList.name} (適度な空白) ${orderList.quantity}」
   ※orderList.nameが10文字以上の場合は２行に分割する
   ※全体の文字配置バランスが良くなるように調整
2. BTN_TransScreen3 ではActivityレベルで遷移する
   ※アクティビティ間において、orderList: MutableList<OrderItem>を渡す

Activity2: OrderComfirmAct
1. MenuChoisActからorderList: MutableList<OrderItem>を受け取る
2. orderListの内容をリアルタイムでRecyclerViewに表示する
   表示内容「${orderList.name} (適度な空白) ${orderList.quantity} (余白) [BTN_AddMenu] [BTN_SubMenu]」
   ※orderList.nameが10文字以上の場合は２行に分割する
   ※全体の文字配置バランスが良くなるように調整
   ※BTN_AddMenu, BTN_SubMenu はButtonとして配置,機能する。
     各行に１つずつ配置(例: 1行しかデータがなければ１行分、3行データがあれば３行分生成)
3. BTN_AddMenu押下時、該当行の orderList.quantity += 1
   BTN_SubMenu押下時、該当行の orderList.quantity -= 1
   ※orderList.quantity==0 の時、orderListから削除

