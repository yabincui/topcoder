import org.junit.*;
import static org.junit.Assert.*;

public class TheSumOfLuckyNumbersTest {
	@Test
	public void test() {
		TheSumOfLuckyNumbers lucky = new TheSumOfLuckyNumbers();
		assertArrayEquals(new int[]{4, 7}, lucky.sum(11));
		assertArrayEquals(new int[]{4, 4, 4}, lucky.sum(12));
		assertArrayEquals(new int[]{}, lucky.sum(13));
		assertArrayEquals(new int[]{4, 4, 4, 44, 44}, lucky.sum(100));
	}
}