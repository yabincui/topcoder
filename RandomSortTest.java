import org.junit.*;
import static org.junit.Assert.*;

public class RandomSortTest {
	@Test
	public void test() {
		RandomSort sort = new RandomSort();
		assertEquals(1.0, sort.getExpected(new int[]{1, 3, 2}), 1e-9);
		assertEquals(4.066666666666666, sort.getExpected(new int[]{4, 3, 2, 1}), 1e-9);
		assertEquals(0.0, sort.getExpected(new int[]{1}), 1e-9);
		assertEquals(5.666666666666666, sort.getExpected(new int[]{2, 5, 1, 6, 3, 4}), 1e-9);
		assertEquals(8.685714285714287, sort.getExpected(new int[]{1, 5, 3, 7, 8, 6, 2, 4}), 1e-9);
	}
}