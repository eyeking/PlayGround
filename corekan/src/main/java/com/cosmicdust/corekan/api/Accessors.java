package com.cosmicdust.corekan.api;

/**
 * Created by nshah on 12/7/2015.
 */
public class Accessors {
    private int num;

    public int getNum() {
        System.out.println("getter called.");
        return num++;
    }
}
