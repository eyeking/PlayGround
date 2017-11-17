package com.cosmicdust.corekan.api.Collection;


import org.testng.annotations.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MultiValuedMapTest {
    @Test
    public void CanNullBeAdded() {
        MultivaluedMap map = new MultivaluedHashMap<String, String>();

        map.add("One", "Ek");
        map.add("One", "Uno");
        map.add("Two", null);

        assertThat(map.size(), is(2));
    }
}
