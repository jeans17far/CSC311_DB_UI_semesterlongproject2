package service;

import java.util.prefs.Preferences;

/**
 * Singleton class for managing user sessions.
 */
public class UserSession {
    private static volatile UserSession instance;

    private final String username;
    private final String role;
    private final Preferences prefs;

    /**
     * Private constructor.
     * @param username User's username.
     * @param role     User's role.
     */
    private UserSession(String username, String role) {
        this.username = username;
        this.role = role;
        this.prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    /**
     * Gets the UserSession instance.
     * @param username User's username.
     * @param role     User's role.
     * @return The UserSession instance.
     */
    public static synchronized UserSession identifyInstance(String username, String role) {
        if (instance == null) {
            instance = new UserSession(username, role);
        }
        return instance;
    }

    /**
     * Saves user credentials.
     * @param username User's username.
     * @param password User's password.
     */
    public void saveCredentials(String username, String password) {
        prefs.put("username", username);
        prefs.put("password", password);
    }

    /**
     * Retrieves the username.
     * @return Saved username.
     */
    public String getUsername() {
        return prefs.get("username", null);
    }

    /**
     * Retrieves the password.
     * @return Saved password.
     */
    public String getPassword() {
        return prefs.get("password", null);
    }

    /**
     * Clears saved credentials.
     */
    public void clearCredentials() {
        prefs.remove("username");
        prefs.remove("password");
    }

    /**
     * Gets user role.
     * @return User role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Cleans the UserSession.
     */
    public static synchronized void cleanUserSession() {
        if (instance != null) {
            instance.clearCredentials();
            instance = null;
        }
    }

    /**
     * Returns UserSession details as a string.
     */
    @Override
    public String toString() {
        return "UserSession{username='" + this.username + "', role=" + this.role + '}';
    }
}
