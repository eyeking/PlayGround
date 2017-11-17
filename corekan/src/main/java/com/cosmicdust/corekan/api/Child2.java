package com.cosmicdust.corekan.api;

/**
 * Created by nshah on 1/5/2016.
 */
public class Child2 extends ParentImpl {
        public Child2 identify(){
            return this;
        }

        public void justInChild2() 
        {
        	System.out.println("From justInChild2()");
        }
        
        public static void whatPrints(OfficeAddress address)
        {
        	System.out.println("From Child2");
        }

}
