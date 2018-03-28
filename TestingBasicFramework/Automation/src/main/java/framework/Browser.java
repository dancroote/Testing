package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class Browser {

    //Declare WebDriver Object and Settings Object
    private WebDriver driver;
    private Settings settings;

    //Constructor that builds Settings Object from Settings Class and calls methods setSettings().
    public Browser(){
        setSettings(new Settings());
            setupDriver();
        }

    /*Method that creates File Object called chromeDriver using the file found in the pathname.
      Also creates ChromeDriverService Object for headed Driver Service
      Also sets WebDriver Object to this new ChromeDriver
     */
    public void setupDriver () {
        File chromeDriver = new File("C:\\chromedriver\\chromedriver.exe");

        ChromeDriverService chromeDriverServiceHeaded = new ChromeDriverService.Builder().usingDriverExecutable(chromeDriver).build();
        driver = new ChromeDriver(chromeDriverServiceHeaded);
    }

    //Webdriver getter method
    public WebDriver getDriver(){
        return driver;
    }

    //Log message Method
    public void log(String message){
        System.out.println(message);
    }

    //Method to load the Environment by URL.
    public void loadEnvironment(){
        getDriver().get(getSettings().getEnvironment().getURL());
    }

    //Settings getter Method
    public Settings getSettings() {
        return settings;
    }

    //Settings setter method
    private void setSettings(Settings settings) {
        this.settings = settings;
    }

    //Method to sleep with timeout var
    public void sleep(int timeout){
        try{
            Thread.sleep(timeout);
        } catch (InterruptedException e){
            log("Caught InterruptedException while trying to sleep");
        }
    }
}
