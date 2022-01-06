package org.sec;

import com.beust.jcommander.Parameter;

import java.util.List;

public class Command {
    @Parameter(names = {"-h", "--help"}, description = "Help Info", help = true)
    public boolean help;

    @Parameter(names = {"-j", "--jar"}, description = "Scan Jar File")
    public List<String> jars;

    @Parameter(names = {"--debug"}, description = "Debug")
    public boolean isDebug;

    public static boolean debug;
}
