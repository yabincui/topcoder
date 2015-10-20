import org.junit.*;
import static org.junit.Assert.*;

public class CollectingMarblesTest {
	@Test
	public void test() {
		CollectingMarbles marbles = new CollectingMarbles();
		assertEquals(4, marbles.mostMarbles(new int[]{2,  2,  2, 2, 2}, 5, 2));
		assertEquals(4, marbles.mostMarbles(new int[]{1,  3,  5, 2, 4}, 5, 2));
		assertEquals(4, marbles.mostMarbles(new int[]{7,  6,  6, 5}, 12, 2));
		assertEquals(0, marbles.mostMarbles(new int[]{2,  2,  2}, 1, 10));
		assertEquals(13, marbles.mostMarbles(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 3, 5}, 
				20, 10));
	}
}