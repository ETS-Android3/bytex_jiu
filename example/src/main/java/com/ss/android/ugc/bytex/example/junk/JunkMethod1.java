package com.ss.android.ugc.bytex.example.junk;

import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class JunkMethod1 {

    private String a;

    private void invoke() {
        if (a != null) {
            method1(null);
            method4(null);
            method9(null);
            method9(null);
            method9(null);
            method9(null);
            method16(null, null, null, null);
            method16(null, null, null, null);
            method16(null, null, null, null);
        }
    }


    public void method1(String param) {
        long now = System.currentTimeMillis();
        if (System.currentTimeMillis() < now) {
            System.out.println("test!!");
        } else {
            System.out.println("test!!" + now);
        }
    }

    private void method2() {

        invoke();


        if (getClass().isAnnotation())
            throw new RuntimeException();
    }

    private void method3() {
        if (System.currentTimeMillis() < 0)
            throw new RuntimeException();
    }


    private void method4(Object o) {
        List<String> list = new ArrayList<>();
        list.add("m4");
        list.add(o.toString());
        System.out.print(list);
    }

    private void method5() {
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt();
        if (i + j > 10) {
            random.nextInt();
        }
    }

    private void method6() {
        Random random = new Random();
        int i = random.nextInt(10);
        int j = random.nextInt();
        if (random.nextInt() < (i + j)) {
            notify();
        }
    }

    private void method7() {
        BigDecimal bigDecimal = new BigDecimal(0);
        int i = bigDecimal.intValue();
        String format = String.format("123", i);
        try {
            getClass().getField(format);
        } catch (NoSuchFieldException e) {

        }
    }


    private void method8() {
        Date date = new Date();
        if (date.getTime() > 0) {
            date.setTime(date.getTime() + date.getTime());
        }
    }

    private void method9(Object context) {
        System.out.println(context.toString());
    }

    private void method10(Object context) {
        if (context != null)
            System.out.println(context.toString());
    }

    private void method11(Object context) {
        if (Calendar.getInstance().isLenient())
            context = null;
    }

    private void method12(Object context) {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        if (time.getTime() > new Date(0).getTime()) {
            instance.clear();
        }
    }

    private void method13(Object context) {
        String s = null;
        if (context != null)
            s += context.toString();
        Log.e("aaa", s);
    }

    private void method14(Object o) {
        try {
            o.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void method15(Object context) {
        if (context == null) {
            throw new NullPointerException();
        }
    }

    private String method16(String context, Boolean context2, Float context3, Object object) {
        System.out.println(getClass().getName());
        return null;
    }

    private String method17(String context, Boolean context2, Float context3, Object object,BigDecimal d) {
        System.out.println(getClass().getName());
        return null;
    }
}
