package com.local.junk_code_plugin.visitors;

/**
 * Created on 2021/11/10 19:45
 */
public class GenerateMethodInfo {
    public int index;
    public String methodName;
    public String junkClass;
    public String integerType;

    public GenerateMethodInfo(int index, String methodName, String junkClass, String integerType) {
        this.index = index;
        this.methodName = methodName;
        this.junkClass = junkClass;
        this.integerType = integerType;
    }
}
