package com.skilldistillery.filmquery.app;

import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.util.VerifyScanner;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void test() {

//		db.findFilmById(2);

		db.findFilmByKeyword("butler");

	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean running = true;

		while (running) {

			showMenu();

			String message = "Selection: ";

			int selection = VerifyScanner.intScan(input, message, -1, 3);
			input.nextLine();

			switch (selection) {
			case 0:
				running = false;
				break;
			case 1:

				message = "Please enter a movie ID: ";
				db.findFilmById(VerifyScanner.intScan(input, message, 0));

				break;
			case 2:
				System.out.print("Please enter your Search: ");
				String search = input.nextLine();
				db.findFilmByKeyword(search);
				break;
			}
		}
		System.out.println("Thank you come Again.");
	}

	private void showMenu() {
		String menu = "";

		menu += "\n       Search A Film";
		menu += "\n----------------------------";
		menu += "\n0) Exit";
		menu += "\n1) Look up a film by id.";
		menu += "\n2) Look up a film by keyWord";
		menu += "\n----------------------------";
		menu += "\n";

		System.out.println(menu);
	}

}
