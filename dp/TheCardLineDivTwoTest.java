import org.junit.*;
import static org.junit.Assert.*;

public class TheCardLineDivTwoTest {
	@Test
	public void test() {
		TheCardLineDivTwo card = new TheCardLineDivTwo();
		assertEquals(2, card.count(new String[] {
				"KH", "QD", "KC"
		}));
		assertEquals(24, card.count(new String[]
				{"JS", "JC", "JD", "JH"}
				));
		assertEquals(0, card.count(new String[]
				{"2S", "3C", "4C", "5S", "6C", "7S", "8S", "9H"}
				));
		assertEquals(2416, card.count(new String[]
				{"KD", "KC", "AD", "7C", "AH", "9C", "4H", "4S", "AS"}
				));
	}
}