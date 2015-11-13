package com.cosmicdust.dwkan.api;

import junit.framework.TestCase;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
/**
 * Created by nshah on 10/23/2015.
 */
public class SayingTest extends TestCase {

    public void testGetId() throws Exception {
// mock creation
        List mockedList = mock(List.class);
when(mockedList.get(0)).thenReturn("First").thenReturn("Second");

// using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        String test = (String)mockedList.get(0);
        String test1 = (String)mockedList.get(0);
        String test2 = (String)mockedList.get(0);
        mockedList.clear();

// selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();

        verify(mockedList);
    }

    public void testGetContent() throws Exception {

    }
}