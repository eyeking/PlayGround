package com.cosmicdust.corekan.api;

import org.testng.annotations.Test;

/**
 * Created by nshah on 1/5/2016.
 */
public class SuperSubTester {

    @Test
    public void isIt()
    {
        Parent child2 = new Child2();
        System.out.println(child2.getClass().getCanonicalName());
        System.out.println(child2.identify().getClass().getCanonicalName());
    }
}
