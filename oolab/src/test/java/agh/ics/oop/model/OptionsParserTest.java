package agh.ics.oop.model;
import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OptionsParserTest {
    @Test
    public void testParser() {
        String[] input = {"f", "invalid", "b", "unknown", "r"};
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT
        };
        assertArrayEquals(expected, OptionsParser.parse(input));
    }
}
