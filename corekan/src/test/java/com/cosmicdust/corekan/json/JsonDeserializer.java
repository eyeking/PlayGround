package com.cosmicdust.corekan.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class JsonDeserializer {

    @Test
    public void testParentClass() throws JsonProcessingException {
        Polygon rectangle = new Polygon();
        rectangle.setColor("Red");
        rectangle.setNumberOfSides(4);

        String strPolygon = deserialize(rectangle);
        System.out.println(strPolygon);

        Conic circle = new Conic();
        circle.setColor("Black");
        circle.setClosedCurve(true);

        String strConic = deserialize(circle);
        System.out.println(strConic);
    }

    private String deserialize(Shape shape) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(shape);
    }
}
