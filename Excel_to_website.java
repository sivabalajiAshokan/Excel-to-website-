package com.DataRead;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel_to_website {

	public static void main(String[] args) throws IOException, InterruptedException {



		File fil = new File("C:\\Users\\Admin\\eclipse-workspace\\DataDriven_FramWork\\src\\test\\java\\sivabalaji\\Data_Read.xlsx");	

		FileInputStream fis = new FileInputStream(fil);

		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheetAt(0);

		int pnr =sheet.getLastRowNum()-sheet.getFirstRowNum();

		WebDriverManager.chromedriver();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a")).click();

		for(int i =1; i<pnr+1; i++) {

			Row row = sheet.getRow(i);

			String fn = row.getCell(0).getStringCellValue();

			String mn = row.getCell(1).getStringCellValue();

			String ln = row.getCell(2).getStringCellValue();



			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[3]/a")).click();
			driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(fn);
			driver.findElement(By.xpath("//input[@name='middleName']")).sendKeys(mn);
			driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(ln);
			driver.findElement(By.xpath("//button[@type='submit']")).click();


			System.out.println(i + fn +mn + ln);
		}
	
	}}
