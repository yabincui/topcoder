import org.junit.*;
import static org.junit.Assert.*;

public class MarbleCollectionGameTest {
	@Test
	public void test() {
		MarbleCollectionGame game = new MarbleCollectionGame();
		assertEquals(7, game.collectMarble(new String[]{"7"}));
		assertEquals(8, game.collectMarble(new String[]{"0",
 "8"}));
		assertEquals(15, game.collectMarble(new String[]{"264",
 "3LL"}
));
		assertEquals(44, game.collectMarble(new String[]{"8U4L9",
 "0183U",
 "U8#38",
 "2158#",
 "L65U7"}
));
		assertEquals(55, game.collectMarble(new String[]{"039LLLU",
 "953348#",
 "0L87#29",
 "718#4#U",
 "594U196"}));
	}
}