package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    void verify_acoount_Registration(){

        logger.info("****** Staring TC001_AccountRegistrationTest");

        try
        {
        HomePage hp=new HomePage(driver);
        hp.clickMyaccount();
        logger.info("Clicked on Myaccount link");

        hp.clickRegsiter();


        logger.info("Providing the details");
        AccountRegistrationPage ac=new AccountRegistrationPage(driver);
        ac.setFirstname(randomString().toUpperCase());
        ac.setLastname(randomString().toUpperCase());
        ac.setEmail(randomString()+"@gmail.com");
        ac.setTelephone(randomNumber());
        ac.setPassword("test@123");
        ac.setConfirmpassword("test@123");
        ac.setPrivacypolicy();
        ac.clickContinue();
        String confmsg=ac.getConfirmationMessage();

        logger.info("Validating expecting message");
        Assert.assertEquals(confmsg,"Your Account Has Been Created!");
        }
        catch (Exception e){

            logger.error("Test failed...");
            logger.debug("Debug logs");
            Assert.fail();
        }

}}
