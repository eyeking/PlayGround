package com.cosmicdust.corekan.api;

import com.cosmicdust.corekan.api.enums.Color;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Formatter {

    @Test
    public void formattedActualMonthlyPayment() {
        Double payment = Double.parseDouble("622.9");
        DecimalFormat formatter = new DecimalFormat("$#,###.00");
        String strPayment = formatter.format(payment);

        assertThat(strPayment, is("$622.90"));
    }

    @Test
    public void stringFormatter() {
        System.out.println(String.format("The color %s is not available.", Color.BLUE.getColorStr()));
        System.out.println(String.format("The color %s is not available.", Color.BLUE.name()));
        System.out.println(String.format("The color %s is not available.", Color.BLUE));
    }
}
