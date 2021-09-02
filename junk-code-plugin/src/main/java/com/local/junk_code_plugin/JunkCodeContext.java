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

    private static final String[] defTypeArr = {"Ljava/lang/Byte;", "Ljava/lang/Short;", "Ljava/lang/Integer;", "Ljava/lang/Long;", "Ljava/lang/Float;", "Ljava/lang/Double;", "Ljava/lang/Character;", "Ljava/lang/Boolean;", "Ljava/lang/String;", "Ljava/lang/Object;"};

    private final List<Pattern> whiteListPattern = new ArrayList<>();
    private final List<Pattern> onlyJunkPattern = new ArrayList<>();
    private final List<String> junkClassNameList = new ArrayList<>();
    private String prefix = null;//前缀
    private int fieldCount = 0;//属性数量
    private int methodCount = 0;//方法数量

    public JunkCodeContext(Project project, AppExtension android, JunkCodeExtension extension) {
        super(project, android, extension);
    }

    @Override
    public synchronized void init() {
        super.init();
        whiteListPattern.clear();
        onlyJunkPattern.clear();
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

        junkClassNameList.addAll(extension.getJunkClassNameList());
        junkClassNameList.addAll(Arrays.asList(defTypeArr));

        prefix = extension.getPrefix();
        fieldCount = extension.getFieldCount();
        methodCount = extension.getMethodCount();

        getLogger().d("init", "prefix:" + prefix);
        getLogger().d("init", "fieldCount:" + fieldCount);
        getLogger().d("init", "methodCount:" + methodCount);
        getLogger().d("init", "junkClassNameList:" + junkClassNameList);
        getLogger().d("init", "onlyCheckList:" + onlyJunkPattern);
        getLogger().d("init", "whiteList:" + whiteListPattern);
    }

    public void releaseContext() {
        super.releaseContext();
        whiteListPattern.clear();
        onlyJunkPattern.clear();
        junkClassNameList.clear();
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

    public boolean needJunkClass(String className) {
        return this.needCheck(className);
    }

    private boolean needCheck(String param) {
        if (isInWhiteList(param)) {
            return false;
        }
        if (onlyJunkPattern.isEmpty()) {
            return true;
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
