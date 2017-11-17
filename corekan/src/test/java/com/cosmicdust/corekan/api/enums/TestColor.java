package com.cosmicdust.corekan.api.enums;

import org.testng.annotations.Test;

public class TestColor {
    @Test
    public void testIfExist() {
        System.out.println(Color.COLOR_LIST.contains(Color.MAUVE));
    }
}
