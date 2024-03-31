package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private String language;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> actors;

	public Film() {
	}

	public Film(int id, String title, String description, int releaseYear, String language, int rentalDuration,
			double rentalRate2, int length, double replacementCost, String rating, String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate2;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, language, length, rating, releaseYear, rentalRate, replacementCost,
				specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && id == other.id && language == other.language
				&& length == other.length && Objects.equals(rating, other.rating) && releaseYear == other.releaseYear
				&& rentalRate == other.rentalRate
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	public List<Actor> getActors() {
		return actors;
	};

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}

	public String getLanguageId() {
		return language;
	}

	public int getLength() {
		return length;
	}

	public String getRating() {
		return rating;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public String getTitle() {
		return title;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setLanguageId(String language) {
		this.language = language;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public void setRentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActorsStr() {

		String actorsStr = "";

		for (Actor actor : actors) {
			actorsStr += "\n";
			actorsStr += actor.getFirstName() + " " + actor.getLastName();
		}

		return actorsStr;
	}

	public void showInfo() {
		String filmStr = "";

		filmStr += "\n-------------------------------------------------------";
		filmStr += "-------------------------------------------------------";
		filmStr += "\nTitle: " + getTitle();
		filmStr += "\nRating: " + getRating();
		filmStr += "\nLanguage: " + getLanguage();
		filmStr += "\nRelease Year: " + getReleaseYear();
		filmStr += "\nDescription: 1" + getDescription();
		filmStr += "\n----------Cast----------";
		filmStr += getActorsStr();
		filmStr += "\n-------------------------------------------------------";
		filmStr += "-------------------------------------------------------";


		System.out.println(filmStr);
	}

	@Override
	public String toString() {

		String actorsStr = "";

		for (Actor actor : actors) {
			actorsStr += "\n";
			actorsStr += "ID: " + actor.getId() + " - " + actor.getFirstName() + " " + actor.getLastName();

		}

		return "Film \nid: " + id + "\nTitle: " + title + "\nDescription: " + description + "\nRelease Year: "
				+ releaseYear + "\nLanguage: " + language + "\nRental Rate: " + rentalRate + "\nLength: " + length
				+ "\nReplacement Cost: " + replacementCost + "\nRating: " + rating + "\nSpecial Features: "
				+ specialFeatures + "\n----------Actors----------" + actorsStr;
	}
}
