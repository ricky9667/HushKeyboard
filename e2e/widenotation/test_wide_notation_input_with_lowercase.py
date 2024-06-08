import time
import unittest

from appium import webdriver
from appium.options.android import UiAutomator2Options
from appium.webdriver.common.appiumby import AppiumBy

options = UiAutomator2Options()
options.platformName = 'Android'
options.device_name = 'Pixel_3a_API_34_extension_level_7_x86_64'
options.app = 'D:/STV/HushKeyboard/app/build/outputs/apk/debug/app-debug.apk'
appium_server_url = 'http://127.0.0.1:4723/wd/hub'

class TestWideNotationInputWithLowercase(unittest.TestCase):
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

        self.toggle_wide_notation_setting()

        self.enter_r_u_f_l_d_b_in_sequence()

    def disable_hush_keyboard(self):
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Enable keyboard"]').click()
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Hush Keyboard"]').click()

    def enable_hush_keyboard(self):
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Enable keyboard"]').click()
        enable = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Hush Keyboard"]')
        self.assertEqual(enable.get_attribute('checked'), 'false')
        enable.click()
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="OK"]').click()
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="OK"]').click()
        enable = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Hush Keyboard"]')
        self.assertEqual(enable.get_attribute('checked'), 'true')
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Navigate up"]').click()

    def switch_hush_keyboard_to_input_keyboard(self):
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Select input method"]').click()
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@resource-id="android:id/text1" and @text="Hush '
                                                               'Keyboard"]/parent::*').click()

    def toggle_wide_notation_setting(self):
        self.driver.find_element(by=AppiumBy.XPATH, value='//*[@content-desc="Settings"]').click()
        time.sleep(2)
        self.driver.tap([(950, 470)])
        time.sleep(1)
        self.driver.tap([(840, 700)])
        # check if wide notation is using lowercase
        wide_notation  = self.driver.find_element(by=AppiumBy.XPATH,
                                      value='//*[@text="Wide notation"]/preceding-sibling::*['
                                            '@text="Use lowercase (r)"]')
        self.assertEqual(wide_notation .get_attribute('text'), 'Use lowercase (r)')
        self.driver.back()

    def enter_r_u_f_l_d_b_in_sequence(self):
        text = self.driver.find_element(by=AppiumBy.XPATH, value='//*[@text="Type here"]/parent::*')
        text.click()
        time.sleep(2)
        self.driver.tap([(610, 1930)])
        self.driver.tap([(150, 1630)])
        self.driver.tap([(300, 1630)])
        self.driver.tap([(450, 1630)])
        self.driver.tap([(600, 1630)])
        self.driver.tap([(750, 1630)])
        self.driver.tap([(900, 1630)])
        self.assertEqual(text.get_attribute('text'), 'r u f l d b ')
        self.driver.back()


if __name__ == '__main__':
    unittest.main()