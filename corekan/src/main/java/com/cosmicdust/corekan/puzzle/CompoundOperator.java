package com.cosmicdust.corekan.puzzle;

public class CompoundOperator {
	public static void main(String[] args)
	{
		byte b = 10;
		byte b1 = 10;
		
		b *= 5.7;
		b1 =  b1 * 5.7; //b1 =  (byte) (b1 * 5.7);
		
		System.out.println(b); 
		System.out.println(b1);

		b = 100;
		b1 = 100;
		
		b /= 2.5;
		b1 = b1 / 2.5; //b1 = (byte) (b1 / 2.5);
		
		System.out.println(b); 
		System.out.println(b1);

		char ch = '0'; // 48
		char ch1 = '0';
		
		ch *= 1.1; // 52.8 -> 52 ('4')
		ch1 = ch1 * 1.1; //ch1 = (char) (ch1 * 1.1);
		
		System.out.println(ch);
		System.out.println(ch1);


		ch = 'A'; // 65
		ch1 = 'A';
		
		ch *= 1.5; //97.5 -> 97 ('a')
		ch1 = ch1 * 1.5; //ch1 = (char) (ch1 * 1.5);
		
		System.out.println(ch); 
		System.out.println(ch1);
	}

}
