package org.sec.core;

import org.objectweb.asm.ClassReader;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.MethodReference;

import java.util.List;

public class Discovery {
    public static void start(List<ClassFile> classFileList,
                             List<ClassReference> discoveredClasses,
                             List<MethodReference> discoveredMethods){
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
