package com.greenback.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class CodeExercise {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);

		System.out.println("Welcome to the Greenback admin panel.\n");
		System.out.println(
				"Please indicate which HTML invoice you'd like to convert over to JSON by choosing one of the options below.\n");
		System.out.println("Type 1: To convert invoice1.html into a invoice1.json file.");
		System.out.println("Type 2: To convert invoice2.html into a invoice2.json file.");
		int input = scnr.nextInt();

		System.out.println(
				"\nConversion complete. Please note that the new JSON file will be found within your database (application package).");

		String userInvoice = null;

		// Pulling raw HTML data via greenback's github repo
		if (input == 1) {
			userInvoice = "https://raw.githubusercontent.com/greenback-inc/software-engineer-exercise/master/invoice1.html";

		} else if (input == 2) {
			userInvoice = "https://raw.githubusercontent.com/greenback-inc/software-engineer-exercise/master/invoice2.html";

		}

		try {
			// Needed to fetch and parse an HTML doc from the web, and to find data within
			// it
			Document doc = Jsoup.connect(userInvoice).get();
			Element content = doc.getElementById("main");
			String title = doc.title();

			Elements ref = content.getElementsContainingOwnText("Invoice");
			Elements dateElement = content.getElementsContainingOwnText("Date");
			Elements grandTotal = content.getElementsContainingOwnText("Grand Total");

			String invoice = ref.text().split(":")[1].trim();
			String date = dateElement.text().split(":")[1].trim();
			String currencyLine = grandTotal.text();

			String[] currencyArray = currencyLine.split(":");
			String[] dollarAmount = currencyArray[1].trim().split(" ");

			// Default currency set to USD
			String currency = "USD";

			if (dollarAmount.length > 1) {
				currency = dollarAmount[1];
			}

			String amount = dollarAmount[0];

			// Date formatting to match greenback's JSON example
			Date tempDate = DateUtility.parseDate(date);
			date = DateUtility.formatDate(tempDate, "yyyy-MM-dd");

			Invoice item = new Invoice(invoice, date, currency, amount);

			// Used to convert java object to JSON
			Gson gson = new Gson();

			title = title.substring(title.indexOf("#") + 1);

			// Creating the .json file
			FileWriter file = new FileWriter("invoice".concat(title).concat(".json"));

			gson.toJson(item, file);

			// Needed to flush the buffer stream
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();

		}

		scnr.close();
	}

}