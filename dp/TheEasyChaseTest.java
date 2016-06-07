import org.junit.*;
import static org.junit.Assert.*;

public class TheEasyChaseTest {
	@Test
	public void test() {
		TheEasyChase chase = new TheEasyChase();
		assertEquals("BLACK 2", chase.winner(2, 1, 1, 2, 2));
		assertEquals("WHITE 1", chase.winner(2, 2, 2, 1, 2));
		assertEquals("BLACK 6", chase.winner(3, 1, 1, 3, 3));
	}
}