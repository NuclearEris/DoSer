package org.sec.core.dos;

import org.sec.model.DoSResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoSUtil {
    public static List<DoSResult> unique(List<DoSResult> data) {
        Set<DoSResult> temp = new HashSet<>(data);
        return new ArrayList<>(temp);
    }

    public static List<DoSResult> addReadExternalResults(List<DoSResult> arrayDoSResults,
                                                          List<DoSResult> patternDoSResults,
                                                          List<DoSResult> forDoSResults,
                                                          List<DoSResult> mapDoSResults,
                                                          List<DoSResult> listDoSResults) {
        Set<DoSResult> temp = new HashSet<>();
        for (DoSResult doSResult : arrayDoSResults) {
            addSet(temp, doSResult);
        }
        for (DoSResult doSResult : patternDoSResults) {
            addSet(temp, doSResult);
        }
        for (DoSResult doSResult : forDoSResults) {
            addSet(temp, doSResult);
        }
        for (DoSResult doSResult : mapDoSResults) {
            addSet(temp, doSResult);
        }
        for (DoSResult doSResult : listDoSResults) {
            addSet(temp, doSResult);
        }
        return new ArrayList<>(temp);
    }

    public static void addSet(Set<DoSResult> set, DoSResult doSResult) {
        if (doSResult.getMethodReference().getName().equalsIgnoreCase("readExternal")) {
            set.add(doSResult);
        }
    }
}
