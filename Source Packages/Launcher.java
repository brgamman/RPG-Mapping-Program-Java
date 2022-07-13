/**
 * This class enables the application to be exported to a JAR executable on devices without JavaFX installed separately.
 * Without it the application fails to launch; see below sources for explanation.
 * Reference sources: https://taylorial.com/cs1021/Jar.htm and https://stackoverflow.com/a/52654791/3956070
 */
public class Launcher {
    public static void main(String[] args) {
        MAPP.main(args);
    }
}
