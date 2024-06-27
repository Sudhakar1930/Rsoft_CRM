package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DataProviders {
	@DataProvider(name="UserNConditionNData")
	public String [][] getAllData(String sEnv) throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//WebForm//WF_RRUserN_OneConditionN_"+sEnv+".xlsx";
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String Testdata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				Testdata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return Testdata;//returning two dimension array
				
	}
}
