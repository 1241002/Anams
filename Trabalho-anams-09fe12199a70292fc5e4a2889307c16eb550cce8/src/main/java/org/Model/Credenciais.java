package org.Model;

public class Credenciais {
    private String login;
    private String password;

    public Credenciais(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    @Override
    public String toString() {
        return "Login: " + login + " | Password: " + password;
    }
}