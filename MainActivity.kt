package com.example.sample01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sample01.ui.theme.Sample01Theme
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()  // システムバー（ステータスバーやナビゲーションバー）を透過させ、アプリのコンテンツが画面全体に広がるように表示
        setContentView(R.layout.screen1)   // デフォルト画面の指定

        // オブジェクト＝id 紐づけ
        val buttonNavigate:Button = findViewById(R.id.BTN_transScreen2)

        /* ボタン押下：「商品選択」画面へ遷移 */
        buttonNavigate.setOnClickListener{
            // Intentオブジェクト生成
            val intent = Intent(this@MainActivity, SelectItems::class.java)
            // 新しいアクティビティを開始
            startActivity(intent)
        }

    }
}
