import org.junit.*;
import static org.junit.Assert.*;

public class IsoscelesTriangulationsTest {
	@Test
	public void test() {
		IsoscelesTriangulations trian = new IsoscelesTriangulations();
		assertEquals(2, trian.getCount(4, 2));
		assertEquals(0, trian.getCount(3, 0));
		assertEquals(5, trian.getCount(5, 3));
		assertEquals(12, trian.getCount(6,  2));
		assertEquals(10, trian.getCount(10, 8));
		assertEquals(106887772, trian.getCount(23, 5));
	}
}