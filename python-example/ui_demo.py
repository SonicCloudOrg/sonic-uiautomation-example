from common.models import AndroidSelector
from uia2.driver import AndroidDriver
import os


class TestDriver:

    def __init__(self):
        self.uia_url = "http://192.168.0.9:7777/uia/49860"
        self.adb_url = "192.168.0.9:49858"
        self.package_name = "com.android.settings"

    def test_demo(self):
        # connect device
        os.system("adb connect {}".format(self.adb_url))
        
        # launch App
        os.system(
            "adb -s {} shell monkey -p {} -c android.intent.category.LAUNCHER 1".format(self.adb_url,
                                                                                        self.package_name))
        
        # connect remote uia2 server
        driver = AndroidDriver(self.uia_url)
        p = driver.get_page_source()
        print(p)
        e = driver.find_element(AndroidSelector.XPATH, "//*[@text='设置']")
        if e is not None:
            print(e.get_text())
            e.send_keys("Hello")
