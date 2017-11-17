package com.cosmicdust.corekan;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.List;

public class ReferenceTester {


    @Test
    public void _whenListReferenceChanged_returnsChangedValues() {
        List<String> strList = Lists.newArrayList("A", "B", "C");

        ListWrapper wrapper = new ListWrapper();
        List<String> retrievedList = wrapper.getStrNumList();
        wrapper.setStrNumList(strList);

        System.out.println(Joiner.on(",").join(retrievedList));
        System.out.println(Joiner.on(",").join(wrapper.getStrNumList()));
    }

    private class ListWrapper {
        private List<String> strNumList = Lists.newArrayList("1", "2", "3");

        public List<String> getStrNumList() {
            return strNumList;
        }

        public void setStrNumList(List<String> strNumList) {
            this.strNumList = strNumList;
        }
    }
}
