import org.junit.*;
import static org.junit.Assert.*;

public class GiftsTest {
	@Test
	public void test1() {
		Gifts gifts = new Gifts();
		assertEquals(1, gifts.maxGifts(new String[]
				{"#######",
						 "#K.G.Q#",
						 "#######"}
				, 6));
		assertEquals(0, gifts.maxGifts(new String[]
				{"#######",
						 "#K.G.Q#",
						 "#######"}
				, 4));
		assertEquals(0, gifts.maxGifts(new String[]
				{"#######",
						 "#K.Q.G#",
						 "#######"}
				, 6));
		assertEquals(1, gifts.maxGifts(new String[]
				{"#######",
						 "#K.Q.G#",
						 "#######"}
				, 8));
		assertEquals(2, gifts.maxGifts(new String[]
				{"#######",
						 "#K.QGG#",
						 "#######"}
				, 9));
		assertEquals(4, gifts.maxGifts(new String[]
				{"#....G#", 
						 "###G###", 
						 "#K...Q#", 
						 "###.###", 
						 "#G..GG#"}
				, 50));
	}
}