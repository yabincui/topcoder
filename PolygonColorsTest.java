import org.junit.*;
import static org.junit.Assert.*;

public class PolygonColorsTest {
	@Test
	public void test() {
		PolygonColors colors = new PolygonColors();
		assertEquals(1, colors.getWays(3, new int[]{1, 2, 0}));
		assertEquals(3, colors.getWays(4, new int[]{1, 2, 3, 0}));
		assertEquals(0, colors.getWays(5, new int[]{0, 1, 1, 1, 1}));
		assertEquals(96791474, colors.getWays(16, new int[]{0,1,2,6,4,5,6,7,1,9,10,11,12,13,14,10}));
	}
}