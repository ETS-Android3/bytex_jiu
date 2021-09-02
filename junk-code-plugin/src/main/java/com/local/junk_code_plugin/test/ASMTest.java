package com.local.junk_code_plugin.test;

import com.local.junk_code_plugin.JunkCodeContext;

import java.util.Date;

/**
 * Created on 2021/9/1 16:15
 */
public class ASMTest {

    private Byte _a;
    private Short _b;
    private Integer _c;
    private Long _d;
    private Float _e;
    private Double _f;
    private Character _g;
    private Boolean _h;
    private String _i;
    private Object _j;


    private void method1(String param) {
        long now = System.currentTimeMillis();
        if (System.currentTimeMillis() < now) {
            System.out.println("test!!");
        } else {
            System.out.println(param);
        }
    }

    private void method2() {
        throw new RuntimeException();
    }

    private String method3() {
        if (System.currentTimeMillis() > 0)
            throw new RuntimeException();
        else {
            return null;
        }
    }


    private int method4() {
        if (System.currentTimeMillis() > 0)
            return 1;
        else {
            return 0;
        }
    }

    private long method5(byte a, byte b) {
        return a + b;
    }

    private int method6(byte a, byte b) {
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

}
