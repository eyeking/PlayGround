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
    
    @Test
    public void printType()
    {
    	Parent p1 = new Child1();
    	Parent p2 = new Child2();
    	
    	System.out.println(p1 instanceof Child1);
    	System.out.println(p1 instanceof Parent);
    	System.out.println(p1 instanceof Child2);
    }
    
    @Test
    public void methodCalledBasedOnParameter()
    {
    	Actor<Child1> a1 = new ActorImpl1();
    	Actor<Child2> a2 = new ActorImpl2();
    	
    	ActorImpl1 ai1 = new ActorImpl1();
    	
    	a1.actDependOnType(new Child1());
    	a2.actDependOnType(new Child2());
    }
    
    @Test
    public void staticOverloadedMethods()
    {
    	ParentImpl.whatPrints(new HomeAddress());
    	ParentImpl.whatPrints(new OfficeAddress());
    }
    
    @Test
    public void staticOverLoadedMethods1()
    {
    	caller(new Child1());
    }
    
    private void caller(ParentImpl parent)
    {
    	OverLoaded.printSomething(parent);
    }
}
