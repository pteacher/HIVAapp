package com.example.hivaapp.models;

public class Task {
    String author;
    String category;
    String date;
    String status;
    String suggestion_text;
    private Boolean isUserAction = false;

    public Task() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSuggestion_text() {
        return suggestion_text;
    }

    public void setSuggestion_text(String suggestion_text) {
        this.suggestion_text = suggestion_text;
    }

    public Boolean getUserAction() {
        return isUserAction;
    }

    public void setUserAction(Boolean userAction) {
        isUserAction = userAction;
    }
}
