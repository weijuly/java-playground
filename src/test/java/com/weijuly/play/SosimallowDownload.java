package com.weijuly.play;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SosimallowDownload {

	static int counter = 264;

	@Test
	public void download() throws Exception {

		System.setProperty("webdriver.chrome.driver", "/Users/gganesan/mac/.bin/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get("http://sosimallow.blogspot.in/2011/05/taeyeon-dream-concert-may-22-2010.html");

		List<WebElement> posts = driver.findElements(By.xpath("//ul[@class='posts']/li/a"));
		List<String> pages = new ArrayList<String>();
		for (WebElement post : posts) {
			System.out.println("page: " + post.getAttribute("href"));
			pages.add(post.getAttribute("href"));
		}
		
		Thread.sleep(5000);
		driver.quit();

		for (String page : pages) {
			images(page);
		}

	}

	private void images(String page) throws Exception {
		System.setProperty("webdriver.chrome.driver", "/Users/gganesan/mac/.bin/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get(page);

		List<WebElement> posts = driver.findElements(By.xpath("//a[@style='margin-left: 1em; margin-right: 1em;']"));
		for (WebElement post : posts) {

			System.out.println("image: " + post.getAttribute("href"));

			URL url = new URL(post.getAttribute("href"));
			ReadableByteChannel channel = Channels.newChannel(url.openStream());
			String output = String.format("downloads/tae-yeon-%03d.jpg", counter);

			System.out.println("file: " + output);

			FileOutputStream stream = new FileOutputStream(output);
			stream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
			stream.close();
			channel.close();
			counter += 1;
		}
		driver.quit();
	}

}
