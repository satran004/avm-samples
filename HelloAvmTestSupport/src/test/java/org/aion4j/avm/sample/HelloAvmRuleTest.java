package org.aion4j.avm.sample;

import avm.Address;
import org.aion.avm.embed.AvmRule;
import org.aion.avm.tooling.ABIUtil;
import org.aion.types.TransactionStatus;
import org.junit.*;

import java.math.BigInteger;

public class HelloAvmRuleTest {
    @ClassRule
    public static AvmRule avmRule = new AvmRule(true);

    public static HelloAvmTestImpl helloAvmTestImpl;

    @Before
    public void deploy() {
        helloAvmTestImpl = new HelloAvmTestImpl(avmRule);
        helloAvmTestImpl.deploy("Hello Test Support");
    }

    @Test
    public void getString() {
        String data = helloAvmTestImpl.getString().getData();

        Assert.assertEquals("Hello Test Support", data);
    }

    @Test
    public void setString() {
        helloAvmTestImpl.setString("test123");

        String data = helloAvmTestImpl.getString().getData();

        Assert.assertEquals("test123", data);
    }

}

