package testCases;

import java.net.MalformedURLException;
import java.net.URL;

public class getrecordId {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
//		String sUrl = "https://rdot.in/public/admin?Module=Crmschedulenotification&view=Detail&record=93207&Related=Summary";
		URL sUrl = new URL("https://rdot.in/public/admin?Module=Crmschedulenotification&view=Detail&record=93207&Related=Summary");
//		String sUrlSplitArray = sUrl.split(sUrl)
		
		String sUrlQuery = sUrl.getQuery();
		String sQryArray[] =sUrlQuery.split("=");
		for(int i = 0;i<sQryArray.length;i++) {
			System.out.println("Split - " + i + "Value is: " + sQryArray[i]);
			if(i==3) {
				String sArrayRecId[] = sQryArray[i].split("&");
				System.out.println("Record Id is:" + sArrayRecId[0]);
			}
		}
		
	}

}
