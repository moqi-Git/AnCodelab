package com.moqi.systemview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.moqi.systemview.databinding.ActivityMainBinding
import com.moqi.systemview.recyclerview.fake.FakeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.tvMain.setOnClickListener {
            startActivity(Intent(this, FakeActivity::class.java))
        }
    }
}