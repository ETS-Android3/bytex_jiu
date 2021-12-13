package com.local.junk_code_plugin.method;

import java.util.List;

public class GenerateMethod {
    public int index;
    public String methodName;
    public String returnType;
    public int access;
    public List<String> paramList;
    public String descriptor;

    public GenerateMethod(int index, String methodName, String returnType, int access, List<String> paramList) {
        this.index = index;
        this.methodName = methodName;
        this.returnType = returnType;
        this.access = access;
        this.paramList = paramList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }
}
