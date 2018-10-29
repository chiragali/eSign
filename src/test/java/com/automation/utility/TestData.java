package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public static Sheet getExcelSheet(int sheetIndex) {
		String dataFilePath = "Resources/Credential.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	public static Sheet upload(int sheetIndex) {
		String dataFilePath = "Resources/firstname.txt";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	public static String getURL() {

		System.out.println("Call getURL.......");
		return getExcelSheet(0).getRow(0).getCell(1).getStringCellValue();
	}

	public static String getUserName() {

		return getExcelSheet(0).getRow(1).getCell(1).getStringCellValue();
	}

	public static String getPassword() {

		return getExcelSheet(0).getRow(2).getCell(1).getStringCellValue();
	}

	public static String getSenderName() {

		return getExcelSheet(0).getRow(5).getCell(1).getStringCellValue();
	}
	
	public static String getRandomDate() {

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, 1);

		int fromday = cal.get(Calendar.DAY_OF_MONTH);
		int fromyear = cal.get(Calendar.YEAR);

		cal.add(Calendar.MONTH, 2);

		String day = String.valueOf(getDate(fromday));
		String month = TestData.getMonthD(cal.get(Calendar.MONTH));
		String year = String.valueOf(fromyear);

		return day + " " + month + " " + year;
	}

	public static int getDate(int fromday) {

		if (fromday < 10) {

			return 10 + (int) Math.round(Math.random() * (30 - 10));
		} else {
			return fromday;
		}
	}

	public static String getMonthD(int month) {
		switch (month) {
		case 1:
			return "Jan";

		case 2:
			return "Feb";

		case 3:
			return "Mar";

		case 4:
			return "Apr";

		case 5:
			return "May";

		case 6:
			return "Jun";

		case 7:
			return "Jul";

		case 8:
			return "Aug";

		case 9:
			return "Sep";

		case 10:
			return "Oct";

		case 11:
			return "Nov";

		case 12:
			return "Dec";

		default:
			return "Invalid month";

		}
	}

	public static String getDateConverted(String dateString) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = format1.parse(dateString);
		System.out.println(format2.format(date));
		return format2.format(date);
	}

	public static String getHeadless() {
		// TODO Auto-generated method stub
		try
		{
			return getExcelSheet(0).getRow(4).getCell(1).getStringCellValue();
		}
		catch(Exception e)
		{
			return "No";
		}	
	}
}
