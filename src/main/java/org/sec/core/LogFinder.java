package org.sec.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.sec.core.log.LogClassVisitor;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.LogResult;
import org.sec.model.MethodReference;

import java.util.List;
import java.util.Map;

public class LogFinder {
    private static final Logger logger = LogManager.getLogger(LogFinder.class);

    public static void start(List<ClassFile> classFileList,
                             Map<ClassReference.Handle, ClassReference> classMap,
                             Map<MethodReference.Handle, MethodReference> methodMap,
                             List<LogResult> logResults) {
        logger.info("start log inject analysis");
        for (ClassFile file : classFileList) {
            try {
                LogClassVisitor lcv = new LogClassVisitor(classMap, methodMap, logResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(lcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("find log inject: " + logResults.size());
        logger.info("log inject analysis finish");
    }
}
