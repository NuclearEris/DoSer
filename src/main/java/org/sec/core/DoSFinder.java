package org.sec.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.DoSResult;
import org.sec.model.MethodReference;

import java.util.List;
import java.util.Map;

public class DoSFinder {
    private static final Logger logger = LogManager.getLogger(DoSFinder.class);

    public static void start(List<ClassFile> classFileList,
                             Map<ClassReference.Handle, ClassReference> classMap,
                             Map<MethodReference.Handle, MethodReference> methodMap,
                             List<DoSResult> patternDoSResults) {
        logger.info("start pattern dos analysis");
        for (ClassFile file : classFileList) {
            try {
                PatternDoSClassVisitor dcv = new PatternDoSClassVisitor(classMap,methodMap, patternDoSResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(dcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
