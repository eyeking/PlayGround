package com.cosmicdust.corekan.api.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

    @Test
    public void testprecision() {

        NumberFormat FORMATTER = new DecimalFormat("#0.0000");
        BigDecimal rate = new BigDecimal("7.46");

        double rateDouble = rate.doubleValue()/100;

        BigDecimal formattedRate = new BigDecimal(rateDouble);

        Double a = formattedRate.doubleValue();
        String b = FORMATTER.format(a);
        BigDecimal c = new BigDecimal(b);
        System.out.println(c);
    }


    @Test
    public void testDouble() {
        final NumberFormat FORMATTER = new DecimalFormat("#0.00");
        BigDecimal value = new BigDecimal(new BigDecimal("10").doubleValue()/100);
        BigDecimal formattedVlaue = new BigDecimal(FORMATTER.format(value.doubleValue()));
        System.out.println(formattedVlaue);
    }


    @Test
    public void verifyCompareAndEqualsSame() {
        Integer int1 = new Integer(100);
        Integer int2 = new Integer(100);

        if (int1 == int2) {
            System.out.println("== returns true");
        }

        if (int1.equals(int2)) {
            System.out.println("equals returns true");
        }

        if (int1.compareTo(int2) == 0) {
            System.out.println("compareTo returns true");
        }
    }
}
