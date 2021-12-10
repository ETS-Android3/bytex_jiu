package com.local.junk_code_plugin;

import com.ss.android.ugc.bytex.common.BaseExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2021/8/30 16:14
 */
public class JunkCodeExtension extends BaseExtension {

    {
        enable(true);
        enableInDebug(true);
    }

    private List<String> onlyJunkClassList = new ArrayList<>();//需要添加垃圾代码的类

    private List<String> whiteList = new ArrayList<>();//白名单

    private List<String> pathARouterList = new ArrayList<>();//添加ARouter的父类

    private List<String> junkClassNameList = new ArrayList<>();//用于生成属性与方法的自定义类类名

    private String prefix = "";//前缀
    private int fieldCount = 0;//属性数量
    private int methodCount = 0;//方法数量


    public List<String> getOnlyJunkClassList() {
        return onlyJunkClassList;
    }

    public void setOnlyJunkClassList(List<String> onlyJunkClassList) {
        this.onlyJunkClassList = onlyJunkClassList;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }

    public List<String> getPathARouterList() {
        return pathARouterList;
    }

    public void setPathARouterList(List<String> pathARouterList) {
        this.pathARouterList = pathARouterList;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(int fieldCount) {
        this.fieldCount = fieldCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public void setMethodCount(int methodCount) {
        this.methodCount = methodCount;
    }

    public List<String> getJunkClassNameList() {
        return junkClassNameList;
    }

    public void setJunkClassNameList(List<String> junkClassNameList) {
        this.junkClassNameList = junkClassNameList;
    }

    @Override
    public String getName() {
        return "junk_code_plugin";
    }
}
