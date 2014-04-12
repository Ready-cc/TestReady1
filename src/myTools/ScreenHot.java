package myTools;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenHot {

	public static File screenShot(WebDriver driver, String caseName) {

		String dir_name = "E:/screenshot"; // 这里定义了截图存放目录名
		File Screenfile = null;
		if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
			new File(dir_name).mkdir(); // 如果不存在则新建一个目录
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time = sdf.format(new Date()); // 这里格式化当前时间，例如20120406-165210，后面用的着

		try {
			File source_file = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			Screenfile = new File(dir_name + File.separator + time + caseName
					+ ".png");// 关键代码，执行屏幕截图，默认会把截图保存到temp目录
			FileUtils.copyFile(source_file, Screenfile); // 这里将截图另存到我们需要保存的目录，例如screenshot\20120406-165210.png
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Screenfile.getAbsoluteFile();
	}

	public static File screentest(WebDriver driver, String caseName) {

		String dir_name = "E:/testshot"; // 这里定义了截图存放目录名
		File Screenfile = null;
		if (!(new File(dir_name).isDirectory())) { // 判断是否存在该目录
			new File(dir_name).mkdir(); // 如果不存在则新建一个目录
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String time = sdf.format(new Date()); // 这里格式化当前时间，例如20120406-165210，后面用的着

		try {
			File source_file = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			Screenfile = new File(dir_name + File.separator + time + caseName
					+ ".png");// 关键代码，执行屏幕截图，默认会把截图保存到temp目录
			FileUtils.copyFile(source_file, Screenfile); // 这里将截图另存到我们需要保存的目录，例如screenshot\20120406-165210.png
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Screenfile.getAbsoluteFile();
	}

}
