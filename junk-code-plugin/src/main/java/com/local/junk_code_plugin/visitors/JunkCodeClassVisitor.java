package com.local.junk_code_plugin.visitors;


import com.local.junk_code_plugin.JunkCodeContext;
import com.ss.android.ugc.bytex.common.Constants;
import com.ss.android.ugc.bytex.common.utils.TypeUtil;
import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;

/**
 * Created on 2021/9/1 15:03
 */
public class JunkCodeClassVisitor extends BaseClassVisitor {

    private static final Random random = new Random();
    private static final char[] abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final String arouter = "Lcom/alibaba/android/arouter/facade/annotation/Route;";

    private final JunkCodeContext context;
    private boolean skip = false;//是否跳过
    private boolean skipARouter = false;//是否跳过Arouter
    private String className = "";
    private String superName = "";
    private String initMethodName = "";
    private boolean fieldPrint = false;//是否打印了添加的属性

    private final List<GenerateMethodInfo> methodList = new ArrayList<>();
    private final List<GenerateFieldInfo> fieldList = new ArrayList<>();

    public JunkCodeClassVisitor(JunkCodeContext context) {
        this.context = context;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        int a = access & (ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE);
        skip = a == (ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE);
        className = name;
        this.superName = superName;
        if (skip) {
            context.getLogger().d("skipClass", "className:" + className);
        } else {
            if (context.getFieldCount() > 0) {
                generateFieldParam();
            }
            if (context.getMethodCount() > 0) {
                generateMethodParam();
                initMethodName = generateName(random.nextInt(20));
            }
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (descriptor.equals(arouter))
            skipARouter = true;
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (skip || name.equals(initMethodName) || TypeUtil.isStatic(access) || name.equals("<init>") || methodList.stream().anyMatch(it -> it.methodName.equals(name)))
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
        if (context.needAddARouter(superName) && !skipARouter) {
            addARouterPath();
        }
        if (context.getFieldCount() > 0) {
            addJunkField();
        }
        if (context.getMethodCount() > 0) {
            addJunkMethod();
            GenerateVMTool.invokeMethod(this, methodList, fieldList, className, initMethodName);
        }
        super.visitEnd();
    }

    private class JunkCodeMethodVisitor extends MethodVisitor {


        public JunkCodeMethodVisitor(MethodVisitor methodVisitor) {
            super(Constants.ASM_API, methodVisitor);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            if (!fieldPrint) {
                //调用全部方法
                mv.visitVarInsn(ALOAD, 0);
                mv.visitMethodInsn(INVOKESPECIAL, className, initMethodName, "()V", false);
                fieldPrint = true;
                GenerateVMTool.printField(mv, className, fieldList);
            }
        }
    }

    /**
     * 给Activity设置ARouter path
     */
    private void addARouterPath() {
        StringBuilder sb = new StringBuilder("/");
        int rI = random.nextInt(8);
        for (int i = 0; i < Math.max(4, rI); i++) {
            sb.append(abc[random.nextInt(abc.length)]);
        }
        sb.append("/");
        for (int i = 0; i < Math.max(4, rI); i++) {
            sb.append(abc[random.nextInt(abc.length)]);
        }
        AnnotationVisitor annotationVisitor0 = visitAnnotation(arouter, false);
        annotationVisitor0.visit("path", sb.toString());
        annotationVisitor0.visitEnd();
    }

    /**
     * field param
     */
    private void generateFieldParam() {
        for (int i = 0; i < context.getFieldCount(); i++) {
            GenerateFieldInfo info = new GenerateFieldInfo(generateName(i), getJunkClassName());
            fieldList.add(info);
        }
    }


    /**
     * method param
     */
    private void generateMethodParam() {
        for (int i = 0; i < context.getMethodCount(); i++) {
            int index = context.getMethodIndexMin() + random.nextInt(context.getMethodIndexMax() - context.getMethodIndexMin());
            GenerateMethodInfo method = new GenerateMethodInfo();
            method.setIndex(index);
            method.setMethodName(generateName(i));
            method.setAccess(GenerateVMTool.getMethodAccess(method.methodName));
            if (index % 2 == 0)
                method.returnType = getJunkClassName();
            else
                method.returnType = "V";
            int paramSize = Math.max(1, random.nextInt(5));
            List<String> paramList = new ArrayList<>();
            StringBuilder descriptor = new StringBuilder();
            for (int j = 0; j < paramSize; j++) {
                String junkClassName = getJunkClassName();
                paramList.add(junkClassName);
                descriptor.append(junkClassName);
            }
            method.descriptor = "(" + descriptor.toString() + ")" + method.returnType;
            method.setParamList(paramList);
            methodList.add(method);
        }

    }

    /**
     * 类添加属性
     */
    private void addJunkField() {
        FieldVisitor fieldVisitor;
        for (int i = 0; i < fieldList.size(); i++) {
            GenerateFieldInfo info = fieldList.get(i);
            fieldVisitor = visitField(ACC_PUBLIC, info.fieldName, info.junkClass, null, null);
            fieldVisitor.visitEnd();
        }
    }

    /**
     * 类添加方法
     */
    private void addJunkMethod() {
        for (int i = 0; i < methodList.size(); i++) {
            GenerateMethodInfo methodInfo = methodList.get(i);
            GenerateVMTool.generateMethod(this, methodInfo, className);
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
        for (int i = 0; i < Math.max(4, rI); i++) {
            sb.append(abc[random.nextInt(abc.length)]);
        }
        sb.append(index);
        for (int i = 0; i < Math.max(4, rI); i++) {
            sb.append(abc[random.nextInt(abc.length)]);
        }
        return sb.toString();
    }
}
