import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 9/15/16.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        String url = "https://ytuber.ru/";
        String youtubeURL = "https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Fapp%3Ddesktop%26action_handle_signin%3Dtrue%26next%3D%252F%26hl%3Dru%26feature%3Dsign_in_button&hl=ru&passive=true&service=youtube&uilel=3#identifier";
        String login = ""; //email here
        String password = ""; //password here
        int countPerPage = 12;
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to(youtubeURL);
        //WebElement youtbeLoginButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/"));
        //youtbeLoginButton.click();
        WebElement youtubeEmailForm = driver.findElement(By.name("Email"));
        youtubeEmailForm.sendKeys(login);
        youtubeEmailForm.submit();
        Thread.sleep(200);
        WebElement youtubePasswordForm = driver.findElement(By.name("Passwd"));
        youtubePasswordForm.sendKeys(""); //pass from youtube here
        youtubePasswordForm.submit();
        driver.navigate().to(url);
        WebElement loginField = driver.findElement(By.name("mail"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[3]/div/button"));
        submitButton.click();
        WebElement ytTasks = driver.findElement(By.linkText("Выполнять задачи"));
        ytTasks.click();
        WebElement ytViews = driver.findElement(By.linkText("YT Просмотры"));
        ytViews.click();
        WebDriverWait wait = new WebDriverWait(driver, 40);

        wait.until(ExpectedConditions.urlToBe("https://ytuber.ru/yt/view"));
        for (int i = 1; i <= countPerPage; i++)
        {
            WebElement videoURL = driver.findElement(By.xpath("//tr[" + i + "]/td[3]/a[@onclick]"));
            videoURL.click();
            Thread.sleep(45000);
        }


        //WebDriverWait wait = new WebDriverWait(driver, 40);

        //WebElement link = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]/td[3]/a"))));
        //System.out.println(link.getText());
        driver.quit();

    }

}
