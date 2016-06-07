import org.junit.*;
import static org.junit.Assert.*;

public class HexagonalBattlefieldEasyTest {
	@Test
	public void test() {
		HexagonalBattlefieldEasy easy = new HexagonalBattlefieldEasy();
		assertEquals(10, easy.countArrangements(new int[]
				{-2,0,1,1,0}, new int[]
				{-2,0,1,0,2}, 3));
		assertEquals(2, easy.countArrangements(new int[]
				{0}, new int[]{0}, 2));
		assertEquals(104, easy.countArrangements(new int[]
				{0}, new int[]{0}, 3));
		assertEquals(6, easy.countArrangements(new int[]
				{-1, 0, 0, 1, 2}, new int[]{0, 0, 1, 0, 0}, 3));
		assertEquals(1, easy.countArrangements(new int[]
				{0,1,0,0,1,-1,-1}, new int[]
				{0,0,-1,1,1,0,-1}, 2));
	}
}