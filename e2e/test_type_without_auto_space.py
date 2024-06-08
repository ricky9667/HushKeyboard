import time
import unittest

from appium import webdriver
from appium.options.android import UiAutomator2Options
from appium.webdriver.common.appiumby import AppiumBy

options = UiAutomator2Options()
options.platformName = 'Android'
options.device_name = 'Pixel_3a_API_34_extension_level_7_x86_64'
options.app = '/Users/oejk9/Desktop/HushKeyboard/app/release/app-release.apk'
appium_server_url = 'http://127.0.0.1:4723/wd/hub'


class TestAppium(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Remote(appium_server_url, options=options)
        self.driver.implicitly_wait(15)

    def tearDown(self):
        self.disable_hush_keyboard()

        if self.driver:
            self.driver.quit()

    def test_switch_default_keyboard_to_hush_keyboard(self):
        self.enable_hush_keyboard()

        self.switch_hush_keyboard_to_input_keyboard()

        self.turn_off_the_auto_space()

        self.enter_R_U_F_L_D_B_in_sequence()

    def disable_hush_keyboard(self):
        # switch Hush Keyboard to disable
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Enable keyboard"]')
        el.click()
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Hush Keyboard"]')
        el.click()

    def enable_hush_keyboard(self):
        # click Enable keyboard bottom
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Enable keyboard"]')
        el.click()
        # check Hush Keyboard is disabled and switch to enable
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Hush Keyboard"]')
        self.assertEqual(el.get_attribute('checked'), 'false')
        el.click()
        # click ok to agree notification
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="OK"]')
        el.click()
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="OK"]')
        el.click()
        # check Hush Keyboard is enabled
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Hush Keyboard"]')
        self.assertEqual(el.get_attribute('checked'), 'true')
        # return to previous page
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Navigate up"]')
        el.click()

    def switch_hush_keyboard_to_input_keyboard(self):
        # click Select input method bottom and select Hush Keyboard
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Select input method"]')
        el.click()
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@resource-id="android:id/text1" and @text="Hush '
                                                               'Keyboard"]/parent::*')
        el.click()

    def turn_off_the_auto_space(self):
        # enter to settings page
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Settings"]')
        el.click()

        # check Add space after notation is off
        el = self.driver.find_element(by=AppiumBy.XPATH,
                                      value='//*[@text="Add space after notation"]/preceding-sibling::*['
                                            '@checkable="true"]')
        el.click()
        self.assertEqual(el.get_attribute('checked'), 'false')
        self.driver.back()

    def enter_R_U_F_L_D_B_in_sequence(self):
        # input R U F L D B
        el = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Type here"]/parent::*')
        el.click()
        time.sleep(2)
        self.driver.tap([(150, 1630)])
        self.driver.tap([(300, 1630)])
        self.driver.tap([(450, 1630)])
        self.driver.tap([(600, 1630)])
        self.driver.tap([(750, 1630)])
        self.driver.tap([(900, 1630)])
        self.assertEqual(el.get_attribute('text'), 'RUFLDB')
        self.driver.back()


if __name__ == '__main__':
    unittest.main()
