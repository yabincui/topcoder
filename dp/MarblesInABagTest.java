import org.junit.*;
import static org.junit.Assert.*;

public class MarblesInABagTest {
	@Test
	public void test() {
		MarblesInABag bag = new MarblesInABag();
		assertEquals(0.3333333333333333, bag.getProbability(1, 2), 1e-9);
		assertEquals(0.13333333333333333, bag.getProbability(2, 3), 1e-9);
		assertEquals(0.22857142857142856, bag.getProbability(2, 5), 1e-9);
		assertEquals(0.0, bag.getProbability(11, 6), 1e-9);
		assertEquals(0.12183372183372182, bag.getProbability(4, 11), 1e-9);
	}
}