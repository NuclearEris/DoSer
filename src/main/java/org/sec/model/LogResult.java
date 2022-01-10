package org.sec.model;

public class LogResult {
    private final ClassReference classReference;
    private final MethodReference methodReference;

    public LogResult(ClassReference classReference, MethodReference methodReference) {
        this.classReference = classReference;
        this.methodReference = methodReference;
    }

    public ClassReference getClassReference() {
        return classReference;
    }

    public MethodReference getMethodReference() {
        return methodReference;
    }
}
