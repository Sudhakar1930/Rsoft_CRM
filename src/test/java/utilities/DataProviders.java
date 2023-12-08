package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DataProviders {
	public String [][] getData(String excelFileName) throws IOException
	{
		String path=".\\testData\\" + excelFileName + ".xlsx" ;//taking xl file from testData
		
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
	
	//DataProvider 1
	@DataProvider(name="SFI_Message")
	public Object[][] fSFI_Message() throws IOException{
    	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
    	Object[][] arrObj = getData("SFI_Message_Tbl");
    	return arrObj;
	}
	//DataProvider 2
		@DataProvider(name="SFI_MultiChoice")
		public Object[][] fSFI_MultiChoice() throws IOException{
	    	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
	    	Object[][] arrObj = getData("SFI_MultiChoice_Tbl");
	    	return arrObj;
		}
		//DataProvider 3
		@DataProvider(name="SFI_MC_Skip")
		public Object[][] fSFI_MC_Skip() throws IOException{
		//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
		Object[][] arrObj = getData("SFI_MultiChoice_Tbl");
		return arrObj;
		}
		
		
	//DataProvider 2
	@DataProvider(name="ValidUsr")
	public Object[][] floginCredentials() throws IOException{
    	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
    	Object[][] arrObj = getData("AE_ValidUser");
    	return arrObj;
	}
	//DataProvider 3
	
	//DataProvider 4
}
