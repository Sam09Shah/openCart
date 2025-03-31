package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="loginData")
    public String [][] getData() throws IOException{

        String path=".\\testdata\\Book1.xlsx"; //taking xl file from testData

        ExcelUtilities xlutil=new ExcelUtilities(path);

        int totalrows=xlutil.getRowcount("Sheet1");
        int totalcols=xlutil.getCellcount("Sheet1",1);

        String logindata[][]=new String[totalrows][totalcols]; //created for two dimesion array

        for(int i=1;i<=totalrows;i++) //Read data from x1 storing in two dimesnion array

        {
            for(int j=0;j<totalcols;j++) //i is rows and j is column
            {

                logindata[i-1][j]=xlutil.getCellData("Sheet1",i,j);

            }
        }
        return logindata;//returning two dimensional array

    }
}
