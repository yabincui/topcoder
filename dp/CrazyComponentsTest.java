import org.junit.*;
import static org.junit.Assert.*;

public class CrazyComponentsTest {
	@Test
	public void test() {
		CrazyComponents crazy = new CrazyComponents();
//		assertEquals(1.5, crazy.expectedProfit(1, new String[]{"",  ""},  new int[]{1, 2}, new int[]{0, 0}), 1e-9);
//		assertEquals(1.5, crazy.expectedProfit(2, new String[]{"1",  ""}, new int[]{10, 0}, new int[]{0, 2}), 1e-9);
//		assertEquals(7.407407407407408, crazy.expectedProfit(3, new String[]
//				{ "1 2", "", "" },
//				new int[]{ 100, 0, 0 },
//				new int[]{ 0, 0, 0 }), 1e-9);
		
		assertEquals(301.23489487382017, crazy.expectedProfit(17, new String[]{
				"1 2 3 4 5", "", "", "", "", ""
		}, new int[]{1000, 0, 0, 0, 0, 0}, new int[]{0, 100, 100, 100, 100, 100}), 1e-9);
	}
}