package com.comicdust.corekan.api;

/**
 * Created by nshah on 12/7/2015.
 */
public class UnravelStaticBlock {
    static int num = new StaticTest().getNum();

    public static void main(String[] args)
    {
        System.out.println(UnravelStaticBlock.num);
        System.out.println(UnravelStaticBlock.num);
        System.out.println(UnravelStaticBlock.num);
    }
}
