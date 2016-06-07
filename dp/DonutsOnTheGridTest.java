import org.junit.*;
import static org.junit.Assert.*;

public class DonutsOnTheGridTest {
	@Test
	public void test() {
		DonutsOnTheGrid grid = new DonutsOnTheGrid();
		assertEquals(4, grid.calc(5, 5, 222, 55555));
		assertEquals(3, grid.calc(5, 6, 121, 58000));
		assertEquals(1, grid.calc(4,  5, 6, 50000));
		assertEquals(9, grid.calc(4,  4,  1,  65536));
	}
}