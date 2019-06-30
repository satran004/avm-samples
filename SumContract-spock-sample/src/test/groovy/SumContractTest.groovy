import avm.Address

import org.aion.avm.core.util.ABIUtil
import org.aion.avm.tooling.AvmRule
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification

class SumContractTest extends Specification {

    @ClassRule
    @Shared
    private AvmRule avmRule = new AvmRule(true);
    private Address from;
    private Address dappAddr;

    def setup() {
        from = avmRule.getPreminedAccount();
        byte[] dapp = avmRule.getDappBytes(org.aion4j.avm.sample.SumContract, null);
        dappAddr = avmRule.deploy(from, BigInteger.ZERO, dapp).getDappAddress();
    }

    def "4 plus 5 should be equal to 9" () {
        when:
            byte[] txData = ABIUtil.encodeMethodArguments("sum", 5, 4);
            AvmRule.ResultWrapper result = avmRule.call(from, dappAddr, BigInteger.ZERO, txData);
            int sum = Integer.parseInt(String.valueOf(result.getDecodedReturnData()));

        then:
            sum == 9;

    }

    def "a plus b should equal to expected" (int a, int b, int expected) {

        given:
            byte[] txData = ABIUtil.encodeMethodArguments("sum", a, b);
            AvmRule.ResultWrapper result = avmRule.call(from, dappAddr, BigInteger.ZERO, txData);
            int sum = Integer.parseInt(String.valueOf(result.getDecodedReturnData()));

        expect:
            sum == expected

        where:
            a | b | expected
            4 | 6 | 10
            9 | 2 | 11
            23| 21| 44

    }

}

