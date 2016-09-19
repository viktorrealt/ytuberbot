import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception{
        String url = "https://ytuber.ru/";
        String youtubeURL = "https://accounts.google.com/ServiceLogin?continue=https%3A%2F%2Fwww.youtube.com%2Fsignin%3Fapp%3Ddesktop%26action_handle_signin%3Dtrue%26next%3D%252F%26hl%3Dru%26feature%3Dsign_in_button&hl=ru&passive=true&service=youtube&uilel=3#identifier";
        String login = ""; //email here
        String password = ""; //password here
        String youtubePassword = ""; //Youtube Password
        int countPerPage = 12; //Количество видео на 1 странице
        int totalPagesCount = 20; //Количество страниц с видео
        ArrayList<String> listOfUrls = makeUrlForDriver(url, totalPagesCount, countPerPage); //Список URL по страницам
        WebDriver driver = new FirefoxDriver();
        authorizeOnYoutube(youtubeURL, login, youtubePassword, driver);
        driver.navigate().to(url);
        WebElement loginField = driver.findElement(By.name("mail"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[3]/div/button"));
        submitButton.click();
        WebElement ytTasks = driver.findElement(By.linkText("Выполнять задачи"));
        ytTasks.click();
        Thread.sleep(400);
        WebElement ytViews = driver.findElement(By.linkText("YT Просмотры"));
        ytViews.click();
        WebDriverWait wait = new WebDriverWait(driver, 40);
        for (int i = 0; i < totalPagesCount; i++)
        {
            driver.navigate().to(listOfUrls.get(i));
            wait.until(ExpectedConditions.urlContains("https://ytuber.ru/yt/view"));
            clickVideoUrls(countPerPage, driver, 45000); //Вызов класса для прокликивания видео, передаем кол-во видео на странице и драйвер
        }


        //WebElement youtbeLoginButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div[2]/"));
        //youtbeLoginButton.click();





        //WebDriverWait wait = new WebDriverWait(driver, 40);

        //WebElement link = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//tr[1]/td[3]/a"))));
        //System.out.println(link.getText());
        driver.quit();

    }
    private static void clickVideoUrls (int countPerPage, WebDriver driver, int timeout) throws Exception {
        for (int i = 1; i <= countPerPage; i++) {
            WebElement videoURL = driver.findElement(By.xpath("//tr[" + i + "]/td[3]/a[@onclick]"));
            videoURL.click();
            Thread.sleep(timeout);
        }
    }

    private static ArrayList<String> makeUrlForDriver(String url, int totalPagesCount, int countPerPage) throws Exception
    {
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 1; i <= totalPagesCount; i++)
        {
            int temp = i*countPerPage;
            url = url + "yt/view/" + temp;
            list.add(url);
        }
        return list;
    }

    public static void authorizeOnYoutube(String youtubeURL, String login, String youtubePassword, WebDriver driver) throws Exception
    {
        driver.navigate().to(youtubeURL);
        WebElement youtubeEmailForm = driver.findElement(By.name("Email"));
        youtubeEmailForm.sendKeys(login);
        youtubeEmailForm.submit();
        Thread.sleep(200);
        WebElement youtubePasswordForm = driver.findElement(By.name("Passwd"));
        youtubePasswordForm.sendKeys(youtubePassword); //pass from youtube here
        youtubePasswordForm.submit();
    }

}
