package org.sec.util;

import org.apache.log4j.Logger;
import org.sec.model.ClassFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class JarUtil {
    private static final Logger logger = Logger.getLogger(JarUtil.class);
    private static final Set<ClassFile> classFileSet = new HashSet<>();

    public static List<ClassFile> resolveNormalJarFile(String jarPath) {
        try {
            final Path tmpDir = Files.createTempDirectory(UUID.randomUUID().toString());
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                DirUtil.removeDir(tmpDir.toFile());
            }));
            resolve(jarPath, tmpDir);
            return new ArrayList<>(classFileSet);
        } catch (Exception e) {
            logger.error("error ", e);
        }
        return new ArrayList<>();
    }

    private static void resolve(String jarPath, Path tmpDir) {
        try {
            InputStream is = new FileInputStream(jarPath);
            JarInputStream jarInputStream = new JarInputStream(is);
            JarEntry jarEntry;
            while ((jarEntry = jarInputStream.getNextJarEntry()) != null) {
                Path fullPath = tmpDir.resolve(jarEntry.getName());
                if (!jarEntry.isDirectory()) {
                    if (!jarEntry.getName().endsWith(".class")) {
                        continue;
                    }
                    Path dirName = fullPath.getParent();
                    if (!Files.exists(dirName)) {
                        Files.createDirectories(dirName);
                    }
                    OutputStream outputStream = Files.newOutputStream(fullPath);
                    IOUtil.copy(jarInputStream, outputStream);
                    ClassFile classFile = new ClassFile(jarEntry.getName(), fullPath);
                    classFileSet.add(classFile);
                }
            }
        } catch (Exception e) {
            logger.error("error ", e);
        }
    }
}