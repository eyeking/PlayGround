package com.cosmicdust.corekan.api;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ScratchPad {

	@Test
	public void stringBuilderEmptyConstructor() {
		StringBuilder sb = new StringBuilder("");
		
		assertThat(sb.toString().isEmpty(), is(true));
	}

	@Test
	public void whatItPrints() throws UnknownHostException {
		System.out.println(InetAddress.getLocalHost());
		System.out.println(InetAddress.getLocalHost().getCanonicalHostName());


		InetAddress address = InetAddress.getByAddress(new byte[]{(byte) 10, (byte) 131, (byte) 94, (byte) 113});
		System.out.println(address.getCanonicalHostName());

		InetAddress address1 = InetAddress.getByName("usplsrovpids55.plano.webhost.eds.net");
		System.out.println(address1.getCanonicalHostName());
	}

	@Test
	public void doubleBrace() {
		List<String> strings = new ArrayList<String>(){
			{
				add("Foo");
				add("Bar");
			}
		};


		strings.forEach(string -> System.out.println(string));
	}

	@Test
	public void checkPrecision() {
		Integer intObj = Integer.parseInt("100");
		Double doubleObj = Double.parseDouble("100.111");

		print(intObj);
		print(doubleObj);
	}

	private void print(Number num) {
		System.out.println(BigDecimal.valueOf(num.doubleValue()));
	}
}
