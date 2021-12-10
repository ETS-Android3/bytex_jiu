package com.local.junk_code_plugin.method.m1;

import com.android.ddmlib.Log;
import com.local.junk_code_plugin.JunkCodeContext;

import java.util.Date;
import java.util.Objects;

/**
 * Created on 2021/9/1 16:15
 */
public class ASMTest1 {

    private Byte _a;
    private Short _b;
    private Integer _c;
    private Long _d;
    public Float _e;
    private Double _f;
    private Character _g;
    private Boolean _h;
    private String _i;
    private Object _j;

    public void printField() {
        if (_a != null) {
            Log.d("dd", "" + _a + _c);
        }

        System.out.println("test!!");
    }

    public void method1(String param) {
        long now = System.currentTimeMillis();
        if (System.currentTimeMillis() < now) {
            System.out.println("test!!");
        } else {
            System.out.println(param);
        }
    }

    private void method2() {
        if (getClass().isAnnotation())
            throw new RuntimeException();
    }

    private String method3() {
        if (System.currentTimeMillis() < 0)
            throw new RuntimeException();
        else {
            return null;
        }
    }


    private int method4() {
        if (System.currentTimeMillis() < 0)
            return 1;
        else {
            return 0;
        }
    }

    private long method5(byte a, short b) {
        return a + b;
    }

    private int method6(byte a, int b) {
        return a - b;
    }

    private String method7(String a, short b) {
        return a + b;
    }


    private long method8(Date a, int b) {
        return a.getTime() + b;
    }

    private void method9(JunkCodeContext context) {
        System.out.println(context.toString());
    }

    private JunkCodeContext method10(JunkCodeContext context) {
        if (context != null)
            System.out.println(context.toString());
        return context;
    }

    private JunkCodeContext method11(JunkCodeContext context, short b) {
        if (b > 0)
            context = null;
        return context;
    }

    private byte method12(JunkCodeContext context, byte b) {
        if (context == null)
            b = 0;
        return b;
    }

    private String method13(String context, String s) {
        if (context != null)
            s += context.toString();
        return s;
    }

    private JunkCodeContext method14(JunkCodeContext context, Object o) {
        try {
            o.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
            context = null;
        }
        return context;
    }

    private JunkCodeContext method15(JunkCodeContext context, JunkCodeContext context2) {
        if (context != null)
            return context;
        return context2;
    }

    private void method16(JunkCodeContext context, Boolean context2, Float context3, Object object) {
        System.out.println(getClass().getName());
    }

    private void m1() {
        method1("1");
        System.out.println("end");
    }

    private void m2() {
        if (getClass().getName().isEmpty()) {
            this.method2();
        }
        System.out.println("end");
    }

    private void m3() {
        if (System.currentTimeMillis() < 0) {
            method3();
        }
        System.out.println("end");
    }

    private void m4() {
        method4();
    }

    private void m5() {
        method5((byte) 100, (byte) 100);
        System.out.println("end");
    }

    private void m6() {
        if (this.getClass().isPrimitive())
            method6((byte) 100, (byte) 100);
        System.out.println("end");
    }

    private void m7() {
        if (this.getClass() == null) {
            method7("333", (short) 100);
        }
        System.out.println("end");
    }

    private void m8() {
        method8(new Date(), 100);
        System.out.println("end");
    }

    private void m9() {
        if (getClass().isEnum() && getClass().isArray()) {
            method9(null);
        }
        System.out.println("end");
    }

    private void m10() {
        if (getClass().getName().startsWith("_"))
            method10(null);
        System.out.println("end");
    }

    private void m11() {
        if (getClass().getSimpleName().endsWith("_"))
            method11(null, (short) 100);
        System.out.println("end");
    }

    private void m12() {
        if (Math.random() < 0) {
            method12(null, (byte) 0);
            System.out.println("end");
        }
    }

    private void m13() {
        if (Objects.isNull(getClass()))
            method13("2", "3");
        System.out.println("end");
    }

    private void m14() {
        if (getClass().getName().equals("_")) {
            method14(null, "");
        }
        System.out.println("end");
    }

    private void m15() {
        method15(null, null);
        System.out.println("end");
    }

    private void m16() {
        method16(null, null, null, null);
        System.out.println("end");
    }

}
