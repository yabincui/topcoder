import org.junit.*;
import static org.junit.Assert.*;

public class TriviaGameTest {
	@Test
	public void test() {
		TriviaGame game = new TriviaGame();
		assertEquals(29, game.maximumScore(new int[]{1, 2, 3, 4, 5, 6},
			3, new int[]{4, 4, 4, 4, 4, 4}));
		assertEquals(39, game.maximumScore(new int[]{1, 2, 3, 4, 5, 6},
		3, new int[]{1, 1, 1, 20, 1, 1}));
		assertEquals(500, game.maximumScore(new int[]{150, 20, 30, 40, 50},
		3, new int[]{0, 0, 0, 250, 0}));
		assertEquals(2000, game.maximumScore(new int[]{500, 500, 500, 0, 500},
3, new int[]
{0, 0, 0, 500, 0}));
	}
}