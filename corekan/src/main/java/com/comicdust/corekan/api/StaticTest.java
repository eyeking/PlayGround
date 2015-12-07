package com.comicdust.corekan.api;

/**
 * Created by nshah on 12/7/2015.
 */
public class StaticTest {
    private int num;
    public int getNum()
    {
        System.out.println("getNum called.");
        return num++;
    }
}
