package org.sec;

import com.beust.jcommander.JCommander;
import com.sun.deploy.security.CredentialInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sec.core.Discovery;
import org.sec.core.DoSFinder;
import org.sec.core.Output;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.DoSResult;
import org.sec.model.MethodReference;
import org.sec.util.RtUtil;

import java.time.zone.ZoneRules;
import java.util.*;


public class Main {
    private static final List<ClassReference> discoveredClasses = new ArrayList<>();
    private static final List<MethodReference> discoveredMethods = new ArrayList<>();
    private static final Map<ClassReference.Handle, ClassReference> classMap = new HashMap<>();
    private static final Map<MethodReference.Handle, MethodReference> methodMap = new HashMap<>();
    private static List<DoSResult> arrayDoSResults = new ArrayList<>();
    private static List<DoSResult> forDoSResults = new ArrayList<>();
    private static List<DoSResult> patternDoSResults = new ArrayList<>();
    private static List<DoSResult> mapDoSResults = new ArrayList<>();
    private static List<DoSResult> listDoSResults = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Logo.PrintLogo();
        logger.info("start code inspector");
        Command command = new Command();
        JCommander jc = JCommander.newBuilder().addObject(command).build();
        jc.parse(args);
        if (command.help) {
            jc.usage();
        }
        if (command.jars != null && command.jars.size() != 0) {
            if (command.isDebug) {
                Command.debug = true;
            }
            start(command);
        } else {
            logger.error("no jars input");
        }
    }

    private static void start(Command command) {
        List<String> jars = command.jars;
        List<ClassFile> classFileList = RtUtil.getAllClassesFromJars(jars);
        Discovery.start(classFileList, discoveredClasses, discoveredMethods, classMap, methodMap);
        logger.info("total classes: " + discoveredClasses.size());
        logger.info("total methods: " + discoveredMethods.size());
        DoSFinder.start(classFileList, classMap, methodMap,
                patternDoSResults, forDoSResults, arrayDoSResults, listDoSResults, mapDoSResults);
        patternDoSResults = unique(patternDoSResults);
        forDoSResults = unique(forDoSResults);
        arrayDoSResults = unique(arrayDoSResults);
        listDoSResults = unique(listDoSResults);
        mapDoSResults = unique(mapDoSResults);
        Output.start(patternDoSResults, forDoSResults, arrayDoSResults, mapDoSResults, listDoSResults);
        logger.info("delete temp files...");
    }

    private static List<DoSResult> unique(List<DoSResult> data) {
        Set<DoSResult> temp = new HashSet<>(data);
        return new ArrayList<>(temp);
    }
}
