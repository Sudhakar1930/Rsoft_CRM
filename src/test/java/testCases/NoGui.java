package testCases;

import java.net.MalformedURLException;

public class NoGui {

public static void main(String[] args) throws MalformedURLException {	
	String sActValue = "Wed May 08 18:00:00 IST 2024";
	String sExpValue = "18:00:00";
	boolean bValue = sActValue.contains(sExpValue);
	System.out.println(bValue);
}
}
