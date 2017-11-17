package com.cosmicdust.corekan.api.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nshah on 2/17/2017.
 */
public enum Color {
RED("red"),
    BLUE("blue"),
    GREEN("green"),
    MAUVE("mauve");

    private String colorStr;

    Color(String colorStr){
        this.colorStr = colorStr;
    }

    public static List<Color> COLOR_LIST = Arrays.asList(RED, GREEN, BLUE);

    public String getColorStr() {
        return colorStr;
    }
}
