import org.junit.*;
import static org.junit.Assert.*;

public class TheDictionaryTest {
	@Test
	public void test() {
		TheDictionary dictionary = new TheDictionary();
		assertEquals("azaz", dictionary.find(2, 2, 2));
		assertEquals("zzaa", dictionary.find(2, 2, 6));
		assertEquals("", dictionary.find(10, 10, 1000000000));
		assertEquals("aaazazaazaz", dictionary.find(7, 4, 47));
	}
}