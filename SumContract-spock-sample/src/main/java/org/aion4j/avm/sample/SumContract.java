package org.aion4j.avm.sample;

import org.aion.avm.tooling.abi.Callable;

public class SumContract {

    @Callable
    public static int sum(int i, int j) {
        return i + j;
    }

}
