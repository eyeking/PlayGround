package com.cosmicdust.corekan;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class testapi {

    @Test
    public void _whenCheckedNullMatcher_() {
        Object obj = null;

        assertThat(obj, nullValue());
    }
}
