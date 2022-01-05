package org.sec.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.MethodReference;

import java.util.List;

public class Discovery {
    private static final Logger logger = LogManager.getLogger(Discovery.class);

    public static void start(List<ClassFile> classFileList,
                             List<ClassReference> discoveredClasses,
                             List<MethodReference> discoveredMethods) {
        logger.info("start discovery information");
        for (ClassFile file : classFileList) {
            try {
                DiscoveryClassVisitor dcv = new DiscoveryClassVisitor(discoveredClasses, discoveredMethods);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(dcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
