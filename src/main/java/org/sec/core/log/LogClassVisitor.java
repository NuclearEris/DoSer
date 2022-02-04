package org.sec.core.log;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.JSRInlinerAdapter;
import org.sec.model.ClassReference;
import org.sec.model.LogResult;
import org.sec.model.MethodReference;

import java.util.List;
import java.util.Map;

public class LogClassVisitor extends ClassVisitor {
    private final Map<ClassReference.Handle, ClassReference> classMap;
    private final Map<MethodReference.Handle, MethodReference> methodMap;
    private final List<LogResult> logResults;

    private String name;

    public LogClassVisitor(Map<ClassReference.Handle, ClassReference> classMap,
                           Map<MethodReference.Handle, MethodReference> methodMap,
                           List<LogResult> logResults) {
        super(Opcodes.ASM6);
        this.classMap = classMap;
        this.methodMap = methodMap;
        this.logResults = logResults;
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.name = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        LogMethodAdapter slf4jLogMethodAdapter = new LogMethodAdapter(
                api, mv, this.name, access, name, desc, signature, exceptions,
                classMap, methodMap, logResults);
        return new JSRInlinerAdapter(slf4jLogMethodAdapter, access, name, desc, signature, exceptions);
    }
}
