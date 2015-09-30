import org.junit.*;
import static org.junit.Assert.*;

public class EqualTowersTest {
	@Test
	public void test() {
		EqualTowers towers = new EqualTowers();
		assertEquals(5, towers.height(new int[]{2, 3, 5}));
		assertEquals(-1, towers.height(new int[]{10, 9, 2}));
		assertEquals(11, towers.height(new int[]{11, 11}));
		assertEquals(64, towers.height(new int[]{14, 3, 20, 15, 15, 14, 24, 23, 15}));
	}
}