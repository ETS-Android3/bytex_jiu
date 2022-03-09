package com.ss.android.ugc.bytex.example.junk;

import android.animation.ValueAnimator;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import java.util.UUID;

public class JunkMethod5 {


    public void m1(Object a) {
        TextView tv = new TextView(null);
        tv.setAllCaps(true);
        tv.setCompoundDrawables(null, null, null, null);
        tv.setCursorVisible(false);
        tv.setEnabled(true);
        tv.setMaxLines(2);
        tv.setMinLines(1);
    }

    public void m2(Object a) {
        EditText et = new EditText(null);
        et.setSelection(0);
        et.selectAll();
        Editable text = et.getText();
        Selection.setSelection(text, 0);
    }

    public void m3(Object a) {
        RadioGroup  rg  = new RadioGroup(null);
        RadioButton rb  = new RadioButton(null);
        RadioButton rb2 = new RadioButton(null);
        RadioButton rb3 = new RadioButton(null);
        rg.addView(rb);
        rg.addView(rb2);
        rg.addView(rb3);
        rg.clearCheck();
    }

    public void m4(Object a) {
        ViewStub vs = new ViewStub(null);
        vs.setInflatedId(0);
        View view = vs.inflate();

        view.requestLayout();
        view.clearAnimation();
        view.clearFocus();
        view.callOnClick();
    }

    public void m5(Object a) {
        ScrollView   sc = new ScrollView(null);
        LinearLayout ll = new LinearLayout(null);
        ListView     lv = new ListView(null);
        ll.addView(lv);
        sc.addView(ll);
        sc.computeScroll();
        if (!sc.isFillViewport()) {
            sc.requestLayout();
        }
    }

    public void m6(Object a) {
        LinkedList<String> linkedList = new LinkedList<>();

        linkedList.add(a.toString());
        linkedList.add("m6666666");
        String first = linkedList.getFirst();
        String last  = linkedList.getLast();
        Log.d("m66666", first);
        Log.d("m66666", last);
        linkedList.clear();
    }

    public void m7(Object a) {
        if (a != null) {
            Toast toast = Toast.makeText(null, a.toString(), Toast.LENGTH_LONG);
            toast.show();
        } else {
            CharSequence m7777 = TextUtils.concat("m7777");
            Toast        toast = Toast.makeText(null, m7777, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void m8(Object a) {
        UUID uuid = UUID.fromString(a.toString());
        try {
            String encode = URLEncoder.encode(String.valueOf(uuid), "utf-8");
            Log.e("m888", encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            Log.e("m888", uuid.toString());
        }
    }

    public void m9(Object a) {
        Package aPackage            = Package.getPackage(a.toString());
        String  name                = aPackage.getName();
        String  implementationTitle = aPackage.getImplementationTitle();
        if (aPackage.isSealed()) {
            String implementationVersion = aPackage.getImplementationVersion();
            Log.d("m99999", implementationVersion);
        }
        Log.d("m9999", name + implementationTitle);
    }

    public void m10(Object a) {
        ValueAnimator valueAnimator = ValueAnimator.ofArgb(0, 1, 2);
        valueAnimator.setDuration(1000L);
        valueAnimator.setRepeatCount(4);
        valueAnimator.setStartDelay(1000);
        valueAnimator.setRepeatMode(0);
        valueAnimator.start();
        valueAnimator.end();
        valueAnimator.cancel();
    }

    public void m11(Object a) {
        Button       button = new Button(null);
        TextView     tv     = new TextView(null);
        EditText     et     = new EditText(null);
        ImageView    iv     = new ImageView(null);
        LinearLayout ll     = new LinearLayout(null);
        ll.addView(button);
        ll.addView(tv);
        ll.addView(et);
        ll.addView(iv);
    }

    public void m12(Object a) {
        Button button = new Button(null);
        button.setText(a.toString());
        TextView tv = new TextView(null);
        tv.setText("m1222222");
        EditText et = new EditText(null);
        et.setHint(a.toString());
        FrameLayout fl = new FrameLayout(null);
        fl.addView(button);
        fl.addView(tv);
        fl.addView(et);
    }

    public void m13(Object a) {
        GridView gv = new GridView(null);
        gv.setAdapter(null);
        gv.setColumnWidth(3);
        gv.setHorizontalSpacing(20);
        gv.setSelection(0);
        gv.setVerticalSpacing(10);
        gv.smoothScrollToPosition(0);
    }

    public void m14(Object a) {
        ProgressBar progressBar = new ProgressBar(null);
        progressBar.setIndeterminate(true);
        progressBar.setMax(100);
        progressBar.setProgress(10);
        progressBar.setIndeterminateDrawable(null);
        progressBar.setInterpolator(null);
        progressBar.postInvalidate();
    }

    public void m15(Object a) {
        RatingBar ratingBar = new RatingBar(null);
        ratingBar.setIsIndicator(true);
        ratingBar.setMax(99);
        ratingBar.setRating(30.0f);
        ratingBar.setNumStars(4);
        ratingBar.setStepSize(5.0f);
        ratingBar.requestLayout();
    }


    public void m16(Object a) {
        Calendar calendar = Calendar.getInstance();
        Date     date     = new Date();
        date.setTime(0);
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(1);
        calendar.setLenient(false);
        calendar.setTimeInMillis(0);
    }

    public void m17(Object a) {
        BigDecimal bigDecimal  = new BigDecimal(a.toString());
        BigDecimal bigDecimal2 = new BigDecimal(a.toString());
        BigDecimal bigDecimal3 = new BigDecimal(a.toString());
        BigDecimal bigDecimal4 = bigDecimal.abs();
        BigDecimal bigDecimal5 = bigDecimal2.divide(bigDecimal4, RoundingMode.HALF_UP);
        BigDecimal subtract    = bigDecimal3.subtract(bigDecimal5);
        Log.e("m177777", subtract.toString());
    }

    public void m18(Object a) {
        BigInteger b1  = new BigInteger(a.toString());
        BigInteger b2  = new BigInteger(a.toString());
        BigInteger b3  = new BigInteger(a.toString());
        BigInteger b4  = b1.abs();
        BigInteger add = b2.add(b3);
        BigInteger and = add.and(b4);
        Log.e("m188888", and.toString());
    }


    public void m19(Object a) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.toString());
        sb.append("m1999");
        sb.append(a.toString());
        sb.append(true);
        sb.append(0);
        sb.append(false);
        Log.e("m19999", sb.toString());
    }

    public void m20(Object a) {
        try {
            ClassLoader loader  = Thread.currentThread().getContextClassLoader();
            Class<?>    clazz   = loader.loadClass("dhvbjdfbvjdfbjb");
            Method[]    methods = clazz.getMethods();
            if (methods.length > 0) {
                Method       method      = methods[0];
                Annotation[] annotations = method.getDeclaredAnnotations();
                if (annotations.length > 0) {
                    Annotation annotation = annotations[0];
                    Log.e("m20000", annotation.toString());
                }
            }
        } catch (Exception e) {
            Log.e("m20000", e.getMessage());
        }
    }

    public void m21(Object a) {
        if (a != null) {
            Class<?>     aClass      = a.getClass();
            Annotation[] annotations = aClass.getAnnotations();
            if (annotations.length > 0) {
                Annotation                  annotation     = annotations[0];
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isArray()) {
                    Log.e("m2111111", annotationType.getName());
                }
            }
        }
    }


    public void m22(Object a) {
        WeakReference<String> reference = new WeakReference<>("m22222");
        boolean               enqueue   = reference.enqueue();
        if (enqueue) {
            reference.clear();
        } else {
            Log.e("m222222", reference.get());
        }
    }

    public void m23(Object a) {
        File file = new File(a.toString());
        if (file.exists()) {
            boolean delete = file.delete();
            if (!delete) {
                String absolutePath = file.getAbsolutePath();
                String name         = file.getName();
                System.out.print(absolutePath);
                System.out.print(name);
            }
        }
    }

    public void m24(Object a) {
        Object o = Objects.requireNonNull(a);
        if (o.getClass().isArray()) {
            System.out.print(o.toString());
        } else {
            String m24444 = Objects.toString(o, "m24444");
            Log.e("m244444", m24444);
        }
    }

}
