package edu.hw11.Task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public class FibAppender implements ByteCodeAppender {

    private static final int MAX_STACK_SIZE = 2;
    private static final int MAX_LOCAL_SIZE = 5;

    @Override
    public @NotNull Size apply(
        MethodVisitor methodVisitor,
        Implementation.@NotNull Context context,
        @NotNull MethodDescription methodDescription
    ) {
        Label l1 = new Label();
        Label l2 = new Label();
        Label l5 = new Label();
        Label l6 = new Label();

        methodVisitor.visitCode();

        //L0

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IFEQ, l1);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPNE, l2);

        //L1

        methodVisitor.visitLabel(l1);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        //L2

        methodVisitor.visitLabel(l2);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitInsn(Opcodes.ICONST_0);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);

        //L3

        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);

        //L4

        methodVisitor.visitInsn(Opcodes.ICONST_1);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 3);

        //L5

        methodVisitor.visitLabel(l5);
        methodVisitor.visitFrame(Opcodes.F_APPEND, 3, new Object[]{Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 3);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
        methodVisitor.visitJumpInsn(Opcodes.IF_ICMPEQ, l6);

        //L7

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 4);

        //L8

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitInsn(Opcodes.IADD);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 2);

        //L9

        methodVisitor.visitVarInsn(Opcodes.ILOAD, 4);
        methodVisitor.visitVarInsn(Opcodes.ISTORE, 1);

        //L10

        methodVisitor.visitIincInsn(3, 1);

        //L11

        methodVisitor.visitJumpInsn(Opcodes.GOTO, l5);

        //L6

        methodVisitor.visitLabel(l6);
        methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        methodVisitor.visitVarInsn(Opcodes.ILOAD, 2);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitMaxs(MAX_STACK_SIZE, MAX_LOCAL_SIZE);
        methodVisitor.visitEnd();

        return new Size(MAX_STACK_SIZE, MAX_LOCAL_SIZE);
    }
}
