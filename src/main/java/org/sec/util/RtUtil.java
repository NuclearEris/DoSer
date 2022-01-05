package org.sec.util;

import org.apache.log4j.Logger;
import org.sec.model.ClassFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RtUtil {
    private static final Logger logger = Logger.getLogger(RtUtil.class);

    public static List<ClassFile> getAllClassesFromJars(List<String> jarPathList) {
        logger.info("get all classes");
        Set<ClassFile> classFileSet = new HashSet<>();
        for (String jarPath : jarPathList) {
            classFileSet.addAll(JarUtil.resolveNormalJarFile(jarPath));
        }
        return new ArrayList<>(classFileSet);
    }
}
