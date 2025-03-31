package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import java.io.IOException;

public class TC002_Login extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_login() throws IOException {


        logger.info("************ Starting TC_002_LoginTest********");

        HomePage hp=new HomePage(driver);
        hp.clickMyaccount();
        hp.clickLogin();

        LoginPage lp=new LoginPage(driver);
        lp.enterUsername(p.getProperty("username"));
        lp.enterPassword(p.getProperty("password"));
        lp.clickLogin();

        //Myaccount
        MyAccountPage myacc=new MyAccountPage(driver);
        Boolean targetPage=myacc.isMyAccountPageExists();

        Assert.assertEquals(targetPage,true);
    }
}
