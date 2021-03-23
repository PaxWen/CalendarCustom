package com.hudway.calendarcustom

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var customComponent:CustomComponent = findViewById(R.id.customCalendarView)
        for (i in 0..52){
            var button:Button = Button(this)
            button.setBackgroundColor(Color.rgb(20*(i%6),20*(i%7),10*((i*2)%13)))
            button.setTextColor(Color.rgb(255-20*(i%6),255-20*(i%7),255-10*((i*2)%13)))
            button.setText((i).toString())
            button.setOnClickListener {
                Toast.makeText(this,button.text,Toast.LENGTH_SHORT).show()
            }
            customComponent.addView(button)
        }





    }
}