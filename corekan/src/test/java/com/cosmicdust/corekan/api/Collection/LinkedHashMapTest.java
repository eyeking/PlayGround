package com.cosmicdust.corekan.api.Collection;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by nshah on 7/13/2017.
 */
public class LinkedHashMapTest {

    @Test
    public void iterate_whenIterated_orderMainted() {
        for(Map.Entry<String,String> entry:sampleLinkedHashMap().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private Map<String, String> sampleLinkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("91", "NINETY ONE");
        map.put("2", "TWO");
        map.put("3", "THREE");
        map.put("4", "FOUR");
        map.put("5", "FIVE");

        return map;
    }
}
