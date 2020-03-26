package com.ajackus.screenshot.service;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Service
public class ScreenShotServiceImpl implements ScreenShotService {

	@Override
	public String takeScreenShot(String reqString) {

		String response = null;
		JSONObject reqJson = new JSONObject(reqString);
		if(reqJson.has("url")) {

			try {
				String url = reqJson.getString("url");
				System.setProperty("webdriver.chrome.driver", "F://dibyam//chromedriver_win32//chromedriver.exe");
				WebDriver driver  = new ChromeDriver();	

				driver.get(url);
				driver.manage().window().maximize();

				Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);

				ImageIO.write(screenshot.getImage(), "jpg", new File("F://dibyam//ElementScreenshot.jpg"));
				driver.manage().window().setPosition(new Point(-2000, 0));
				response = "Screenshot successfully created";
			} catch(IOException e) {
				System.out.println("Exception occurs = "+e);
				response = "Error occurs";
			}
		} else {
			response = "Request doesnot contain url";
		}
		return response;
	}

}
