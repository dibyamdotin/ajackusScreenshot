package com.ajackus.screenshot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajackus.screenshot.service.ScreenShotService;

@RestController
public class ScreenshotController {
	
	@Autowired
	ScreenShotService screenShotService;

	@PostMapping("/ajackus/takescreenshot")
	public String takeScreenshot(@RequestBody String reqBody) {
		return screenShotService.takeScreenShot(reqBody);
	}

}
