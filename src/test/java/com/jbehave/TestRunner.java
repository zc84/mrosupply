package com.jbehave;

import com.data.DataProvider;
import jxl.write.WriteException;
import net.thucydides.jbehave.ThucydidesJUnitStories;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Dmitry Sherstobitov QA LSE at intetics.com skype: dmitry_sherstobitov
 */
public class TestRunner extends ThucydidesJUnitStories {

    public TestRunner() throws ClassNotFoundException, SQLException, IOException {
        runThucydides();
    }

    @BeforeStories
    public void setup() throws Exception {
        set_driver();
        new StoryProvider().generate_report();
    }

    @BeforeScenario
    public void reset() throws Exception {
        new DataProvider().clear();
    }

    @AfterStories
    public void tearDown() throws IOException {
        Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
        Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
    }

    /**
     * Generates test plan from stories
     *
     * @throws IOException
     * @throws WriteException
     */

    public void set_driver() {
        String driver = System.getProperty("webdriver.driver");

        if (driver == null) {
            System.setProperty("webdriver.driver", "firefox");
        } else if (driver.equals("iexplorer")) {
            System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
        } else if (driver.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.logfile", "logs/chromedriver.log");
        } else {
            System.setProperty("webdriver.driver", "firefox");
        }
    }
}
