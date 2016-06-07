import org.junit.*;
import static org.junit.Assert.*;

public class SetOfPatternsTest {
	@Test
	public void test() {
		SetOfPatterns pattern = new SetOfPatterns();
		assertEquals(26, pattern.howMany(new String[]{"?"}, 1));
		assertEquals(3, pattern.howMany(new String[]{"a", "b", "c"}, 1));
		assertEquals(1, pattern.howMany(new String[]{"a?", "?b"}, 2));
		assertEquals(881343, pattern.howMany(new String[]{"?????"}, 1));
	}
}