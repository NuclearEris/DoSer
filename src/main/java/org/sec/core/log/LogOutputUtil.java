package org.sec.core.log;

import org.sec.model.DoSResult;
import org.sec.model.LogResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LogOutputUtil {
    public static void start(List<LogResult> logResults) {
        try {
            StringBuilder patternBuilder = new StringBuilder();
            int index = 1;
            for (LogResult logResult : logResults) {
                patternBuilder.append(index);
                patternBuilder.append("-");
                patternBuilder.append(logResult.getClassReference().getName());
                patternBuilder.append(".");
                patternBuilder.append(logResult.getMethodReference().getName());
                patternBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("log.txt"),
                    patternBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
