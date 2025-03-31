package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext){

         String timsStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
         repName="Test-Report-"+timsStamp+".html";
         sparkReporter=new ExtentSparkReporter(".\\reports" + repName);

         sparkReporter.config().setDocumentTitle("Opencart Automation Report");
         sparkReporter.config().setReportName("Opencart functional Testing");
         sparkReporter.config().setTheme(Theme.DARK);


         extent=new ExtentReports();
         extent.attachReporter(sparkReporter);
         extent.setSystemInfo("Application","Opencart");
         extent.setSystemInfo("Module","admin");
         extent.setSystemInfo("Sub module","customers");
         extent.setSystemInfo("Username",System.getProperty("user.name"));
         extent.setSystemInfo("Environment","QA");


         String os=testContext.getCurrentXmlTest().getParameter("os");
         extent.setSystemInfo("operating system",os);

         String browser= testContext.getCurrentXmlTest().getParameter("browser");
         extent.setSystemInfo("Browser","browser");


         List<String> includeGroups= testContext.getCurrentXmlTest().getIncludedGroups();
         if(!includeGroups.isEmpty()){
             extent.setSystemInfo("Groups",includeGroups.toString());
         }


    }

    public void onTestSuccess(ITestResult result){
        test=extent.createTest(result.getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS,result.getName()+" got successfully executed");
    }

    public void onFinish(ITestResult Context) {

        extent.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);
    }

    public void onTestFailure(ITestResult result){

        test=extent.createTest(result.getClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL,result.getName()+"got failed");
        test.log(Status.INFO,result.getThrowable().getMessage());

        try {

            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e1) {

            e1.printStackTrace();
        }

    }


        /*try {

            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }


