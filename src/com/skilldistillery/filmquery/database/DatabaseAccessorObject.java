package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";

	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		String USER = "student";
		String PASS = "student";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASS);

			String query;
			query = "Select film.*, language.name ";
			query += "FROM film ";
			query += "Join language ON film.language_id = language.id ";
			query += "WHERE film.id = ?";

			statement = connection.prepareStatement(query);
			statement.setInt(1, filmId);

			result = statement.executeQuery();

			if (result.next()) {

				int id = result.getInt("id");
				String title = result.getString("title");
				String description = result.getString("description");
				int releaseYear = result.getInt("release_year");
				String language = result.getString("language.name");
				int rentalDuration = result.getInt("rental_duration");
				double rentalRate = result.getDouble("rental_rate");
				int length = result.getInt("length");
				double replacementCost = result.getDouble("replacement_cost");
				String rating = result.getString("rating");
				String specialFeatures = result.getString("special_features");

				film = new Film(id, title, description, releaseYear, language, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures);

				film.setActors(findActorsByFilmId(id));

				film.showInfo();
			} else {
				System.out.println("\nSorry, film is unavailable.");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				} // Not needed, stmt.close() will close it; but good practice
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}

		return film;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		Film film = null;

		String USER = "student";
		String PASS = "student";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASS);

			String query = "";

			query += "Select film.*, language.name ";
			query += "FROM film ";
			query += "Join language ON film.language_id = language.id ";
			query += "WHERE film.title Like ? OR film.description like ?";

			statement = connection.prepareStatement(query);
			statement.setString(1, "%" + keyword + "%");
			statement.setString(2, "%" + keyword + "%");

			result = statement.executeQuery();

			while (result.next()) {

				int id = result.getInt("id");
				String title = result.getString("title");
				String description = result.getString("description");
				int releaseYear = result.getInt("release_year");
				String language = result.getString("language.name");
				int rentalDuration = result.getInt("rental_duration");
				double rentalRate = result.getDouble("rental_rate");
				int length = result.getInt("length");
				double replacementCost = result.getDouble("replacement_cost");
				String rating = result.getString("rating");
				String specialFeatures = result.getString("special_features");

				film = new Film(id, title, description, releaseYear, language, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures);

				film.setActors(findActorsByFilmId(id));

				films.add(film);
				film.showInfo();

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				} // Not needed, stmt.close() will close it; but good practice
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}

		if (films.isEmpty()) {
			System.out.println("Sorry, No films found.");
		}

		return films;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String USER = "student";
		String PASS = "student";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASS);

			String sql = "SELECT * FROM actor WHERE id = ?";

			statement = connection.prepareStatement(sql);
			statement.setInt(1, actorId);

			result = statement.executeQuery();

			if (result.next()) {
				actor = new Actor();
				actor.setId(result.getInt("id"));
				actor.setFirstName(result.getString("first_name"));
				actor.setLastName(result.getString("last_name"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				} // Not needed, stmt.close() will close it; but good practice
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}
		return actor;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;
		String USER = "student";
		String PASS = "student";

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {

			connection = DriverManager.getConnection(URL, USER, PASS);

			String query = "";
			query += "Select actor.* ";
			query += "From actor ";
			query += "Join film_actor ON actor.id = film_actor.actor_id ";
			query += "Where film_id = ?";

			statement = connection.prepareStatement(query);
			statement.setInt(1, filmId);

			result = statement.executeQuery();

			while (result.next()) {
				actor = new Actor();
				actor.setId(result.getInt("id"));
				actor.setFirstName(result.getString("first_name"));
				actor.setLastName(result.getString("last_name"));
				actors.add(actor);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (result != null) {
					result.close();
				} // Not needed, stmt.close() will close it; but good practice
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}
		return actors;
	}



}
