import org.junit.*;
import static org.junit.Assert.*;

public class NumbersAndMatchesTest {
	@Test
	public void test() {
		NumbersAndMatches match = new NumbersAndMatches();
		assertEquals(4, match.differentNumbers(10, 1));
		assertEquals(4, match.differentNumbers(23, 1));
		assertEquals(15, match.differentNumbers(66, 2));
		assertEquals(1, match.differentNumbers(888888888L, 100));
		assertEquals(1, match.differentNumbers(444444444444444444L, 2));
	}
}