package com.test;

import avm.Blockchain;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;


public class StructAndMap {

    private static final AionMap<String,podInfo> pods = new AionMap();
    static {
        addPodInfo();
    }

    private static class podInfo {
        int num;
        String[] podMembers;
        podInfo(int num, String[] podmembers) {
            this.num = num;
            this.podMembers = podmembers;
        }
    }


    private static void addPodInfo() {
        podInfo accelerationAdoption = new podInfo(5,new String[]{"Sam","Karim","Eric","Dipesh","Jennifer", "Satya"});
        podInfo devSuccess = new podInfo(3, new String[]{"Mike","John","Kim"});

        pods.put("Acceleration Adoption", accelerationAdoption);
        pods.put("Dev Success", devSuccess);

    }

    @Callable
    public static int getPodNum(String podName) {
        Blockchain.require(pods.containsKey(podName));
        return pods.get(podName).num;
    }

    @Callable
    public static String[] getPodMembers(String podName) {
        Blockchain.require(pods.containsKey(podName));
        return pods.get(podName).podMembers;
    }
}