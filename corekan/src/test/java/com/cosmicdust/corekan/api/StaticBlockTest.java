package com.cosmicdust.corekan.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by nshah on 12/7/2015.
 */
public class StaticBlockTest {

    static int num = new Accessors().getNum();

    int num1 = new Accessors().getNum();

    @Test
    public void isNumAccessible()
    {
        assertThat(StaticBlockTest.num, is(0));
        assertThat(StaticBlockTest.num, is(0));
        assertThat(StaticBlockTest.num, is(0));
    }
}
