package com.local.junk_code_plugin.visitors;

import java.util.Objects;

/**
 * Created on 2021/11/10 19:45
 */
public class GenerateMethodInfo {
    public int index;
    public String methodName;
    public String junkClass;
    public String integerType;

    public String descriptor;
    public int paramSize;

    public GenerateMethodInfo(int index, String methodName, String junkClass, String integerType) {
        this.index = index;
        this.methodName = methodName;
        this.junkClass = junkClass;
        this.integerType = integerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerateMethodInfo that = (GenerateMethodInfo) o;
        return methodName.equals(that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodName);
    }
}
