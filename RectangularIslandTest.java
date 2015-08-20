import org.junit.*;
import static org.junit.Assert.*;

public class RectangularIslandTest {
	RectangularIsland island = new RectangularIsland();
	
	@Test
	public void test() {
		assertEquals(0, island.theProbablity(1, 1, 0, 0, 1), 1e-9);
		assertEquals(0.25, island.theProbablity(1, 2, 0, 0, 1), 1e-9);
		assertEquals(0.75, island.theProbablity(5, 8, 4, 3, 1), 1e-9);
		assertEquals(0.5, island.theProbablity(5, 8, 4, 7, 1), 1e-9);
		assertEquals(0.03125, island.theProbablity(2, 2, 0, 1, 5), 1e-9);
		assertEquals(0.13056659769966347, island.theProbablity(58, 85, 47, 74, 1000), 1e-9);
		assertEquals(0.9868611148475199, island.theProbablity(1000, 1000, 123, 456, 5000), 1e-9);
	}
}