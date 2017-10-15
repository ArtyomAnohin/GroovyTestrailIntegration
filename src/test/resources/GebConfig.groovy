import org.openqa.selenium.chrome.ChromeDriver

driver = {
    System.setProperty('webdriver.chrome.driver', 'chromedriver')
    new ChromeDriver()
}

reportsDir = "build/geb-reports"
reportOnTestFailureOnly = true

baseNavigatorWaiting = true
atCheckWaiting = true