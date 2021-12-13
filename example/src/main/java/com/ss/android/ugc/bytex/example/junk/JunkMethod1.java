package com.ss.android.ugc.bytex.example.junk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class JunkMethod1 {

    public String m1() {
        if (Math.random() < 0) {
            File file = new File("");
            if (file.exists()) {
                file.delete();
            }
        }
        return null;
    }

    public void m11() {
        if (Math.random() < 0) {
            File file = new File("");
            if (file.exists()) {
                file.delete();
            }
        }
    }


    public void m2() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(i);
        }
        Log.e("m2", list.toString());
    }

    public void m3(Object a, TextView b) {
        if (TextUtils.isEmpty(getClass().toString())) {
            try {
                View view = new View(null);
                view.animate();
            } catch (Exception e) {

            }
        }
    }

    public void m4(Object a) {
        new AlertDialog.Builder(null)
                .setMessage("123")
                .create().show();
    }

    public void m5(Object a) {
        Toast.makeText(null, "123", Toast.LENGTH_SHORT).show();
    }

    public void m6(Object a) {
        while (true) {
            String format = DecimalFormat.getInstance().format(Math.random());
            if (format.contains("0")) {
                break;
            }
        }
    }

    public void m7(Object a) {
        ImageView iv = new ImageView(null);
        iv.setImageAlpha(1);
        iv.setImageLevel(0);
        iv.setMaxHeight(200);
    }


    public void m8(Object a) {
        ImageView iv = new ImageView(null);
        iv.setImageResource(0);
        boolean adjustViewBounds = iv.getAdjustViewBounds();
        if (adjustViewBounds) {
            iv.setVisibility(View.VISIBLE);
        }
    }

    public void m9(Object a) {
        TextView tv = null;
        TextView tv2 = null;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                tv = new TextView(null);
            } else {
                tv2 = new TextView(null);
            }
        }

        tv.setText("m9");
        tv2.setText(null);
    }

    public void m10(Object a) {
        TextView tv = new TextView(null);
        TextView tv2 = null;
        for (int i = 0; i < BigDecimal.ROUND_HALF_DOWN; i++) {
            if (tv2 == null) {
                tv2 = new TextView(null, null);
            }
        }
        tv.setText(tv2.getText());
    }

    public void m11(Object a) {
        final Button btn = new Button(null);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setText("m10");
            }
        });
    }

    public void m12(Object a) {
        final Button btn = new Button(null);
        btn.setPadding(0, 1, 2, 3);
        btn.setRotation(60);
        btn.setBackgroundColor(Color.WHITE);
    }


    public void m13(Object a) {
        String m13 = new SpannableStringBuilder().append("m13").toString();
        if (m13.length() > 3) {
            m13 = m13.substring(3);
        }
        Log.d("m13", m13);
    }

    public void m14(Object a) {
        Notification notification = new Notification.Builder(null)
                .setContentTitle("m14")
                .setContentText("m14")
                .build();
        notification.describeContents();
    }

    public void m15(Object a) {
        Intent intent = new Intent();
        intent.putExtra("m15", "m15");
        intent.setAction("com.android.system");
        intent.setPackage("com.android.system");
    }
}
