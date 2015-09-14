import org.junit.*;
import static org.junit.Assert.*;

public class RotatingTrianglesTest {
	@Test
	public void test() {
		RotatingTriangles rotating = new RotatingTriangles();
		assertEquals(10, rotating.count(new String[]
				{"//"}
				));
		assertEquals(5, rotating.count(new String[]
				{"#//#./#/#",
						 "####.#/##",
						 "...../#.#",
						 ".....####"}
				));
		assertEquals(0, rotating.count(new String[]
				{".#.",
						 "#/#",
						 ".#."}
				));
		assertEquals(46, rotating.count(new String[]
				{".../...",
						 "..///./",
						 ".//#/./"}
				));
		
	}
}