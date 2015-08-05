import org.junit.*;
import static org.junit.Assert.*;

public class ParkingTest {
	Parking parking = new Parking();
	
	@Test
	public void test() {
		assertEquals(6, parking.minTime(new String[]
				{"C.....P",
						 "C.....P",
						 "C.....P"}
				));
		assertEquals(16, parking.minTime(new String[]
				{"C.X.....",
						 "..X..X..",
						 "..X..X..",
						 ".....X.P"}
		));
		assertEquals(5, parking.minTime(new String[]
				{"XXXXXXXXXXX",
						 "X......XPPX",
						 "XC...P.XPPX",
						 "X......X..X",
						 "X....C....X",
						 "XXXXXXXXXXX"}
				));
		assertEquals(4, parking.minTime(new String[]
				{".C.",
						 "...",
						 "C.C",
						 "X.X",
						 "PPP"}
				));
		assertEquals(-1, parking.minTime(new String[]
				{"CCCCC",
						 ".....",
						 "PXPXP"}
				));
		assertEquals(-1, parking.minTime(new String[]
				{"..X..",
						 "C.X.P",
						 "..X.."}
				));
	}
}