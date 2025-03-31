package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDTC3 extends BaseClass {


    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class,groups = "DataDriven")
    public void verify_loginDDT(String email, String pwd, String exp) {

        logger.info(".............Starting TC003_LoginDDTC3 test case");

        try {

            HomePage hp = new HomePage(driver);
            hp.clickMyaccount();
            hp.clickLogin();

            //Login
            LoginPage lp = new LoginPage(driver);
            lp.enterUsername(email);
            lp.enterPassword(pwd);
            lp.clickLogin();


            //MyAccount

            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageExists();

    /*
Data is valid ---> Login Success---> Test passed -->Logout
Data is valid----> Login Failed----> Test Failed

Data is Invalid--->Login Success--->Test Failed ---> logout
Data is Invalid----> Login Failed---> Test passed


 */
            if (exp.equalsIgnoreCase("Valid")) {

                if (targetPage == true) {

                    macc.logout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }

            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage == true) {

                    macc.logout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }

            }

            logger.info(".............Finished TC003_LoginDDTC3 test case");

        } catch (Exception e) {

            Assert.fail();
        }

    }}




