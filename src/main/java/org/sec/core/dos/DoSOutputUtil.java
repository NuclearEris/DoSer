package org.sec.core.dos;

import org.sec.model.DoSResult;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SuppressWarnings("all")
public class DoSOutputUtil {
    public static void start(List<DoSResult> patternDoSResults,
                             List<DoSResult> forDoSResults,
                             List<DoSResult> arrayDoSResults,
                             List<DoSResult> mapDoSResults,
                             List<DoSResult> listDoSResults,
                             List<DoSResult> readExternalResults) {
        try {
            StringBuilder patternBuilder = new StringBuilder();
            int index = 1;
            for (DoSResult doSResult : patternDoSResults) {
                patternBuilder.append(index);
                patternBuilder.append("-");
                patternBuilder.append(doSResult.getClassReference().getName());
                patternBuilder.append(".");
                patternBuilder.append(doSResult.getMethodReference().getName());
                patternBuilder.append("->");
                patternBuilder.append(doSResult.getType());
                patternBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("pattern.txt"),
                    patternBuilder.toString().getBytes(StandardCharsets.UTF_8));

            StringBuilder forBuilder = new StringBuilder();
            index = 1;
            for (DoSResult doSResult : forDoSResults) {
                forBuilder.append(index);
                forBuilder.append("-");
                forBuilder.append(doSResult.getClassReference().getName());
                forBuilder.append(".");
                forBuilder.append(doSResult.getMethodReference().getName());
                forBuilder.append("->");
                forBuilder.append(doSResult.getType());
                forBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("for.txt"),
                    forBuilder.toString().getBytes(StandardCharsets.UTF_8));

            StringBuilder arrayBuilder = new StringBuilder();
            index = 1;
            for (DoSResult doSResult : arrayDoSResults) {
                arrayBuilder.append(index);
                arrayBuilder.append("-");
                arrayBuilder.append(doSResult.getClassReference().getName());
                arrayBuilder.append(".");
                arrayBuilder.append(doSResult.getMethodReference().getName());
                arrayBuilder.append("->");
                arrayBuilder.append(doSResult.getType());
                arrayBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("array.txt"),
                    arrayBuilder.toString().getBytes(StandardCharsets.UTF_8));

            StringBuilder listBuilder = new StringBuilder();
            index = 1;
            for (DoSResult doSResult : listDoSResults) {
                listBuilder.append(index);
                listBuilder.append("-");
                listBuilder.append(doSResult.getClassReference().getName());
                listBuilder.append(".");
                listBuilder.append(doSResult.getMethodReference().getName());
                listBuilder.append("->");
                listBuilder.append(doSResult.getType());
                listBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("list.txt"),
                    listBuilder.toString().getBytes(StandardCharsets.UTF_8));

            StringBuilder mapBuilder = new StringBuilder();
            index = 1;
            for (DoSResult doSResult : mapDoSResults) {
                mapBuilder.append(index);
                mapBuilder.append("-");
                mapBuilder.append(doSResult.getClassReference().getName());
                mapBuilder.append(".");
                mapBuilder.append(doSResult.getMethodReference().getName());
                mapBuilder.append("->");
                mapBuilder.append(doSResult.getType());
                mapBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("map.txt"),
                    mapBuilder.toString().getBytes(StandardCharsets.UTF_8));

            StringBuilder readBuilder = new StringBuilder();
            index = 1;
            for (DoSResult doSResult : readExternalResults) {
                readBuilder.append(index);
                readBuilder.append("-");
                readBuilder.append(doSResult.getClassReference().getName());
                readBuilder.append(".");
                readBuilder.append(doSResult.getMethodReference().getName());
                readBuilder.append("->");
                readBuilder.append(doSResult.getType());
                readBuilder.append("\n");
                index++;
            }
            Files.write(Paths.get("readExternal.txt"),
                    readBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
