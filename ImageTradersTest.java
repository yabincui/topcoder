import org.junit.*;
import static org.junit.Assert.*;

public class ImageTradersTest {
	@Test
	public void test() {
		ImageTraders trader = new ImageTraders();
		assertEquals(2, trader.maxOwners(new String[]{
				"01",
				"10"	
		}));
		assertEquals(2, trader.maxOwners(new String[]{
				"022",
"101",
"110"
		}));
		assertEquals(4, trader.maxOwners(new String[]{
				"01231",
"00231",
"00031",
"00002",
"00000"
		}));
		assertEquals(3, trader.maxOwners(new String[]{
				"15555",
"11111",
"15111",
"11111",
"11111"
		}));
		assertEquals(10, trader.maxOwners(new String[]{
				"0100000000",
"0020000000",
"0003000000",
"0000400000",
"0000050000",
"0000006000",
"0000000700",
"0000000080",
"0000000009",
"1111111111"
		}));
	}
}