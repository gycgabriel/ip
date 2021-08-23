package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private final PrintStream stdout = System.out;
    private final InputStream stdin = System.in;

    public void resetStreams() {
        System.setIn(stdin);
        System.setOut(stdout);
    }

    @Test
    public void ByeTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String data = "bye\n";
        System.setOut(new PrintStream(outputStream));
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        new Duke("data/test.txt").run();
        resetStreams();
        Ui ui = new Ui();
        String expected = (ui.LOGO + "\n" +
                "__________________________________________________________________________________\n" +
                "|  Cat:  Meow-ning!\n" +
                "__________________________________________________________________________________\n" +
                "\n" +
                "__________________________________________________________________________________\n" +
                "|  Cat:  See you again, meow!\n" +
                "__________________________________________________________________________________\n" +
                "\n").replaceAll("\n", "").replaceAll("\r", "");
        ;

        assertEquals(expected, outputStream.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

    @Test
    public void EventTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String data = "event christmas day /at 25Dec\nbye\n";
        System.setOut(new PrintStream(outputStream));
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        new Duke("data/test.txt").run();
        resetStreams();
        Ui ui = new Ui();
        String expected = (ui.LOGO + "\n" +
                "__________________________________________________________________________________\n" +
                "|  Cat:  Meow-ning!\n" +
                "__________________________________________________________________________________\n" +
                "\n" +
                "__________________________________________________________________________________\n" +
                "|  Cat:  Meow. I've added this task:\n" +
                "   [E][ ] christmas day (at: Dec 25 2021 All day)\n" +
                "|  Now you have 1 tasks in the list.\n" +
                "__________________________________________________________________________________\n" +
                "\n" +
                "__________________________________________________________________________________\n" +
                "|  Cat:  See you again, meow!\n" +
                "__________________________________________________________________________________\n" +
                "\n").replaceAll("\n", "").replaceAll("\r", "");
        ;

        assertEquals(expected, outputStream.toString().replaceAll("\n", "").replaceAll("\r", ""));
    }

}
