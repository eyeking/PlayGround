package com.routeone;

import com.smartystreets.api.ClientBuilder;
import com.smartystreets.api.Credentials;
import com.smartystreets.api.Request;
import com.smartystreets.api.exceptions.SmartyException;
import com.smartystreets.api.us_street.Candidate;
import com.smartystreets.api.us_street.Client;
//import com.smartystreets.api.us_street.ClientBuilder;
import com.smartystreets.api.us_street.Lookup;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

/**
 * Created by nshah on 3/9/2017.
 */
public class SmartyStreetTest {

    @Test
    public void getStreetAddress() throws SmartyException, IOException {
        Lookup lookup = buildSmartyStreetsLookup("31500 Northwestern Hwy", "", "", "", "48334");

        Client client = new ClientBuilder("552dd787-394d-dbca-555d-63b6808af619", "PiSuM8xaUJHz78eODU8k").buildUsStreetApiClient();//buildSmartyStreetsClient();
        client.send(lookup);

        Candidate resolvedAddress = lookup.getResult(0);

        assertThat(resolvedAddress.getComponents().getPlus4Code(), is("2567"));
        assertThat(resolvedAddress.getComponents().getCityName(), is("Farmington Hills"));

    }

    private Lookup buildSmartyStreetsLookup(String addressLine1, String addressLine2, String city, String state, String zipCode) {
        Lookup lookup = new Lookup();
        lookup.setMaxCandidates(1);
        lookup.setStreet(addressLine1);
        lookup.setZipCode(zipCode);

        if(addressLine2 != null) {
            lookup.setSecondary(addressLine2);
        }

        if(city != null) {
            lookup.setCity(city);
        }

        if(state != null) {
            lookup.setState(state);
        }

        return lookup;
    }

    private Client buildSmartyStreetsClient() {

        return new ClientBuilder(new Credentials() {
            @Override
            public void sign(Request request) {
                request.putParameter("auth-token","998784255946100");
                request.putHeader("Referer","https://www.CAPE-LINK.com");
            }
        }).buildUsStreetApiClient();
    }
}
