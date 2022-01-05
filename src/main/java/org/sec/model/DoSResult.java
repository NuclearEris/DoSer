package org.sec.model;

public class DoSResult {
    private final ClassReference classReference;
    private final MethodReference methodReference;
    private final Integer type;

    public static final Integer ARRAY_TYPE = 0;
    public static final Integer FOR_TYPE = 1;
    public static final Integer PATTERN_TYPE = 2;
    public static final Integer LIST_TYPE = 3;
    public static final Integer MAP_TYPE = 4;

    public DoSResult(ClassReference classReference, MethodReference methodReference, Integer type) {
        this.classReference = classReference;
        this.methodReference = methodReference;
        if (type < 0 || type > 4) {
            throw new RuntimeException("error type");
        }
        this.type = type;
    }

    public ClassReference getClassReference() {
        return classReference;
    }

    public MethodReference getMethodReference() {
        return methodReference;
    }

    public String getType() {
        if (this.type.equals(ARRAY_TYPE)) {
            return "Array DoS";
        }
        if (this.type.equals(FOR_TYPE)) {
            return "For DoS";
        }
        if (this.type.equals(PATTERN_TYPE)) {
            return "Pattern DoS";
        }
        if (this.type.equals(LIST_TYPE)) {
            return "List DoS";
        }
        if (this.type.equals(MAP_TYPE)) {
            return "Map DoS";
        }
        return "null";
    }
}
