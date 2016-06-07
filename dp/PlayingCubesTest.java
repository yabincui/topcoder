import org.junit.*;
import static org.junit.Assert.*;

public class PlayingCubesTest {
	PlayingCubes cubes = new PlayingCubes();
	
	@Test
	public void test() {
		assertArrayEquals(new int[]{1}, cubes.composeWords(new String[]
				{"ABCDEF", "DEFGHI", "OPQRST", "ZZZZZZ", "YYYYYY"}, new String[]
		{"CAT", "DOG", "PIZZA"}));
		assertArrayEquals(new int[]{0, 1, 3, 5}, cubes.composeWords(new String[]
				{"ABCDEF", "DEFGHI", "OPQRST", "MNZLSA", "QEIOGH", "IARJGS"}, new String[]
		{"DOG", "CAT", "MOUSE", "BIRD", "CHICKEN", "PIG", "ANIMAL"}));
		assertArrayEquals(new int[]{0, 1, 2}, cubes.composeWords(new String[]
				{"AAAAAA", "AAAAAA", "AAAAAA", "AAAAAA"}, new String[]
		{"AA", "AAA", "AAAA", "AAAAA", "AAAAAA"}));
		assertArrayEquals(new int[]{1, 2}, cubes.composeWords(new String[]
				{"ABCDEF", "DEFGHI", "OPQRST", "ZZZZZZ", "ZZZZZZ"}, new String[]
		{"CAT", "DOG", "PIZZA"}));
	}
}