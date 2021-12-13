package com.local.junk_code_plugin;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;
import com.ss.android.ugc.bytex.common.utils.Utils;

import org.gradle.api.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created on 2021/8/30 16:18
 */
public class JunkCodeContext extends BaseContext<JunkCodeExtension> {

    private static final String[] defTypeArr = {"Ljava/lang/Integer;", "Ljava/lang/Float;", "Ljava/lang/Boolean;", "Ljava/lang/String;"};

    private final List<Pattern> whiteListPattern = new ArrayList<>();
    private final List<Pattern> onlyJunkPattern = new ArrayList<>();
    private final List<String> junkClassNameList = new ArrayList<>();
    private final List<Pattern> aRouterPattern = new ArrayList<>();//添加ARouter的父类
    private String prefix = null;//前缀
    private int fieldCount = 0;//属性数量
    private int methodCount = 0;//方法数量
    private int junkMethodCase = 0;//选择插入的方法

    public JunkCodeContext(Project project, AppExtension android, JunkCodeExtension extension) {
        super(project, android, extension);
    }

    @Override
    public synchronized void init() {
        super.init();
        whiteListPattern.clear();
        onlyJunkPattern.clear();
        aRouterPattern.clear();
        junkClassNameList.clear();

        final List<String> whiteList = extension.getWhiteList();
        for (String item : whiteList) {
            if (item == null || "".equals(item.trim())) {
                //ignore empty item
                continue;
            }
            whiteListPattern.add(Pattern.compile(Utils.convertToPatternString(item)));
        }
        final List<String> onlyCheckList = extension.getOnlyJunkClassList();
        for (String item : onlyCheckList) {
            if (item == null || "".equals(item.trim())) {
                //ignore empty item
                continue;
            }
            onlyJunkPattern.add(Pattern.compile(Utils.convertToPatternString(item)));
        }
        final List<String> pathARouterList = extension.getPathARouterList();
        for (String item : pathARouterList) {
            if (item == null || "".equals(item.trim())) {
                //ignore empty item
                continue;
            }
            aRouterPattern.add(Pattern.compile(Utils.convertToPatternString(item)));
        }

        junkClassNameList.addAll(extension.getJunkClassNameList());
        junkClassNameList.addAll(Arrays.asList(defTypeArr));

        prefix = extension.getPrefix();
        fieldCount = extension.getFieldCount();
        methodCount = extension.getMethodCount();
        junkMethodCase = extension.getJunkMethodCase();

        getLogger().i("init", "junkMethodCase:" + junkMethodCase);
        getLogger().i("init", "prefix:" + prefix);
        getLogger().i("init", "fieldCount:" + fieldCount);
        getLogger().i("init", "methodCount:" + methodCount);
        getLogger().i("init", "junkClassNameList:" + junkClassNameList);
        getLogger().i("init", "aRouterPattern:" + aRouterPattern);
        getLogger().i("init", "onlyCheckList:" + onlyJunkPattern);
        getLogger().i("init", "whiteList:" + whiteListPattern);
    }

    public void releaseContext() {
        super.releaseContext();
        whiteListPattern.clear();
        onlyJunkPattern.clear();
        junkClassNameList.clear();
        aRouterPattern.clear();
    }

    public int getJunkMethodCase() {
        return junkMethodCase;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getFieldCount() {
        return fieldCount;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public List<String> getJunkClassNameList() {
        return junkClassNameList;
    }

    public boolean needAddARouter(String className) {
        if (aRouterPattern.isEmpty()) {
            return false;
        }
        for (Pattern pattern : aRouterPattern) {
            if (pattern.matcher(className).matches()) {
                return true;
            }
        }
        return false;
    }

    public boolean needJunkClass(String className) {
        return this.needCheck(className);
    }

    private boolean needCheck(String param) {
        if (isInWhiteList(param)) {
            return false;
        }
        if (onlyJunkPattern.isEmpty()) {
            return false;
        }
        for (Pattern pattern : onlyJunkPattern) {
            if (pattern.matcher(param).matches()) {
                return true;
            }
        }
        return false;
    }


    private boolean isInWhiteList(String param) {
        for (Pattern pattern : whiteListPattern) {
            if (pattern.matcher(param).matches()) {
                return true;
            }
        }
        return false;
    }

}
