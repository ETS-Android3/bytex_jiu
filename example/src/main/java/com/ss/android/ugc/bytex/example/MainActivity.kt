package com.ss.android.ugc.bytex.example

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ss.android.ugc.bytex.example.coverage.CoverageReportTask
import com.ss.android.ugc.bytex.example.junk.JunkFieldTest
import com.ss.android.ugc.bytex.example.junk.WWW

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle coverage log info, send to the server
        CoverageReportTask.init()
//        Log.e("aa", WWW.NOT.toString())
        Log.e("aaaaaaaaaaaaaaaaaaaaaa", JunkFieldTest().toString())
        JunkFieldTest().printField()
    }
}
