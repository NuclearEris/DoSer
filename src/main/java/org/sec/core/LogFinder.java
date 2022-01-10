package org.sec.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.MethodReference;

import java.util.List;
import java.util.Map;

public class LogFinder {
    private static final Logger logger = LogManager.getLogger(Logger.class);

    public static void start(List<ClassFile> classFileList,
                             Map<ClassReference.Handle, ClassReference> classMap,
                             Map<MethodReference.Handle, MethodReference> methodMap) {
    }
}
