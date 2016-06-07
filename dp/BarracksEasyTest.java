import org.junit.*;
import static org.junit.Assert.*;

public class BarracksEasyTest {
	@Test
	public void test() {
		BarracksEasy easy = new BarracksEasy();
		assertEquals(4, easy.attack(10, 11, 15));
		assertEquals(-1, easy.attack(3, 10, 4));
		assertEquals(9, easy.attack(2, 10, 1));
		assertEquals(2, easy.attack(11, 12, 9));
	}
}