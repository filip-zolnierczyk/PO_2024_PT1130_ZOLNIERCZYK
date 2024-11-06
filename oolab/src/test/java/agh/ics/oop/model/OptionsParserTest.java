package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptionsParserTest {

    @Test
    public void testValidDirections() {
        String[] input = {"f", "b", "r", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        );
        assertEquals(expected, OptionsParser.parse(input));
    }

    @Test
    public void testInvalidDirections() {
        String[] input = {"f", "invalid", "b"};
        Executable parseAction = () -> OptionsParser.parse(input);
        assertThrows(IllegalArgumentException.class, parseAction, "Invalid move direction: invalid");
    }

    @Test
    public void testMixedValidAndInvalidDirections() {
        String[] input = {"f", "invalid", "b", "unknown", "r"};
        Executable parseAction = () -> OptionsParser.parse(input);
        assertThrows(IllegalArgumentException.class, parseAction, "Invalid move direction: invalid");
    }

    @Test
    public void testEmptyInput() {
        String[] input = {};
        List<MoveDirection> expected = List.of();
        assertEquals(expected, OptionsParser.parse(input));
    }
}
