package org.sec.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.JSRInlinerAdapter;
import org.sec.model.ClassReference;
import org.sec.model.DoSResult;
import org.sec.model.MethodReference;

import java.util.List;
import java.util.Map;

public class ForDoSClassVisitor extends ClassVisitor {
    private final Map<ClassReference.Handle, ClassReference> classMap;
    private final Map<MethodReference.Handle, MethodReference> methodMap;
    private final List<DoSResult> forDoSResults;

    private String name;

    public ForDoSClassVisitor(Map<ClassReference.Handle, ClassReference> classMap,
                              Map<MethodReference.Handle, MethodReference> methodMap,
                              List<DoSResult> forDoSResults) {
        super(Opcodes.ASM6);
        this.classMap = classMap;
        this.methodMap = methodMap;
        this.forDoSResults = forDoSResults;
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
        ForDoSMethodAdapter forDoSMethodVisitor = new ForDoSMethodAdapter(
                api, mv, this.name, access, name, desc, signature, exceptions,
                classMap, methodMap, forDoSResults);
        return new JSRInlinerAdapter(forDoSMethodVisitor, access, name, desc, signature, exceptions);
    }
}
