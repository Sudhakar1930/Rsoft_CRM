package testCases;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class NoGui {
	static int n1=0, n2=1,n3;
	static void printFibonacci(int count) {
		if(count > 0) {
			n3  = n1 + n2;
			System.out.println(" " + n3);
			n1 = n2;
			n2 = n3;
			printFibonacci(count-1);
		}
	}

public static void main(String[] args) throws MalformedURLException {
	
	
//	String sActValue = "Wed May 08 18:00:00 IST 2024";
//	String sExpValue = "18:00:00";
//	boolean bValue = sActValue.contains(sExpValue);
//	System.out.println(bValue);
	
//	String s3 = "JournalDev";
//	int start = 1;
//	int end = 5;
//
//	System.out.println(s3.substring(start, end));
//	int choice = 2;
	
//	int n1=0, n2=1,n3,count=10;
//	System.out.print(n1 + " " + n2);
//	for(int i =2;i<count;i++) {
//		n3 = n1 + n2;
//		System.out.print(" " + n3);
//		n1 = n2;
//		n2 = n3;
//	}
//	
	
	printFibonacci(10);
	
	
	TreeSet<String> TrSet = new TreeSet<String>();
	TrSet.add("A");
	TrSet.add("B");
	TrSet.add("C");
	TrSet.add("D");
	System.out.println(TrSet);
	NavigableSet<String> TrRev = TrSet.descendingSet();
	
	Iterator<String> ItrSet = TrRev.iterator();
	while(ItrSet.hasNext()) {
		System.out.println(ItrSet.next());
	}



	ArrayList<Integer> al = new ArrayList<Integer>();
	al.add(1);
	al.add(5);
	al.add(10);
	al.add(15);
	al.add(20);
	
	Iterator<Integer> it = al.iterator();
	while(it.hasNext()) {
		System.out.println(it.next());
	}
	
	ArrayList<String> lt = new ArrayList<String>();
	lt.add("one");
	lt.add("two");
	lt.add("three");
	lt.add("four");
	lt.add("five");
	
	Iterator<String> its = lt.iterator();
	while(its.hasNext()) {
		System.out.println(its.next());
	}
	
	//al.forEach((n)->{System.out.println(n);});
	
//	Runnable r1 = () -> System.out.println("My Runnable");

	
//	String s1 ="tskNT_ExecuteTask_DTAfterDaysasdfasdf";
//	String s2="tskNT_ExecuteTask_DTBeforeMinuasdfasdf";
//	System.out.println("s1 length:" + StringUtils.left(s1,29));
//	System.out.println("s1 length:" + StringUtils.left(s2,29));
//	  Date d1 = new Date(); 
//	  // Printing the value stored in above object 
//      System.out.println("Current date is " + d1);
//      
//      Date currentDate = new Date();
//      System.out.println("current get date: " + currentDate.getDate());
//      System.out.println("current get day: " + currentDate.getDay());
//      
//      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//      String currentDateTime = dateFormat.format(currentDate);
//      System.out.println("Current date is " + currentDateTime);
 
      
      
}
}
