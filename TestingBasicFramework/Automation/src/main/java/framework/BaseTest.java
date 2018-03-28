package framework;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private Browser browser;

    @BeforeSuite
    public void setup() {
        browser = new Browser();
        browser.loadEnvironment();
    }

    @AfterSuite
    public void tearDown(){
        if(getBrowser().getSettings().getCloseBrowser()){
            browser.getDriver().quit();
        }
    }

    public Browser getBrowser(){
        return browser;
    }


}
