package com.local.junk_code_plugin.visitors;

import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.ATHROW;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.I2L;
import static org.objectweb.asm.Opcodes.IADD;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.IFGE;
import static org.objectweb.asm.Opcodes.IFLE;
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

    private static String getIntegerType() {
        return integerArr[random.nextInt(integerArr.length)];
    }


    static void method1(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(Ljava/lang/String;)V", null, null);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "()V", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
        methodVisitor.visitInsn(DUP);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "()V", false);
        methodVisitor.visitInsn(ATHROW);
        methodVisitor.visitMaxs(2, 1);
        methodVisitor.visitEnd();
    }

    static void method3(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "()Ljava/lang/String;", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
        methodVisitor.visitInsn(LCONST_0);
        methodVisitor.visitInsn(LCMP);
        Label label0 = new Label();
        methodVisitor.visitJumpInsn(IFLE, label0);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "()I", null, null);
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

    static void method5(ClassVisitor classVisitor, String methodName) {
        String integerType = getIntegerType();
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + integerType + integerType + ")J", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(IADD);
        methodVisitor.visitInsn(I2L);
        methodVisitor.visitInsn(LRETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void method6(ClassVisitor classVisitor, String methodName) {
        String integerType = getIntegerType();
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + integerType + integerType + ")I", null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ILOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 2);
        methodVisitor.visitInsn(ISUB);
        methodVisitor.visitInsn(IRETURN);
        methodVisitor.visitMaxs(2, 3);
        methodVisitor.visitEnd();
    }

    static void method7(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(Ljava/lang/String;" + getIntegerType() + ")Ljava/lang/String;", null, null);
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

    static void method8(ClassVisitor classVisitor, String methodName) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(Ljava/util/Date;" + getIntegerType() + ")J", null, null);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + ")V", null, null);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + ")" + paramType, null, null);
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

    static void method11(ClassVisitor classVisitor, String methodName, String paramType) {
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + getIntegerType() + ")" + paramType, null, null);
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

    static void method12(ClassVisitor classVisitor, String methodName, String paramType) {
        String integerType = getIntegerType();
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + integerType + ")" + integerType, null, null);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + "Ljava/lang/String;)Ljava/lang/String;", null, null);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + "Ljava/lang/Object;)" + paramType, null, null);
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
        MethodVisitor methodVisitor = classVisitor.visitMethod(methodName.length() % 2 == 0 ? ACC_PRIVATE : ACC_PUBLIC, methodName, "(" + paramType + paramType + ")" + paramType, null, null);
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


}
