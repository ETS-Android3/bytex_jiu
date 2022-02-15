package com.ss.android.ugc.bytex.example.junk;

import android.graphics.Rect;
import android.os.Build;
import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;

public class JunkMethod4 {

    public void m1(Object a) {
        double random  = Math.random();
        double random2 = Math.random();
        double max     = Math.max(random, random2);
        Log.d("math", ":" + max);
    }

    public void m2(Object a) {
        String format = NumberFormat.getInstance().format(Math.random());
        if (format != null) {
            TextView tv = new TextView(null);
            tv.setText(format);
        }
    }

    public void m3(Object a) {
        RoundingMode roundingMode = NumberFormat.getInstance().getRoundingMode();
        BigDecimal   divide       = new BigDecimal(a.toString()).divide(new BigDecimal(2), roundingMode);
        System.out.print(divide);
    }

    public void m4(Object a) {
        Timer timer = new Timer();
        timer.schedule(null, 0, 0);
    }

    public void m5(Object a) {
        SparseArray<String> array = new SparseArray<>();
        array.put(0, a.toString());
        array.put(1, "eee");
    }

    public void m6(Object a) {
        ArrayMap<String, String> map = new ArrayMap<>();
        map.put("aaa", a.toString());
    }

    public void m7(Object a) {
        byte[] decode = Base64.decode(a.toString(), 0);
        Log.i("ddd", Arrays.toString(decode));
    }

    public void m8(Object a) {
        List<Pair<String, String>> list = new ArrayList<>();
        list.add(new Pair<String, String>(a.toString(), a.toString()));
        list.add(new Pair<String, String>("www", a.toString()));
        list.add(new Pair<String, String>("www" + a.toString(), a.toString()));
        list.add(new Pair<String, String>(a.toString() + "www", a.toString()));
    }

    public void m9(Object a) {
        View inflate = ViewStub.inflate(null, 0, null);
        inflate.animate();
        inflate.callOnClick();
        inflate.clearFocus();
        inflate.clearAnimation();
    }

    public void m10(Object a) {
        LayoutInflater.Factory factory = LayoutInflater.from(null).getFactory();
        View                   view    = factory.onCreateView("ddd", null, null);
        view.requestLayout();
    }

    public void m11(Object a) {
        WebView webView = new WebView(null);
        webView.setWebViewClient(null);
        webView.setWebChromeClient(null);
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    public void m12(Object a) {
        Log.w("ddd", Build.BRAND + Build.DEVICE + Build.MODEL);
        System.out.print(Build.MANUFACTURER);
        System.out.print(a.toString());
    }

    public void m13(Object a) {
        ImageView iv = new ImageView(null);
        iv.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).bottomMargin = 0;
        }
    }

    public void m14(Object a) {
        TextView  textView = new TextView(null);
        ImageView iv       = new ImageView(null);
        textView.setText(a.toString());
        iv.setImageResource(0);
        LinearLayout linearLayout = new LinearLayout(null);
        linearLayout.addView(textView);
        linearLayout.addView(iv);
    }

    public void m15(Object a) {
        View          view     = new View(null);
        Rect          rect     = new Rect();
        TouchDelegate delegate = new TouchDelegate(rect, view);
        delegate.onTouchEvent(MotionEvent.obtain(0, 0, 0, 0, 0, 0));
    }
}
