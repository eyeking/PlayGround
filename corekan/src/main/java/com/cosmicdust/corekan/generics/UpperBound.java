package com.cosmicdust.corekan.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nshah on 2/10/2016.
 */
public class UpperBound {
public static void main(String[] args)

{
    //List<?> objects would translates in to List<? extends Object>. And in that scenario, compiler doesn't know which type to use.
    // List<? extends String> also doesn't work, because ? extends String may mean ONLY children of String.
    //But List <? extends String> might work for getting value from List.
    //List<? super String> means String AND its parent(s).
    List<? super String> objects = new ArrayList<String>();

    objects.add("try");
    System.out.println(objects.get(0));
}
}
