package me.lukeis.example.pageobjects;

public interface ChatterFeedPage extends Page {
  public ChatterFeedPage createPost(String message);
  public String getStringOfLatestPost();
}
