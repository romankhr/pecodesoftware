package com.test;

import com.test.listeners.TestListener;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.apache.commons.lang3.SystemUtils.IS_OS_LINUX;

@Listeners(TestListener.class)
public abstract class BaseTest {

    private static final String DEFAULT_ENV_PROPERTIES_FILE_PATH = "src/test/resources/test.properties";
    protected RemoteWebDriver driver = null;
    private static Properties properties;
    protected WebDriverWait wait;

    static {
        properties = new Properties();
        loadPropertiesFromFile(DEFAULT_ENV_PROPERTIES_FILE_PATH);
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        if (IS_OS_MAC || IS_OS_LINUX) {
            if (getBrowser().equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
                driver = new ChromeDriver();
                this.wait = new WebDriverWait(driver, Integer.parseInt(getTimeouts()));
            } else if (getBrowser().equals("firefox")) {
                System.setProperty("webdriver.firefox.driver", "drivers/geckodriver");
                driver = new FirefoxDriver();
                this.wait = new WebDriverWait(driver, Integer.parseInt(getTimeouts()));
            }
        } else if (IS_OS_WINDOWS) {
            if (getBrowser().equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
                driver = new ChromeDriver();
                this.wait = new WebDriverWait(driver, Integer.parseInt(getTimeouts()));
            } else if (getBrowser().equals("firefox")) {
                System.setProperty("webdriver.gecko.driver", "drivers//firefox//geckodriver.exe");
//                System.setProperty("webdriver.firefox.driver", "drivers//firefox//geckodriver.exe");
                System.out.println(System.getProperty("webdriver.firefox.driver"));
                System.out.println(getBrowser().toString());
                driver = new FirefoxDriver();
                this.wait = new WebDriverWait(driver, Integer.parseInt(getTimeouts()));
            }
        }
        driver.manage().window().maximize();
        driver.get(getMainUrl());
        System.out.println("----->" + getMainUrl().toString());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected String getMainUrl() {
        String result;
        if (getProperty("test.isLocal").equals("true")) {
            result = properties.getProperty("test.mainUrl");
        } else {
            result = properties.getProperty("test.qaUrl");
        }
        return (result != null) ? result.trim() : null;
    }

    protected String getProperty(String key) {
        String result = properties.getProperty(key);
        return (result != null) ? result.trim() : null;
    }

    protected String getBrowser() {
        return getProperty("test.browser");
    }

    protected String getTimeouts() {
        return getProperty("test.timeout");
    }

    protected String getEmail() {
        return getProperty("test.email");
    }

    protected String getApiKey() {
        return getProperty("test.apiKey");
    }

    private static void loadPropertiesFromFile(String propertiesFilePath) {
        try {
            InputStream propertiesStream;
            propertiesStream = new FileInputStream(new File(propertiesFilePath).getPath());
            properties.load(propertiesStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
