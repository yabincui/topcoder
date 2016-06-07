import org.junit.*;
import static org.junit.Assert.*;

public class PalindromeFactoryTest {
	@Test
	public void test() {
		PalindromeFactory factory = new PalindromeFactory();
		
		assertEquals(0, factory.getEditDistance("abba"));
		assertEquals(1, factory.getEditDistance("dabba"));
		assertEquals(2, factory.getEditDistance("babacvabba"));
		assertEquals(1, factory.getEditDistance("abc"));
		assertEquals(1, factory.getEditDistance("acxcba"));
		assertEquals(1, factory.getEditDistance("abcacbd"));
	}
}