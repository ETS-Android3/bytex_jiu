package com.local.junk_code_plugin.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.List;
import java.util.Random;

import static org.objectweb.asm.Opcodes.AASTORE;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ANEWARRAY;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.DCMPG;
import static org.objectweb.asm.Opcodes.DCMPL;
import static org.objectweb.asm.Opcodes.DCONST_0;
import static org.objectweb.asm.Opcodes.DLOAD;
import static org.objectweb.asm.Opcodes.DSTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.FCONST_0;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.ICONST_4;
import static org.objectweb.asm.Opcodes.ICONST_5;
import static org.objectweb.asm.Opcodes.ICONST_M1;
import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.IFGE;
import static org.objectweb.asm.Opcodes.IFLE;
import static org.objectweb.asm.Opcodes.IFNE;
import static org.objectweb.asm.Opcodes.IFNONNULL;
import static org.objectweb.asm.Opcodes.IFNULL;
import static org.objectweb.asm.Opcodes.IF_ICMPGE;
import static org.objectweb.asm.Opcodes.IF_ICMPLE;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INSTANCEOF;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.LADD;
import static org.objectweb.asm.Opcodes.LCMP;
import static org.objectweb.asm.Opcodes.LCONST_0;
import static org.objectweb.asm.Opcodes.LLOAD;
import static org.objectweb.asm.Opcodes.LSTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.PUTFIELD;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.SIPUSH;

/**
 * Created on 2021/9/1 18:50
 * VisitorMethod生成工具
 * {@link com.ss.android.ugc.bytex.example.junk}
 */
public class GenerateVMTool {

    private static final String[] integerArr = {"B", "S", "I"};
    private static final Random random = new Random();

    public static String getIntegerType() {
        return integerArr[random.nextInt(integerArr.length)];
    }

    public static int getMethodAccess(String methodName) {
        return methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC;
    }

    public static int getMethodInvoke(int access) {
        return access == ACC_PRIVATE ? INVOKESPECIAL : INVOKEVIRTUAL;
    }

//    public static int getMethodOpcode(String methodName) {
//        return methodName.length() % 2 == 0 ? INVOKESPECIAL : INVOKEVIRTUAL;
//    }

    static void printField(MethodVisitor methodVisitor, String className, List<GenerateFieldInfo> fieldList) {
        if (fieldList.isEmpty()) return;
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, className, fieldList.get(0).fieldName, fieldList.get(0).junkClass);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitLdcInsn(fieldList.get(0).fieldName);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitLdcInsn("");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);

        for (int i = 0; i < fieldList.size(); i++) {
            GenerateFieldInfo info = fieldList.get(i);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, className, info.fieldName, info.junkClass);
            if (info.junkClass.equals("Ljava/lang/String;"))
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            else
                methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
        }

        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label0);

    }

    static void invokeMethod(ClassVisitor classVisitor, List<GenerateMethodInfo> methodList, List<GenerateFieldInfo> fieldList, String className, String methodName) {
        GenerateFieldInfo fieldInfo     = fieldList.get(0);
        MethodVisitor     methodVisitor = classVisitor.visitMethod(ACC_PRIVATE, methodName, "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, className, fieldInfo.fieldName, fieldInfo.junkClass);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        int maxStack = 1;
        for (int i = 0; i < methodList.size(); i++) {
            GenerateMethodInfo methodInfo = methodList.get(i);
            maxStack = Math.max(1, methodInfo.paramList.size());
            methodVisitor.visitVarInsn(ALOAD, 0);
            for (int j = 0; j < methodInfo.paramList.size(); j++) {
                methodVisitor.visitInsn(ACONST_NULL);
            }
            methodVisitor.visitMethodInsn(getMethodInvoke(methodInfo.access), className, methodInfo.methodName, methodInfo.descriptor, false);
            if (!methodInfo.returnType.equals("V")) {
                methodVisitor.visitInsn(POP);
            }
        }
        methodVisitor.visitLabel(label0);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(maxStack + 1, 1);
        methodVisitor.visitEnd();
    }

    static void invokeMethod2(ClassVisitor classVisitor, List<GenerateMethodInfo> methodList, List<GenerateFieldInfo> fieldList, String className, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(ACC_PRIVATE, methodName, "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
        methodVisitor.visitInsn(DCONST_0);
        methodVisitor.visitInsn(DCMPL);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFLE, label0);

        for (int i = 0; i < fieldList.size(); i++) {
            GenerateFieldInfo fieldInfo = fieldList.get(i);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, className, fieldInfo.fieldName, fieldInfo.junkClass);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitVarInsn(ASTORE, i + 1);
        }

        methodVisitor.visitLdcInsn(methodName);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);

        for (int i = 0; i < fieldList.size(); i++) {
            methodVisitor.visitVarInsn(ALOAD, i + 1);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        }

        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        int maxStack = 1;
        for (int i = 0; i < methodList.size(); i++) {
            GenerateMethodInfo methodInfo = methodList.get(i);
            maxStack = Math.max(1, methodInfo.paramList.size());
            methodVisitor.visitVarInsn(ALOAD, 0);
            for (int j = 0; j < methodInfo.paramList.size(); j++) {
                methodVisitor.visitInsn(ACONST_NULL);
            }
            methodVisitor.visitMethodInsn(getMethodInvoke(methodInfo.access), className, methodInfo.methodName, methodInfo.descriptor, false);
            if (!methodInfo.returnType.equals("V")) {
                methodVisitor.visitInsn(POP);
            }
        }
        methodVisitor.visitLabel(label0);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(maxStack + 1, fieldList.size() + 1);
        methodVisitor.visitEnd();
    }

    static void generateMethod(ClassVisitor classVisitor, GenerateMethodInfo info, String className) {
        int index = info.index;
        switch (index) {
            case 0:
                m1(classVisitor, info, className);
                break;
            case 1:
                m2(classVisitor, info, className);
                break;
            case 2:
                m3(classVisitor, info, className);
                break;
            case 3:
                m4(classVisitor, info, className);
                break;
            case 4:
                m5(classVisitor, info, className);
                break;
            case 5:
                m6(classVisitor, info, className);
                break;
            case 6:
                m7(classVisitor, info, className);
                break;
            case 7:
                m8(classVisitor, info, className);
                break;
            case 8:
                m9(classVisitor, info, className);
                break;
            case 9:
                m10(classVisitor, info, className);
                break;
            case 10:
                m11(classVisitor, info, className);
                break;
            case 11:
                m12(classVisitor, info, className);
                break;
            case 12:
                m13(classVisitor, info, className);
                break;
            case 13:
                m14(classVisitor, info, className);
                break;
            case 14:
                m15(classVisitor, info, className);
                break;
            case 15:
                m16(classVisitor, info, className);
                break;
            case 16:
                m17(classVisitor, info, className);
                break;
            case 17:
                m18(classVisitor, info, className);
                break;
            case 18:
                m19(classVisitor, info, className);
                break;
            case 19:
                m20(classVisitor, info, className);
                break;
            case 20:
                m21(classVisitor, info, className);
                break;
            case 21:
                m22(classVisitor, info, className);
                break;
            case 22:
                m23(classVisitor, info, className);
                break;
            case 23:
                m24(classVisitor, info, className);
                break;
            case 24:
                m25(classVisitor, info, className);
                break;
            case 25:
                m26(classVisitor, info, className);
                break;
            case 26:
                m27(classVisitor, info, className);
                break;
            case 27:
                m28(classVisitor, info, className);
                break;
            case 28:
                m29(classVisitor, info, className);
                break;
            case 29:
                m30(classVisitor, info, className);
                break;
            case 30:
                m31(classVisitor, info, className);
                break;
            case 31:
                m32(classVisitor, info, className);
                break;
            case 32:
                m33(classVisitor, info, className);
                break;
            case 33:
                m34(classVisitor, info, className);
                break;
            case 34:
                m35(classVisitor, info, className);
                break;
            case 35:
                m36(classVisitor, info, className);
                break;
            case 36:
                m37(classVisitor, info, className);
                break;
            case 37:
                m38(classVisitor, info, className);
                break;
            case 38:
                m39(classVisitor, info, className);
                break;
            case 39:
                m40(classVisitor, info, className);
                break;
            case 40:
                m41(classVisitor, info, className);
                break;
            case 41:
                m42(classVisitor, info, className);
                break;
            case 42:
                m43(classVisitor, info, className);
                break;
            case 43:
                m44(classVisitor, info, className);
                break;
            case 44:
                m45(classVisitor, info, className);
                break;
            case 45:
                m46(classVisitor, info, className);
                break;
            case 46:
                m47(classVisitor, info, className);
                break;
            case 47:
                m48(classVisitor, info, className);
                break;
            case 48:
                m49(classVisitor, info, className);
                break;
            case 49:
                m50(classVisitor, info, className);
                break;
            case 50:
                m51(classVisitor, info, className);
                break;
            case 51:
                m52(classVisitor, info, className);
                break;
            case 52:
                m53(classVisitor, info, className);
                break;
            case 53:
                m54(classVisitor, info, className);
                break;
            case 54:
                m55(classVisitor, info, className);
                break;
            case 55:
                m56(classVisitor, info, className);
                break;
            case 56:
                m57(classVisitor, info, className);
                break;
            case 57:
                m58(classVisitor, info, className);
                break;
            case 58:
                m59(classVisitor, info, className);
                break;
            case 59:
                m60(classVisitor, info, className);
                break;
            case 60:
                m61(classVisitor, info, className);
                break;
        }
    }

    static void generateMethodReturn(MethodVisitor methodVisitor, GenerateMethodInfo info) {
        if (info.returnType.equals("V")) {
            methodVisitor.visitInsn(RETURN);
        } else {
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitInsn(ARETURN);
        }
    }

    static void m1(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitVarInsn(LSTORE, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitVarInsn(LLOAD, 2);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFGE, label0);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label1);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitVarInsn(LLOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitLabel(label1);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 4);
        methodVisitor.visitEnd();
    }

    static void m2(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "isAnnotation", "()Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "()V", false);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitEnd();
    }

    static void m3(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFGE, label0);
        methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "()V", false);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 1);
        methodVisitor.visitEnd();
    }

    static void m4(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/Object;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void m5(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/Random");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Random", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitIntInsn(BIPUSH, 10);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextInt", "(I)I", false);
        methodVisitor.visitVarInsn(ISTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextInt", "()I", false);
        methodVisitor.visitVarInsn(ISTORE, 3);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitVarInsn(ILOAD, 3);
        methodVisitor.visitInsn(IADD);
        methodVisitor.visitIntInsn(BIPUSH, 10);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPLE, label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextInt", "()I", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 4);
        methodVisitor.visitEnd();
    }

    static void m6(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/Random");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Random", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitIntInsn(BIPUSH, 10);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextInt", "(I)I", false);
        methodVisitor.visitVarInsn(ISTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextInt", "()I", false);
        methodVisitor.visitVarInsn(ISTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Random", "nextInt", "()I", false);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitVarInsn(ILOAD, 3);
        methodVisitor.visitInsn(IADD);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGE, label0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "notify", "()V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();
    }

    static void m7(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/NoSuchFieldException");
        methodVisitor.visitTypeInsn(NEW, "java/math/BigDecimal");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(I)V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/math/BigDecimal", "intValue", "()I", false);
        methodVisitor.visitVarInsn(ISTORE, 2);
        methodVisitor.visitLdcInsn("123");
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/Object");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        methodVisitor.visitInsn(AASTORE);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/String", "format", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label1);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitLabel(label3);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(5, 5);
        methodVisitor.visitEnd();
    }

    static void m8(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/Date");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Date", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFLE, label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        methodVisitor.visitInsn(LADD);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "setTime", "(J)V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(5, 2);
        methodVisitor.visitEnd();
    }

    static void m9(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

    static void m10(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

    static void m11(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Calendar", "getInstance", "()Ljava/util/Calendar;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Calendar", "isLenient", "()Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(1, 2);
        methodVisitor.visitEnd();
    }

    static void m12(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Calendar", "getInstance", "()Ljava/util/Calendar;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Calendar", "getTime", "()Ljava/util/Date;", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        methodVisitor.visitTypeInsn(NEW, "java/util/Date");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Date", "<init>", "(J)V", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFLE, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Calendar", "clear", "()V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(6, 4);
        methodVisitor.visitEnd();
    }

    static void m13(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLdcInsn("aaa");
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void m14(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/InterruptedException");
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "wait", "()V", false);
        methodVisitor.visitLabel(label1);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/InterruptedException", "printStackTrace", "()V", false);
        methodVisitor.visitLabel(label3);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(1, 3);
        methodVisitor.visitEnd();
    }

    static void m15(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label0);
        methodVisitor.visitTypeInsn(NEW, "java/lang/NullPointerException");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/NullPointerException", "<init>", "()V", false);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

    static void m16(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 5);
        methodVisitor.visitEnd();
    }


    static void m17(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
        methodVisitor.visitInsn(DCONST_0);
        methodVisitor.visitInsn(DCMPG);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFGE, label0);
        methodVisitor.visitTypeInsn(NEW, "java/io/File");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/io/File", "<init>", "(Ljava/lang/String;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "exists", "()Z", false);
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "delete", "()Z", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 2);
        methodVisitor.visitEnd();
    }

    static void m18(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ISTORE, 2);
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(ICONST_4);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGE, label1);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitIincInsn(2, 1);
        methodVisitor.visitJumpInsn(GOTO, label0);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "e", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }


    static void m19(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Exception");
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/text/TextUtils", "isEmpty", "(Ljava/lang/CharSequence;)Z", false);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label3);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitTypeInsn(NEW, "android/view/View");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/view/View", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "animate", "()Landroid/view/ViewPropertyAnimator;", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitLabel(label3);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();
    }

    static void m20(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/app/AlertDialog$Builder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/app/AlertDialog$Builder", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/AlertDialog$Builder", "setMessage", "(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/AlertDialog$Builder", "create", "()Landroid/app/AlertDialog;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/AlertDialog", "show", "()V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 2);
        methodVisitor.visitEnd();
    }

    static void m21(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/widget/Toast", "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Toast", "show", "()V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 2);
        methodVisitor.visitEnd();
    }

    static void m22(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/text/DecimalFormat", "getInstance", "()Ljava/text/NumberFormat;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/text/NumberFormat", "format", "(D)Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn("0");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "contains", "(Ljava/lang/CharSequence;)Z", false);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label1);
        Label label2 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label2);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitJumpInsn(GOTO, label0);
        methodVisitor.visitLabel(label2);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m23(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/ImageView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ImageView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageAlpha", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageLevel", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitIntInsn(SIPUSH, 200);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setMaxHeight", "(I)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m24(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/ImageView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ImageView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageResource", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "getAdjustViewBounds", "()Z", false);
        methodVisitor.visitVarInsn(ISTORE, 3);
        methodVisitor.visitVarInsn(ILOAD, 3);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setVisibility", "(I)V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();
    }

    static void m25(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ISTORE, 4);
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ILOAD, 4);
        methodVisitor.visitInsn(ICONST_2);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGE, label1);
        methodVisitor.visitVarInsn(ILOAD, 4);
        Label label2 = new Label();
        methodVisitor.visitJumpInsn(IFNE, label2);
        methodVisitor.visitTypeInsn(NEW, "android/widget/TextView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitTypeInsn(NEW, "android/widget/TextView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitIincInsn(4, 1);
        methodVisitor.visitJumpInsn(GOTO, label0);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "setText", "(Ljava/lang/CharSequence;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "setText", "(Ljava/lang/CharSequence;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 5);
        methodVisitor.visitEnd();
    }

    static void m26(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/TextView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ISTORE, 4);
        Label label0 = new Label();
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ILOAD, 4);
        methodVisitor.visitInsn(ICONST_5);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGE, label1);
        methodVisitor.visitVarInsn(ALOAD, 3);
        Label label2 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label2);
        methodVisitor.visitTypeInsn(NEW, "android/widget/TextView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitIincInsn(4, 1);
        methodVisitor.visitJumpInsn(GOTO, label0);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "getText", "()Ljava/lang/CharSequence;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "setText", "(Ljava/lang/CharSequence;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 5);
        methodVisitor.visitEnd();
    }

    static void m27(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/Button");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/Button", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Button", "hasFocus", "()Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Button", "setText", "(Ljava/lang/CharSequence;)V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m28(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/Button");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/Button", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(ICONST_2);
        methodVisitor.visitInsn(ICONST_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Button", "setPadding", "(IIII)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_M1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/Button", "setBackgroundColor", "(I)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(5, 3);
        methodVisitor.visitEnd();
    }

    static void m29(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/text/SpannableStringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/text/SpannableStringBuilder", "<init>", "()V", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/text/SpannableStringBuilder", "append", "(Ljava/lang/CharSequence;)Landroid/text/SpannableStringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/text/SpannableStringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "length", "()I", false);
        methodVisitor.visitInsn(ICONST_3);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPLE, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "substring", "(I)Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void m30(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/app/Notification$Builder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/app/Notification$Builder", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/Notification$Builder", "setContentTitle", "(Ljava/lang/CharSequence;)Landroid/app/Notification$Builder;", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/Notification$Builder", "setContentText", "(Ljava/lang/CharSequence;)Landroid/app/Notification$Builder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/Notification$Builder", "build", "()Landroid/app/Notification;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/app/Notification", "describeContents", "()I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m31(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/content/Intent");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/content/Intent", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/content/Intent", "putExtra", "(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn("com.android.system");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/content/Intent", "setAction", "(Ljava/lang/String;)Landroid/content/Intent;", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn("com.android.system");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/content/Intent", "setPackage", "(Ljava/lang/String;)Landroid/content/Intent;", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }


    static void m32(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "isArray", "()Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "isEmpty", "()Z", true);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(IFNE, label1);
        methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "()V", false);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitLabel(label1);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void m33(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/os/Handler");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/os/Looper", "getMainLooper", "()Landroid/os/Looper;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/os/Handler", "<init>", "(Landroid/os/Looper;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/os/Message", "obtain", "()Landroid/os/Message;", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitFieldInsn(PUTFIELD, "android/os/Message", "obj", "Ljava/lang/Object;");
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/os/Handler", "sendMessage", "(Landroid/os/Message;)Z", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();
    }

    static void m34(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/os/Handler");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/os/Looper", "getMainLooper", "()Landroid/os/Looper;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/os/Handler", "<init>", "(Landroid/os/Looper;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/os/Handler", "removeCallbacksAndMessages", "(Ljava/lang/Object;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitLdcInsn(2000L);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/os/Handler", "sendEmptyMessageDelayed", "(IJ)Z", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 3);
        methodVisitor.visitEnd();
    }

    static void m35(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "MODEL", "Ljava/lang/String;");
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "trim", "()Ljava/lang/String;", false);
        methodVisitor.visitLdcInsn("\\s*");
        methodVisitor.visitLdcInsn("");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "replaceAll", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label1);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitLdcInsn("");
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m36(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "BOARD", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "HARDWARE", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 2);
        methodVisitor.visitEnd();
    }

    static void m37(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Throwable");
        Label label3 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label3, null);
        Label label4 = new Label();
        methodVisitor.visitTryCatchBlock(label3, label4, label3, null);
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "BRAND", "Ljava/lang/String;");
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/text/TextUtils", "isEmpty", "(Ljava/lang/CharSequence;)Z", false);
        methodVisitor.visitJumpInsn(IFNE, label1);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "toLowerCase", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        Label label5 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitJumpInsn(GOTO, label5);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitLabel(label4);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitLabel(label5);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 5);
        methodVisitor.visitEnd();
    }

    static void m38(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/CheckBox");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/CheckBox", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/CheckBox", "isChecked", "()Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/CheckBox", "setTag", "(Ljava/lang/Object;)V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }


    static void m39(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/CheckBox");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/CheckBox", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/CheckBox", "setEnabled", "(Z)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/CheckBox", "setFocusableInTouchMode", "(Z)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/CheckBox", "setFocusable", "(Z)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }


    static void m40(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/ListView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ListView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitIntInsn(BIPUSH, 20);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ListView", "setDividerHeight", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ListView", "getSelectedItemPosition", "()I", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ListView", "setSelection", "(I)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }


    static void m41(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/ListView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ListView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitTypeInsn(NEW, "android/graphics/drawable/ColorDrawable");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/graphics/drawable/ColorDrawable", "<init>", "()V", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ListView", "setDivider", "(Landroid/graphics/drawable/Drawable;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitTypeInsn(NEW, "android/widget/ArrayAdapter");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ListView", "getContext", "()Landroid/content/Context;", false);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ArrayAdapter", "<init>", "(Landroid/content/Context;I)V", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ListView", "setAdapter", "(Landroid/widget/ListAdapter;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(5, 3);
        methodVisitor.visitEnd();
    }

    static void m42(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/DatePicker");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/DatePicker", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(9223372036854775807L);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/DatePicker", "setMaxDate", "(J)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/DatePicker", "setMinDate", "(J)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/DatePicker", "setEnabled", "(Z)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m43(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/DatePicker");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/DatePicker", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/DatePicker", "updateDate", "(III)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 3);
        methodVisitor.visitEnd();
    }

    static void m44(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/RadioGroup");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/RadioGroup", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitTypeInsn(NEW, "android/widget/RadioButton");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/RadioButton", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitTypeInsn(NEW, "android/widget/RadioButton");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/RadioButton", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/RadioGroup", "addView", "(Landroid/view/View;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/RadioGroup", "addView", "(Landroid/view/View;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 5);
        methodVisitor.visitEnd();
    }

    static void m45(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/RadioButton");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/RadioButton", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/RadioButton", "setId", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/RadioButton", "findFocus", "()Landroid/view/View;", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m46(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/LinkedList");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/LinkedList", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedList", "add", "(Ljava/lang/Object;)Z", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedList", "add", "(Ljava/lang/Object;)Z", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedList", "contains", "(Ljava/lang/Object;)Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedList", "clear", "()V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void m47(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
        methodVisitor.visitVarInsn(DSTORE, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
        methodVisitor.visitVarInsn(DSTORE, 4);
        methodVisitor.visitVarInsn(DLOAD, 2);
        methodVisitor.visitVarInsn(DLOAD, 4);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "max", "(DD)D", false);
        methodVisitor.visitVarInsn(DSTORE, 6);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitLdcInsn(":");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitVarInsn(DLOAD, 6);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(D)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 8);
        methodVisitor.visitEnd();
    }

    static void m48(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/text/NumberFormat", "getInstance", "()Ljava/text/NumberFormat;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/text/NumberFormat", "format", "(D)Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitTypeInsn(NEW, "android/widget/TextView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "setText", "(Ljava/lang/CharSequence;)V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();
    }

    static void m49(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/text/NumberFormat", "getInstance", "()Ljava/text/NumberFormat;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/text/NumberFormat", "getRoundingMode", "()Ljava/math/RoundingMode;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitTypeInsn(NEW, "java/math/BigDecimal");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(Ljava/lang/String;)V", false);
        methodVisitor.visitTypeInsn(NEW, "java/math/BigDecimal");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ICONST_2);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/math/BigDecimal", "<init>", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/math/BigDecimal", "divide", "(Ljava/math/BigDecimal;Ljava/math/RoundingMode;)Ljava/math/BigDecimal;", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/Object;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(4, 4);
        methodVisitor.visitEnd();
    }

    static void m50(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/Timer");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Timer", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Timer", "schedule", "(Ljava/util/TimerTask;JJ)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(6, 3);
        methodVisitor.visitEnd();
    }

    static void m51(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/util/SparseArray");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/util/SparseArray", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/util/SparseArray", "put", "(ILjava/lang/Object;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/util/SparseArray", "put", "(ILjava/lang/Object;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m52(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/util/ArrayMap");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/util/ArrayMap", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/util/ArrayMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m53(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Base64", "decode", "(Ljava/lang/String;I)[B", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Arrays", "toString", "([B)Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void m54(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/util/ArrayList");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitTypeInsn(NEW, "android/util/Pair");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/util/Pair", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitTypeInsn(NEW, "android/util/Pair");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/util/Pair", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitTypeInsn(NEW, "android/util/Pair");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/util/Pair", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitTypeInsn(NEW, "android/util/Pair");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/util/Pair", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(5, 3);
        methodVisitor.visitEnd();
    }

    static void m55(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/view/ViewStub", "inflate", "(Landroid/content/Context;ILandroid/view/ViewGroup;)Landroid/view/View;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "animate", "()Landroid/view/ViewPropertyAnimator;", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "callOnClick", "()Z", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "clearFocus", "()V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "clearAnimation", "()V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m56(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/view/LayoutInflater", "from", "(Landroid/content/Context;)Landroid/view/LayoutInflater;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/LayoutInflater", "getFactory", "()Landroid/view/LayoutInflater$Factory;", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "android/view/LayoutInflater$Factory", "onCreateView", "(Ljava/lang/String;Landroid/content/Context;Landroid/util/AttributeSet;)Landroid/view/View;", true);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/View", "requestLayout", "()V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 5);
        methodVisitor.visitEnd();
    }

    static void m57(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/webkit/WebView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/webkit/WebView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/webkit/WebView", "setWebViewClient", "(Landroid/webkit/WebViewClient;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/webkit/WebView", "setWebChromeClient", "(Landroid/webkit/WebChromeClient;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/webkit/WebView", "canGoBack", "()Z", false);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/webkit/WebView", "goBack", "()V", false);
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 3);
        methodVisitor.visitEnd();
    }

    static void m58(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitLdcInsn(method.methodName);
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "BRAND", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "DEVICE", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "MODEL", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/util/Log", "w", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        methodVisitor.visitInsn(POP);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitFieldInsn(GETSTATIC, "android/os/Build", "MANUFACTURER", "Ljava/lang/String;");
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(Ljava/lang/String;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 2);
        methodVisitor.visitEnd();
    }

    static void m59(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/ImageView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ImageView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setVisibility", "(I)V", false);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "getLayoutParams", "()Landroid/view/ViewGroup$LayoutParams;", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitTypeInsn(INSTANCEOF, "android/widget/FrameLayout$LayoutParams");
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFEQ, label0);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/FrameLayout$LayoutParams");
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitFieldInsn(PUTFIELD, "android/widget/FrameLayout$LayoutParams", "bottomMargin", "I");
        methodVisitor.visitLabel(label0);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 4);
        methodVisitor.visitEnd();
    }

    static void m60(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/widget/TextView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/TextView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitTypeInsn(NEW, "android/widget/ImageView");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/ImageView", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/TextView", "setText", "(Ljava/lang/CharSequence;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageResource", "(I)V", false);
        methodVisitor.visitTypeInsn(NEW, "android/widget/LinearLayout");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/widget/LinearLayout", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/LinearLayout", "addView", "(Landroid/view/View;)V", false);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/LinearLayout", "addView", "(Landroid/view/View;)V", false);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(3, 5);
        methodVisitor.visitEnd();
    }

    static void m61(ClassVisitor classVisitor, GenerateMethodInfo method, String className) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(method.access, method.methodName, method.descriptor, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "android/view/View");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/view/View", "<init>", "(Landroid/content/Context;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 2);
        methodVisitor.visitTypeInsn(NEW, "android/graphics/Rect");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/graphics/Rect", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitTypeInsn(NEW, "android/view/TouchDelegate");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "android/view/TouchDelegate", "<init>", "(Landroid/graphics/Rect;Landroid/view/View;)V", false);
        methodVisitor.visitVarInsn(ASTORE, 4);
        methodVisitor.visitVarInsn(ALOAD, 4);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(FCONST_0);
        methodVisitor.visitInsn(FCONST_0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "android/view/MotionEvent", "obtain", "(JJIFFI)Landroid/view/MotionEvent;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/view/TouchDelegate", "onTouchEvent", "(Landroid/view/MotionEvent;)Z", false);
        methodVisitor.visitInsn(POP);
        generateMethodReturn(methodVisitor, method);
        methodVisitor.visitMaxs(9, 5);
        methodVisitor.visitEnd();
    }

}
