package com.cosmicdust.corekan.puzzle;

import java.util.Random;

public class RandomNum {
	public static void main(String[] args)
	{
		Random random = new Random(100);

		for(int i=0;i<10;i++)
		{
		    System.out.println(random.nextInt(10));
		}
	}

}
