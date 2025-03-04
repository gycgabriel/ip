package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * Adapted from:
 * Lum, Jeffry (2021) JavaFX tutorial. https://se-education.org/guides/tutorials/javaFx.html
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
