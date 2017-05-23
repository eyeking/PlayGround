package com.cosmicdust.corekan.guava;


import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.google.common.base.Predicates.alwaysTrue;
import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.or;

/**
 * Created by nshah on 2/14/2017.
 */
public class PredicatesExplore {

    @Test
    public void testAnd() {
        Predicate<String> p1 = new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return s.length() == 3;
            }
        };

        Predicate<String> p2 = new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return CharMatcher.javaUpperCase().matchesAllOf(s);
            }
        };

        Predicate<String> finalPred = alwaysTrue();
        finalPred = and(finalPred, p1);
        finalPred = and(finalPred, p2);

        Predicate<String> tmpPred = and(alwaysTrue(), p1, p2);

        List<String> strings = Arrays.asList("ABC", "def", "ABCD", "defg");
        Collection<String> filetredList = Collections2.filter(strings, and(p1, p2));

        filetredList.forEach((s -> {
            System.out.println(s);
        }));
    }
}
