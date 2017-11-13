package com.cosmicdust.corekan.api;

import org.testng.annotations.Test;

/**
 * Created by Nayan on 6/11/2016.
 */
public class IntegerCacheTest {
    @Test
    public void whenIntegerUsedOftenThenCached()
    {
        for(int i = -255; i<= 256; i++)
        {
            Integer i1 = i;
            Integer i2 = i;

            System.out.println(i1 + " == " + i2 + " : " + (i1 == i2));
        }
    }
}
