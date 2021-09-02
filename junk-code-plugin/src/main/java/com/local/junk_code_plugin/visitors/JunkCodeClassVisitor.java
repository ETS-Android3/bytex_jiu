package com.local.junk_code_plugin.visitors;

import static org.objectweb.asm.Opcodes.ACC_ABSTRACT;
import static org.objectweb.asm.Opcodes.ACC_INTERFACE;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

import com.local.junk_code_plugin.JunkCodeContext;
import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.FieldVisitor;

import java.util.List;
import java.util.Random;

/**
 * Created on 2021/9/1 15:03
 */
public class JunkCodeClassVisitor extends BaseClassVisitor {

    private static final Random random = new Random();

    private static final char[] abc = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private final JunkCodeContext context;
    private boolean skip = false;//是否跳过
    private String className = "";

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
        for (int i = 0; i < context.getMethodCount(); i++) {
            int index = random.nextInt(15);
            String methodName = generateName(index);
            if (index == 0) {
                GenerateVMTool.method1(this, methodName);
            } else if (index == 1) {
                GenerateVMTool.method2(this, methodName);
            } else if (index == 2) {
                GenerateVMTool.method3(this, methodName);
            } else if (index == 3) {
                GenerateVMTool.method4(this, methodName);
            } else if (index == 4) {
                GenerateVMTool.method5(this, methodName);
            } else if (index == 5) {
                GenerateVMTool.method6(this, methodName);
            } else if (index == 6) {
                GenerateVMTool.method7(this, methodName);
            } else if (index == 7) {
                GenerateVMTool.method8(this, methodName);
            } else if (index == 8) {
                GenerateVMTool.method9(this, methodName, getJunkClassName());
            } else if (index == 9) {
                GenerateVMTool.method10(this, methodName, getJunkClassName());
            } else if (index == 10) {
                GenerateVMTool.method11(this, methodName, getJunkClassName());
            } else if (index == 11) {
                GenerateVMTool.method12(this, methodName, getJunkClassName());
            } else if (index == 12) {
                GenerateVMTool.method13(this, methodName, getJunkClassName());
            } else if (index == 13) {
                GenerateVMTool.method14(this, methodName, getJunkClassName());
            } else if (index == 14) {
                GenerateVMTool.method15(this, methodName, getJunkClassName());
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
