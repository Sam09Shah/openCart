package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

    public AccountRegistrationPage(WebDriver driver){

        super(driver);
    }

    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txtFirstname;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txtlasstname;

    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;

    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txtTelephone;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement txtPassword;

    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txtConfirmpassword;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkdPolicy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;


    public void setFirstname(String fname){
        txtFirstname.sendKeys(fname);

    }

    public void setLastname(String lname){

        txtlasstname.sendKeys(lname);
    }

    public void setEmail(String email){

        txtEmail.sendKeys(email);
    }

    public void setTelephone(String telephone){

        txtTelephone.sendKeys(telephone);
    }

    public void setPassword(String password){

        txtPassword.sendKeys(password);
    }

    public void setConfirmpassword(String confirmpassword){

        txtConfirmpassword.sendKeys(confirmpassword);
    }

    public void setPrivacypolicy(){

        chkdPolicy.click();
    }

    public void clickContinue(){

        btnContinue.click();
    }

    public String getConfirmationMessage(){

        try{
            return (msgConfirmation.getText());
        } catch (Exception e){

            return (e.getMessage());
        }

    }

}


