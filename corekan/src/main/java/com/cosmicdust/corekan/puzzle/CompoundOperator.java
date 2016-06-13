package com.cosmicdust.corekan.puzzle;

public class CompoundOperator {
	public static void main(String[] args)
	{
		byte b = 10;
		b *= 5.7;
		//byte b1 = b * 5.7;
		
		//System.out.println(b1);
		System.out.println(b); // prints 57


		b = 100;
		b /= 2.5;
		System.out.println(b); // prints 40


		char ch = '0';
		ch *= 1.1;
		System.out.println(ch); // prints '4'


		ch = 'A';
		ch *= 1.5;
		System.out.println(ch); // prints 'a'

	}

}
