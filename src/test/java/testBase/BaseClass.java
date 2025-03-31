package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;



public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass (groups = {"Sanity","Regression","Master","DataDriven"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        //loading config.properties

        FileReader file=new FileReader("./src//test/resources//config.properties");

        p=new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());


        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){

            DesiredCapabilities capabilities=new DesiredCapabilities();
            if(os.equalsIgnoreCase("windows")){

                capabilities.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac")){

                capabilities.setPlatform(Platform.MAC);
            }
            else {
                System.out.println("No Matching OS");
                return;
            }
            switch (br.toLowerCase()) {

                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("No matching browser");
                    return;
            }

                driver = new RemoteWebDriver(new URL("http://192.168.1.39:4444"), capabilities);


           // driver=new RemoteWebDriver(new URL("http://192.168.1.39:4444/ui/"),capabilities);
        }

        if(p.getProperty("execution_env").equalsIgnoreCase("local")){
        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver(); break;
            case "edge":
                driver = new EdgeDriver(); break;
            case "firefox":
                driver = new FirefoxDriver(); break;
            default:
                System.out.println("Invalid Browser"); return;
        }
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appUrl"));//reading url from properties file
        driver.manage().window().maximize();

    }

    @AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
    public void tearDown() {

        driver.quit();

        }

    public String randomString() {

        String geneneratedstring = RandomStringUtils.randomAlphabetic(5);
        return geneneratedstring;
    }


    public String randomNumber(){

        String geneneratednumbers = RandomStringUtils.randomNumeric(5);
        return geneneratednumbers;

    }

    public String captureScreen(String tname) throws IOException{

        String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname + "_"+ timestamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
