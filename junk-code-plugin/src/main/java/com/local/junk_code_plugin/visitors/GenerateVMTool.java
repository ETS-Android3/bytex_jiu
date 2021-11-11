package com.local.junk_code_plugin.visitors;

import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.DCMPG;
import static org.objectweb.asm.Opcodes.DCONST_0;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.I2L;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.IFGE;
import static org.objectweb.asm.Opcodes.IFLE;
import static org.objectweb.asm.Opcodes.IFNE;
import static org.objectweb.asm.Opcodes.IFNONNULL;
import static org.objectweb.asm.Opcodes.IFNULL;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.ISUB;
import static org.objectweb.asm.Opcodes.LADD;
import static org.objectweb.asm.Opcodes.LCMP;
import static org.objectweb.asm.Opcodes.LCONST_0;
import static org.objectweb.asm.Opcodes.LLOAD;
import static org.objectweb.asm.Opcodes.LRETURN;
import static org.objectweb.asm.Opcodes.LSTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.POP2;
import static org.objectweb.asm.Opcodes.RETURN;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Random;

/**
 * Created on 2021/9/1 18:50
 * VisitorMethod生成工具
 * {@link com.local.junk_code_plugin.test.ASMTest}
 */
public class GenerateVMTool {

    private static final String[] integerArr = {"B", "S", "I"};
    private static final Random random = new Random();

    public static String getIntegerType() {
        return integerArr[random.nextInt(integerArr.length)];
    }

    public static int getMethodAccess(String methodName) {
//        return methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC;
        return ACC_PRIVATE;
    }

//    public static int getMethodOpcode(String methodName) {
//        return methodName.length() % 2 == 0 ? INVOKESPECIAL : INVOKEVIRTUAL;
//    }


    static void method1(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(Ljava/lang/String;)V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitVarInsn(LSTORE, 2);
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitVarInsn(LLOAD, 2);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFGE, label0);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitLdcInsn(methodName);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        Label label1 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label1);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitLabel(label1);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(4, 4);
        methodVisitor.visitEnd();
    }

    static void method2(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "()V", null, null);
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
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitEnd();
    }

    static void method3(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "()Ljava/lang/String;", null, null);
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
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(4, 1);
        methodVisitor.visitEnd();
    }

    static void method4(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "()I", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFLE, label0);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitMaxs(4, 1);
        methodVisitor.visitEnd();
    }

    static void method5(ClassVisitor classVisitor, String methodName, String integerType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + integerType + integerType + ")J", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(IADD);
        methodVisitor.visitInsn(I2L);
        methodVisitor.visitInsn(LRETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void method6(ClassVisitor classVisitor, String methodName, String integerType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + integerType + integerType + ")I", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void method7(ClassVisitor classVisitor, String methodName, String integerType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(Ljava/lang/String;" + integerType + ")Ljava/lang/String;", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void method8(ClassVisitor classVisitor, String methodName, String integerType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(Ljava/util/Date;" + integerType + ")J", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(I2L);
        methodVisitor.visitInsn(LADD);
        methodVisitor.visitInsn(LRETURN);
        methodVisitor.visitMaxs(4, 3);
        methodVisitor.visitEnd();
    }

    static void method9(ClassVisitor classVisitor, String methodName, String paramType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + ")V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

    static void method10(ClassVisitor classVisitor, String methodName, String paramType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + ")" + paramType, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(2, 2);
        methodVisitor.visitEnd();
    }

    static void method11(ClassVisitor classVisitor, String methodName, String paramType, String integerType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + integerType + ")" + paramType, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ILOAD, 2);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFLE, label0);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(1, 3);
        methodVisitor.visitEnd();
    }

    static void method12(ClassVisitor classVisitor, String methodName, String paramType, String integerType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + integerType + ")" + integerType, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNONNULL, label0);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitVarInsn(ISTORE, 2);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitMaxs(1, 3);
        methodVisitor.visitEnd();
    }

    static void method13(ClassVisitor classVisitor, String methodName, String paramType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + "Ljava/lang/String;)Ljava/lang/String;", null, null);
        methodVisitor.visitCode();
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
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void method14(ClassVisitor classVisitor, String methodName, String paramType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + "Ljava/lang/Object;)" + paramType, null, null);
        methodVisitor.visitCode();
        Label label0 = new Label();
        Label label1 = new Label();
        Label label2 = new Label();
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/InterruptedException");
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "wait", "()V", false);
        methodVisitor.visitLabel(label1);
        Label label3 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label3);
        methodVisitor.visitLabel(label2);
        methodVisitor.visitVarInsn(ASTORE, 3);
        methodVisitor.visitVarInsn(ALOAD, 3);
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/InterruptedException", "printStackTrace", "()V", false);
        methodVisitor.visitInsn(ACONST_NULL);
        methodVisitor.visitVarInsn(ASTORE, 1);
        methodVisitor.visitLabel(label3);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(1, 4);
        methodVisitor.visitEnd();
    }

    static void method15(ClassVisitor classVisitor, String methodName, String paramType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(getMethodAccess(methodName), methodName, "(" + paramType + paramType + ")" + paramType, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 1);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFNULL, label0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitLabel(label0);
        methodVisitor.visitVarInsn(ALOAD, 2);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(1, 3);
        methodVisitor.visitEnd();
    }

    static void junkCodeMethod(MethodVisitor methodVisitor, GenerateMethodInfo methodInfo, String className) {
        int ifIndex = random.nextInt(10);
        String methodName = methodInfo.methodName;
        String junkClassName = methodInfo.junkClass;
        String integerType = methodInfo.integerType;
        if (ifIndex == 0) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 1) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "isEmpty", "()Z", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 2) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
            methodVisitor.visitInsn(LCONST_0);
            methodVisitor.visitInsn(LCMP);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFGE, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 3) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "isEnum", "()Z", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "isArray", "()Z", false);
            methodVisitor.visitJumpInsn(IFEQ, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 4) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "hashCode", "()I", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFNE, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);

        } else if (ifIndex == 5) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFNONNULL, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 6) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 7) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "endsWith", "(Ljava/lang/String;)Z", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 8) {
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
            methodVisitor.visitInsn(DCONST_0);
            methodVisitor.visitInsn(DCMPG);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFGE, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            methodVisitor.visitLabel(label0);
        } else if (ifIndex == 9) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/util/Objects", "isNull", "(Ljava/lang/Object;)Z", false);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label0);
            joinMethodCode(methodVisitor, methodInfo, className);
            methodVisitor.visitLabel(label0);
        }
    }

    private static void joinMethodCode(MethodVisitor methodVisitor, GenerateMethodInfo methodInfo, String className) {
        int index = methodInfo.index;
        String methodName = methodInfo.methodName;
        String junkClassName = methodInfo.junkClass;
        String integerType = methodInfo.integerType;
        if (index == 0) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(Ljava/lang/String;)V", false);
        } else if (index == 1) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "()V", false);
        } else if (index == 2) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "()Ljava/lang/String;", false);
            methodVisitor.visitInsn(POP);
        } else if (index == 3) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "()I", false);
            methodVisitor.visitInsn(POP);
        } else if (index == 4) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + integerType + integerType + ")J", false);
            methodVisitor.visitInsn(POP2);
        } else if (index == 5) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + integerType + integerType + ")I", false);
            methodVisitor.visitInsn(POP);
        } else if (index == 6) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(Ljava/lang/String;" + integerType + ")Ljava/lang/String;", false);
            methodVisitor.visitInsn(POP);
        } else if (index == 7) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitTypeInsn(NEW, "java/util/Date");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/Date", "<init>", "()V", false);
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(Ljava/util/Date;" + integerType + ")J", false);
            methodVisitor.visitInsn(POP2);
        } else if (index == 8) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + junkClassName + ")V", false);
        } else if (index == 9) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + junkClassName + ")" + junkClassName, false);
            methodVisitor.visitInsn(POP);
        } else if (index == 10) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + junkClassName + integerType + ")" + junkClassName, false);
            methodVisitor.visitInsn(POP);
        } else if (index == 11) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitIntInsn(BIPUSH, random.nextInt(100));
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + junkClassName + integerType + ")" + integerType, false);
            methodVisitor.visitInsn(POP);
        } else if (index == 12) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", false);
            methodVisitor.visitInsn(POP);
        } else if (index == 13) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitLdcInsn(methodName);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + junkClassName + "Ljava/lang/Object;)" + junkClassName, false);
            methodVisitor.visitInsn(POP);
        } else if (index == 14) {
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, className, methodName, "(" + junkClassName + junkClassName + ")" + junkClassName, false);
            methodVisitor.visitInsn(POP);
        }
    }


}
