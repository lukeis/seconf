package me.lukeis.example.pageobjects;

public interface LoginPage extends Page {
  public ChatterFeedPage login(String username, String password);
}
