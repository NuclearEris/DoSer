package org.sec.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.sec.core.dos.*;
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
                             List<DoSResult> patternDoSResults,
                             List<DoSResult> forDoSResults,
                             List<DoSResult> arrayDoSResults,
                             List<DoSResult> listDoSResults,
                             List<DoSResult> mapDoSResults) {
        logger.info("start pattern dos analysis");
        for (ClassFile file : classFileList) {
            try {
                PatternDoSClassVisitor pcv = new PatternDoSClassVisitor(classMap, methodMap, patternDoSResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(pcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("find pattern dos: " + patternDoSResults.size());
        logger.info("pattern dos analysis finish");
        logger.info("start for dos analysis");
        for (ClassFile file : classFileList) {
            try {
                ForDoSClassVisitor fcv = new ForDoSClassVisitor(classMap, methodMap, forDoSResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(fcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("find for dos: " + forDoSResults.size());
        logger.info("for dos analysis finish");
        logger.info("start array dos analysis");
        for (ClassFile file : classFileList) {
            try {
                ArrayDoSClassVisitor acv = new ArrayDoSClassVisitor(classMap, methodMap, arrayDoSResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(acv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("find array dos: " + arrayDoSResults.size());
        logger.info("array dos analysis finish");
        logger.info("start list dos analysis");
        for (ClassFile file : classFileList) {
            try {
                ListDoSClassVisitor lcv = new ListDoSClassVisitor(classMap, methodMap, listDoSResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(lcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("find list dos: " + listDoSResults.size());
        logger.info("list dos analysis finish");
        logger.info("start map dos analysis");
        for (ClassFile file : classFileList) {
            try {
                MapDoSClassVisitor mcv = new MapDoSClassVisitor(classMap, methodMap, mapDoSResults);
                ClassReader cr = new ClassReader(file.getFile());
                cr.accept(mcv, ClassReader.EXPAND_FRAMES);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("find map dos: " + mapDoSResults.size());
        logger.info("map dos analysis finish");
    }
}
