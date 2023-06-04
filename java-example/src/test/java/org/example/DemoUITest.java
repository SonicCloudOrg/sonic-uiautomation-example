package org.example;

import org.cloud.sonic.driver.common.tool.SonicRespException;
import org.cloud.sonic.driver.android.AndroidDriver;
import java.io.IOException;
import org.junit.*;

public class DemoUITest {
    // Test Data
    static AndroidDriver androidDriver;
    static String adbUrl = "192.168.0.9:64196";
    static String uiaUrl = "http://192.168.0.9:7777/uia/64197";
    static String appPackage = "com.android.settings";

    @Before
    public void before() throws SonicRespException, IOException {
        System.out.println("Test Start...");
        Runtime.getRuntime().exec(String.format("adb connect %s", adbUrl));
        androidDriver = new AndroidDriver(uiaUrl);
    }

    @Test
    public void test() throws SonicRespException, IOException {
        Runtime.getRuntime().exec(String.format("adb -s %s shell monkey -p %s -c android.intent.category.LAUNCHER 1", adbUrl, appPackage));
        androidDriver.findElement("xpath", "//android.widget.LinearLayout").click();
        System.out.println(androidDriver.getPageSource());
    }

    @AfterClass
    public static void afterClass() throws SonicRespException, IOException {
        Runtime.getRuntime().exec(String.format("adb disconnect %s", adbUrl));
        System.out.println("Test Finish...");
    }
}
