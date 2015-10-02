import org.junit.*;
import static org.junit.Assert.*;

public class InfiniteSequenceTest {
	@Test
	public void test() {
		InfiniteSequence sequence = new InfiniteSequence();
		assertEquals(1, sequence.calc(0, 2, 3));
		assertEquals(7, sequence.calc(7, 2, 3));
		assertEquals(32768, sequence.calc(10000000, 3, 3));
		assertEquals(89, sequence.calc(256, 2, 4));
		assertEquals(2, sequence.calc(1, 1000000, 1000000));
	}
}