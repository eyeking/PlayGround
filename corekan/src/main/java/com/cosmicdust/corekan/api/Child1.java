package com.cosmicdust.corekan.api;

/**
 * Created by nshah on 1/5/2016.
 */
public class Child1 extends ParentImpl {
    public Child1 identify(){
        return this;
    }
    
    public void justInChild1() 
    {
    	System.out.println("From justInChild1()");
    }
    
    public static void whatPrints(HomeAddress address)
    {
    	System.out.println("From Child1");
    }
}
