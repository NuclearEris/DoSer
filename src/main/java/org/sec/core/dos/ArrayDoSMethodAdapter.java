package org.sec.core.dos;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.sec.Command;
import org.sec.jvm.CoreMethodAdapter;
import org.sec.model.ClassReference;
import org.sec.model.DoSResult;
import org.sec.model.MethodReference;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ArrayDoSMethodAdapter extends CoreMethodAdapter<String> {
    private static final Logger logger = LogManager.getLogger(ArrayDoSMethodAdapter.class);

    private final ClassReference classReference;
    private final MethodReference methodReference;
    private final List<DoSResult> arrayDoSResults;
    private final String owner;
    private final int access;
    private final String name;
    private final String desc;

    public ArrayDoSMethodAdapter(int api, MethodVisitor mv, String owner, int access,
                                 String name, String desc, String signature, String[] exceptions,
                                 Map<ClassReference.Handle, ClassReference> classMap,
                                 Map<MethodReference.Handle, MethodReference> methodMap,
                                 List<DoSResult> arrayDoSResults) {
        super(api, mv, owner, access, name, desc, signature, exceptions);
        this.classReference = classMap.get(new ClassReference.Handle(owner));
        this.methodReference = methodMap.get(new MethodReference.Handle(
                this.classReference.getHandle(), name, desc));
        this.arrayDoSResults = arrayDoSResults;
        this.owner = owner;
        this.access = access;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        int localIndex = 0;
        if ((this.access & Opcodes.ACC_STATIC) == 0) {
            localVariables.set(localIndex, "source");
            localIndex += 1;
        }
        for (Type argType : Type.getArgumentTypes(desc)) {
            localVariables.set(localIndex, "source");
            localIndex += argType.getSize();
        }
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        if (opcode == Opcodes.NEWARRAY) {
            if (operandStack.get(0).contains("source")) {
                if (Command.debug) {
                    logger.info("find array dos: " + this.owner + "." + this.name);
                }
                arrayDoSResults.add(new DoSResult(
                        this.classReference, this.methodReference, DoSResult.ARRAY_TYPE));
            }
        }
        super.visitIntInsn(opcode, operand);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        if (opcode == Opcodes.ANEWARRAY) {
            if (operandStack.get(0).contains("source")) {
                if (Command.debug) {
                    logger.info("find array dos: " + this.owner + "." + this.name);
                }
                arrayDoSResults.add(new DoSResult(
                        this.classReference, this.methodReference, DoSResult.ARRAY_TYPE));
            }
        }
        super.visitTypeInsn(opcode, type);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        Type[] argTypes = Type.getArgumentTypes(desc);
        if (opcode != Opcodes.INVOKESTATIC) {
            Type[] extendedArgTypes = new Type[argTypes.length + 1];
            System.arraycopy(argTypes, 0, extendedArgTypes, 1, argTypes.length);
            extendedArgTypes[0] = Type.getObjectType(owner);
            argTypes = extendedArgTypes;
        }
        for (int i = 0; i < argTypes.length; i++) {
            if (operandStack.get(i).contains("source")) {
                Type returnType = Type.getReturnType(desc);
                if (returnType.getSort() != Type.VOID) {
                    super.visitMethodInsn(opcode, owner, name, desc, itf);
                    operandStack.set(0, "source");
                    return;
                }
            }
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}
