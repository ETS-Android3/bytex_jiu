package com.ss.android.ugc.bytex.example.junk;


import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JunkMethod3 {
    public void m1(Boolean a) {
        List<Object> list1 = new ArrayList<>();
        if (a.getClass().isArray()) {
            list1.add(a);
        }
        if (!list1.isEmpty()) {
            throw new RuntimeException();
        }
    }


    public void m2(Float a) {
        Handler handler = new Handler(Looper.getMainLooper());
        Message obtain = Message.obtain();
        obtain.obj = a;
        handler.sendMessage(obtain);
    }

    public void m3(Float a) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.removeCallbacksAndMessages(null);
        handler.sendEmptyMessageDelayed(0, 2000);
    }


    public void m4(Float a) {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        Log.d("11", model);
    }

    public void m5(Float a) {
        Log.d("11", Build.BOARD + Build.HARDWARE);
    }

    public void m6(Float a) {
        String brand = Build.BRAND;
        try {
            if (!TextUtils.isEmpty(brand)) {
                brand = brand.toLowerCase();
            }
        } catch (Throwable ignore) {/**/} finally {
            Log.d("11", brand);
        }
    }

    public void m7(Float a) {
        CheckBox cb = new CheckBox(null);
        if (cb.isChecked()) {
            cb.setTag(true);
        }
    }

    public void m8(Float a) {
        final CheckBox cb = new CheckBox(null);
        cb.setEnabled(false);
        cb.setFocusableInTouchMode(false);
        cb.setFocusable(false);
    }

    public void m9(Float a) {
        final ListView listView = new ListView(null);
        listView.setDividerHeight(20);
        listView.setSelection(listView.getSelectedItemPosition());
    }

    public void m10(Float a) {
        final ListView listView = new ListView(null);
        listView.setDivider(new ColorDrawable());
        listView.setAdapter(new ArrayAdapter<>(listView.getContext(), 0));
    }


    public void m11(Float a) {
        DatePicker datePicker = new DatePicker(null);
        datePicker.setMaxDate(Long.MAX_VALUE);
        datePicker.setMinDate(0);
        datePicker.setEnabled(true);
    }

    public void m12(Float a) {
        DatePicker datePicker = new DatePicker(null);
        datePicker.updateDate(0, 0, 0);
    }

    public void m13(Float a) {
        RadioGroup rg = new RadioGroup(null);
        RadioButton rb = new RadioButton(null);
        RadioButton rb2 = new RadioButton(null);
        rg.addView(rb);
        rg.addView(rb2);
    }

    public void m14(Float a) {
        final RadioButton rb = new RadioButton(null);
        rb.setId(0);
        rb.findFocus();
    }

    public void m15(Float a) {
        LinkedList<Object> list = new LinkedList<>();
        list.add(a);
        list.add(true);
        if (list.contains(false)) {
            list.clear();
        }
    }
}
