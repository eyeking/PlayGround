package com.cosmicdust.corekan.api.math;

import java.math.BigDecimal;
import java.math.MathContext;

import org.testng.annotations.Test;

public class RounderTest {

    @Test
    public void roundingUpToSpecifiedValue()
    {
        BigDecimal securityDeposit = new BigDecimal(1024.5384);
        double roundingFactor = 50d;
        BigDecimal temp = securityDeposit.divide(BigDecimal.valueOf(roundingFactor), MathContext.DECIMAL32);
        BigDecimal ceilingValueAfterDivision = temp.setScale(0, BigDecimal.ROUND_CEILING);
        BigDecimal doubleRounded = ceilingValueAfterDivision.multiply(BigDecimal.valueOf(roundingFactor));

//        double ceilingValueAfterDivision = Math.ceil(securityDeposit.doubleValue()/roundingFactor);
//        double doubleRounded = ceilingValueAfterDivision*roundingFactor;

        System.out.println(doubleRounded);

        /*System.out.println(ceilingValueAfterDivision*.01d);
        bd = bd.setScale(3, RoundingMode.CEILING);
        assertThat(bd.toPlainString(), is("27.235"));*/
    }

}
