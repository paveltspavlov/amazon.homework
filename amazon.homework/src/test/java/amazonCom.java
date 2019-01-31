import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.lang.String;

public class amazonCom {

        WebDriver driver;
        SoftAssert softAssert = new SoftAssert();

        @BeforeClass
        public void initBrowser(){
            System.setProperty("webdriver.chrome.driver","browsers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

        @Test
        public void openAmazon(){
            driver.navigate().to("https://www.amazon.com/");
            softAssert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com/");
            softAssert.assertAll();
        }

        @Test (dependsOnMethods = "openAmazon")
        public void findSearchField (){
            WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
                    search.click();
                    search.sendKeys("Harry Potter");

        }

        @Test (dependsOnMethods = "findSearchField")
        public void initiateSearch (){
            WebElement searchBtn = driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
            searchBtn.click();
            softAssert.assertEquals(driver.getCurrentUrl().toLowerCase(),"https://www.amazon.com/s/ref=nb_sb_noss_2/143-3116857-7649549?url=search-alias%3Daps&field-keywords=Harry+Potter");
        }

        @Test (dependsOnMethods = "initiateSearch")
        public void expandCategories () {
            WebElement expandBtn = driver.findElement(By.linkText("See All 37 Departments")).findElement(By.tagName("span"));
            expandBtn.click();
//            softAssert.notify();
        }

        @Test (dependsOnMethods = "expandCategories")
        public void findCategory (){
            WebElement category = driver.findElement(By.linkText("Toys & Games"));
            category.click();
//            softAssert.assertEquals(driver.getCurrentUrl(),"https://www.amazon.com/s/ref=sr_nr_i_42?rh=k%3AHarry+Potter%2Ci%3Atoys-and-games&keywords=Harry+Potter&ie=UTF8&qid=1537020973");
        }
        @Test (dependsOnMethods = "findCategory")
        public void fillMinPrice (){
            WebElement minPrice = driver.findElement(By.id("low-price"));
            minPrice.sendKeys("10");
            String minPriceField = minPrice.getText();
            softAssert.assertEquals(minPriceField,10);
        }
        @Test (dependsOnMethods = "fillMinPrice")
        public void fillMaxPrice(){
            WebElement maxPrice = driver.findElement(By.id("high-price"));
            maxPrice.sendKeys("150");
            String maxPriceField = maxPrice.getText();
            softAssert.assertEquals(maxPriceField,150);
        }
        @Test (dependsOnMethods = "fillMaxPrice")
        public void filterPrice(){
            WebElement goBtn = driver.findElement(By.xpath("//*[@id=\"leftNavContainer\"]/ul[4]/div/li[5]/span/form/span[3]/span")).findElement(By.linkText("Go"));
            goBtn.click();

//            WebElement assCorrCategory = driver.findElement(By.className("a-link-normal a-color-base a-text-bold a-text-normal")).findElement(By.linkText("Toys & Games"));
//            WebElement assCorrPriceRange = driver.findElement(By.className("a-link-normal a-color-base a-text-bold a-text-normal")).findElement(By.linkText("$10-$150"));
//
//            WebElement assAll = assCorrCategory +
        }
//        @Test (dependsOnMethods = "filterPrice")
//        public void filterAge{}

}
