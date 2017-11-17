package com.routeone.hmac;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.routeone.fault.HmacSignatureGenerationException;
import org.junit.Test;

public class HmacSignatureTest {
    @Test
    public void testCompute() throws HmacSignatureGenerationException {
        String sharedSecret="8$tfs^6uW7~KTW&d#GE&C3uT@jhZw&BUsYEGYxv&dbyZ*3YVfVdII9Za#GKwAkgT";
        String algorithm="HmacSHA256";
        String canonicalRepresentation ="GET\n" +
        "\n"+
        "application/json\n"+
        "fri, 24 feb 2017 17:02:25 gmt\n"+
        "x-routeone-act-as-dealership:HN1MZ\n"+
        "x-routeone-date:Fri, 24 Feb 2017 17:02:25 GMT\n"+
        "/customer-quote/categories\n";

        String fromHeaders = "GET\n\napplication/json\nfri, 24 feb 2017 17:02:25 gmt\nx-routeone-act-as-dealership:HN1MZ\nx-routeone-date:Fri, 24 Feb 2017 17:02:25 GMT\n/customer-quote/categories\n";
        HmacSignature hmacSignature = new HmacSignature(sharedSecret, algorithm, fromHeaders);
        assertEquals("Correct Signature returned", "sLCfYGKXp6i7WkmUlCyudSrEAWmmWENKqOqj0E+zvzg=",hmacSignature.compute());

    }

}