package com.local.junk_code_plugin.visitors;


import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

import com.local.junk_code_plugin.JunkCodeContext;
import com.ss.android.ugc.bytex.common.Constants;
import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created on 2021/9/1 15:03
 */
public class JunkCodeClassVisitor extends BaseClassVisitor {

    private static final Random random = new Random();
    private static final Pattern p = Pattern.compile(".*\\d+.*");
    private static final char[] abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private final JunkCodeContext context;
    private boolean skip = false;//是否跳过
    private String className = "";

    private final List<GenerateMethodInfo> methodList = new ArrayList<>();

    public JunkCodeClassVisitor(JunkCodeContext context) {
        this.context = context;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        int a = access & (ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE);
        skip = a == (ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE);
        className = name;
        if (skip) {
            context.getLogger().d("skipClass", "className:" + className);
        } else if (context.getMethodCount() > 0) {
            generateMethodParam();
        }
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (skip || ((access & ACC_STATIC) != 0) || name.equals("<init>") || p.matcher(name).matches())
            return methodVisitor;
        else {
//            return methodVisitor;
            return new JunkCodeMethodVisitor(methodVisitor);
        }
    }

    @Override
    public void visitEnd() {
        if (skip) {
            super.visitEnd();
            return;
        }
        if (context.getFieldCount() > 0) {
            addJunkField();
        }
        if (context.getMethodCount() > 0) {
            addJunkMethod();
        }
        super.visitEnd();
    }

    private class JunkCodeMethodVisitor extends MethodVisitor {


        public JunkCodeMethodVisitor(MethodVisitor methodVisitor) {
            super(Constants.ASM_API, methodVisitor);
        }

        @Override
        public void visitCode() {
            GenerateMethodInfo methodInfo = methodList.get(random.nextInt(methodList.size()));
            GenerateVMTool.junkCodeMethod(mv, methodInfo, className);
            super.visitCode();
        }
    }


    /**
     * method param
     */
    private void generateMethodParam() {
        for (int i = 0; i < context.getMethodCount(); i++) {
            int index = random.nextInt(16);
            String methodName = generateName(i);
            String junkClassName = getJunkClassName();
            GenerateMethodInfo methodInfo = new GenerateMethodInfo(index, methodName, junkClassName, GenerateVMTool.getIntegerType());
            if (index > 14) {
                int paramSize = Math.max(1, random.nextInt(5));
                StringBuilder descriptor = new StringBuilder();
                for (int j = 0; j < paramSize; j++) {
                    descriptor.append(context.getJunkClassNameList().get(random.nextInt(context.getJunkClassNameList().size())));
                }
                methodInfo.paramSize = paramSize;
                methodInfo.descriptor = "(" + descriptor.toString() + ")V";
            }
            methodList.add(methodInfo);
        }
    }

    /**
     * 类添加属性
     */
    private void addJunkField() {
        List<String> junkClassNameList = context.getJunkClassNameList();
        FieldVisitor fieldVisitor;
        for (int i = 0; i < context.getFieldCount(); i++) {
            fieldVisitor = visitField(i % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, generateName(i), junkClassNameList.get(random.nextInt(junkClassNameList.size())), null, null);
            fieldVisitor.visitEnd();
        }
    }

    /**
     * 类添加方法
     */
    private void addJunkMethod() {
        for (int i = 0; i < methodList.size(); i++) {
            GenerateMethodInfo methodInfo = methodList.get(i);
            int index = methodInfo.index;
            String methodName = methodInfo.methodName;
            String junkClassName = methodInfo.junkClass;
            String integerType = methodInfo.integerType;
            if (index == 0) {
                GenerateVMTool.method1(this, methodName);
            } else if (index == 1) {
                GenerateVMTool.method2(this, methodName);
            } else if (index == 2) {
                GenerateVMTool.method3(this, methodName);
            } else if (index == 3) {
                GenerateVMTool.method4(this, methodName);
            } else if (index == 4) {
                GenerateVMTool.method5(this, methodName, integerType);
            } else if (index == 5) {
                GenerateVMTool.method6(this, methodName, integerType);
            } else if (index == 6) {
                GenerateVMTool.method7(this, methodName, integerType);
            } else if (index == 7) {
                GenerateVMTool.method8(this, methodName, integerType);
            } else if (index == 8) {
                GenerateVMTool.method9(this, methodName, junkClassName);
            } else if (index == 9) {
                GenerateVMTool.method10(this, methodName, junkClassName);
            } else if (index == 10) {
                GenerateVMTool.method11(this, methodName, junkClassName, integerType);
            } else if (index == 11) {
                GenerateVMTool.method12(this, methodName, junkClassName, integerType);
            } else if (index == 12) {
                GenerateVMTool.method13(this, methodName, junkClassName);
            } else if (index == 13) {
                GenerateVMTool.method14(this, methodName, junkClassName);
            } else if (index == 14) {
                GenerateVMTool.method15(this, methodName, junkClassName);
            } else {
                GenerateVMTool.method16(this, methodName, methodInfo.descriptor);
            }
        }
    }

    private String getJunkClassName() {
        return context.getJunkClassNameList().get(random.nextInt(context.getJunkClassNameList().size()));
    }


    /**
     * 生成名称
     */
    static String generateName(int index) {
        StringBuilder sb = new StringBuilder();
        int rI = random.nextInt(8);
        for (int i = 0; i < 4 + rI; i++) {
            sb.append(abc[random.nextInt(abc.length)]);
        }
        sb.append(index);
        return sb.toString();
    }
}
