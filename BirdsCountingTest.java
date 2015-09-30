import org.junit.*;
import static org.junit.Assert.*;

public class BirdsCountingTest {
	@Test
	public void test() {
		BirdsCounting bird = new BirdsCounting();
		assertEquals(0.6666666666666666, bird.computeProbability(3, 1, 2, 2), 1e-9);
		assertEquals(0.012345679012345678, bird.computeProbability(3, 1, 5, 1), 1e-9);
		assertEquals(0.2582908163265306, bird.computeProbability(8, 3, 3, 7), 1e-9);
		assertEquals(0.6, bird.computeProbability(5, 3, 2, 4), 1e-9);
		assertEquals(0.30035494805367574, bird.computeProbability(20, 6, 5, 17), 1e-9);
	}
}