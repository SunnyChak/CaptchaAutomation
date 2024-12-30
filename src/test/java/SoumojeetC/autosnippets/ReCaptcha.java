package SoumojeetC.autosnippets;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class ReCaptcha {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		// Initiate chrome browser
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		try {
		driver.get("https://2captcha.com/demo/normal");
	
		//Locate the captcha
		WebElement captchaImage = driver.findElement(By.xpath("//img[@alt='normal captcha example']"));
		
		//Take a screenshot of the CAPTCHA
		File captchaFile = captchaImage.getScreenshotAs(OutputType.FILE);
		File savedCapctha = new File("captcha.png");
		ImageIO.write(ImageIO.read(captchaFile), "png", savedCapctha);
		
		System.out.println("Captcha image saved as 'captcha.png'. Please open it, solve it and enter the text:");
		
		//Pause to allow manual Captcha into the input field
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Captcha text: ");
		String captchaText = scanner.nextLine();
		
		//Enter the solved Captcha into the input field
		
		WebElement captchaInput = driver.findElement(By.xpath("//input[@id='simple-captcha-field']"));
		captchaInput.sendKeys(captchaText);
		System.out.println(captchaText);
		
		//Click Check
		WebElement checkButton = driver.findElement(By.cssSelector("button[type='submit']"));
		checkButton.click();
		Thread.sleep(5000);
		
		
		//Print success message
		
		String successMsg = driver.findElement(By.xpath("//p[@class='_successMessage_rrn3u_1']")).getText();
		
		
		System.out.println(successMsg);
		Assert.assertEquals("Captcha is passed successfully!", successMsg);
		
		
		} catch(IOException e) {
			System.err.println("Error saving Captcha image:" + e.getMessage());
			
		} finally {
			driver.quit();
		}
		
				
	}	
		
			
		
}
