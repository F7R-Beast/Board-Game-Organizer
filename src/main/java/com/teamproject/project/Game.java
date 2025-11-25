package com.teamproject.project;

import java.time.LocalDate;

public class Game {
    private int id;

    private String title;
    private int yearPublished;
    private int minPlayers;
    private int maxPlayers;
    private int minPlayed;
    private double complexity;
    private String userName;

    public Game(String title, int yearPublished, int minPlayers, int maxPlayers, int minPlayed, double complexity) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minPlayed = minPlayed;
        this.complexity = complexity;
    }

    public Game(String title, int yearPublished, int minPlayers, int maxPlayers, int minPlayed, double complexity, String userName) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minPlayed = minPlayed;
        this.complexity = complexity;
        this.userName = userName;
    }

    public Game(int id, String title, int yearPublished, int minPlayers, int maxPlayers, int minPlayed, double complexity, String userName) {
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minPlayed = minPlayed;
        this.complexity = complexity;
        this.userName = userName;
    }

    public void addMinPlayed(int minPlayed) {
        if (minPlayed < 0) {
            throw new IllegalArgumentException("Played minutes cannot be negative");
        }
        this.minPlayed += minPlayed;
    }



//Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setYearPublished(int yearPublished) {
        if (yearPublished < -300 || yearPublished > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Year must be realistic");
        }
        this.yearPublished = yearPublished;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        if (minPlayers <= 0) {
            throw new IllegalArgumentException("Minimum players must be greater than 0");
        }
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        if (maxPlayers < minPlayers) {
            throw new IllegalArgumentException("Max players cannot be less than min players");
        }
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayed() {
        return minPlayed;
    }

    public void setMinPlayed(int minPlayed) {
        if (minPlayed < 0) {
            throw new IllegalArgumentException("Played minutes cannot be negative");
        }
        this.minPlayed = minPlayed;
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        if (complexity < 1.0 || complexity > 5.0) {
            throw new IllegalArgumentException("Complexity must be between 1.0 and 5.0");
        }
        this.complexity = complexity;
    }
}
