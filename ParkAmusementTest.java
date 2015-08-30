import org.junit.*;
import static org.junit.Assert.*;

public class ParkAmusementTest {
	@Test
	public void test() {
		ParkAmusement park = new ParkAmusement();
		
		assertEquals(0.3333333333333333, park.getProbability(new String[]
				{"E000",
						 "1000",
						 "1000",
						 "1000"}, 1, 1), 1e-9);
		assertEquals(0.6666666666666666, park.getProbability(new String[]
				{"E000",
						 "1000",
						 "1001",
						 "000P"}, 1, 1), 1e-9);
		assertEquals(0.14285714285714288, park.getProbability(new String[]
				{"01000100",
						 "00111000",
						 "00001010",
						 "000E0000",
						 "0000E000",
						 "00000P00",
						 "000000P0",
						 "01000000"}, 1, 2), 1e-9);
		assertEquals(0.0, park.getProbability(new String[]
				{"0100",
						 "0010",
						 "0001",
						 "000E"}, 0, 2), 1e-9);
		assertEquals(0.0, park.getProbability(new String[]
				{"E00",
						 "0E0",
						 "010"}, 0, 1), 1e-9);
	}
}