package org.sec;

import com.beust.jcommander.JCommander;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sec.core.Discovery;
import org.sec.model.ClassFile;
import org.sec.model.ClassReference;
import org.sec.model.MethodReference;
import org.sec.util.RtUtil;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final List<ClassReference> discoveredClasses = new ArrayList<>();
    private static final List<MethodReference> discoveredMethods = new ArrayList<>();
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
            start(command);
        } else {
            logger.error("no jars input");
        }
    }

    private static void start(Command command) {
        List<String> jars = command.jars;
        boolean debug = command.debug;
        List<ClassFile> classFileList = RtUtil.getAllClassesFromJars(jars);
        Discovery.start(classFileList,discoveredClasses,discoveredMethods);
    }
}
