package fcu.sqa.findbugs;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.List;

/*
 * 執行findbugs找出此程式中潛在的bug
 */

public class Lab3 {

	private List<String> dateList;

	/**
	 * 從access.log取出每一筆log的時間資訊
	 * for example
	 *    204.249.225.59 - - [28/Aug/1995:00:00:34 -0400] "GET /pub/rmharris/catalogs/dawsocat/intro.html HTTP/1.0" 200 3542
	 *    則時間資訊為28/Aug/1995:00:00:34
	 */
	void parseLog() {

		LineNumberReader lnr = null;
		try {
			lnr = new LineNumberReader(new FileReader(this.getClass().getResource("./access.log").getFile()));
			String line;
			while ((line = lnr.readLine()) != null) {
				String dateInfo = getDateInfo(line);
				dateList.add(dateInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 從每一行log中取出時間資訊
	 */
	String getDateInfo(String line) {
		String[] split = line.split(" ");

		String dateInfo = "";
		for (int i = 0; i < split.length; i++) {
			if (split[i].startsWith("[")) {
				dateInfo = split[i];
				break;
			}
		}

		if (dateInfo != null) {
			dateInfo.substring(1, dateInfo.length());
		}
		return dateInfo;
	}

	/**
	 * 將所有蒐集到的時間資訊印出
	 */
	void showAllDateInfo() {
		for (String dateInfo : dateList) {
			System.out.println(dateInfo);
		}
	}

	public static void main(String[] args) {
		Lab3 l3 = new Lab3();
		l3.parseLog();
		l3.showAllDateInfo();
	}

}
