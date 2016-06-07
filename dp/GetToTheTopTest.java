import org.junit.*;
import static org.junit.Assert.*;

public class GetToTheTopTest {
	@Test
	public void test() {
		GetToTheTop top = new GetToTheTop();
		assertEquals(13, top.collectSweets(2,
				new int[]{1, 2, 3, 4, 3, 5},
				new int[]{1, 1, 1, 4, 5, 5},
				new int[]{1, 3, 4, 1, 2, 3},
				new int[]{2, 1, 1, 2, 1, 1}
				));
		assertEquals(47, top.collectSweets(4,
				new int[]{2, 8, 7, 4, 1, 4, 7, 5, 11, 4},
		new int[]{2, 9, 4, 6, 10, 5, 2, 8, 1, 10},
		new int[]{1, 1, 3, 3, 3, 5, 6, 6, 8, 9},
		new int[]{2, 2, 1, 2, 2, 2, 4, 3, 2, 2}
				));
		assertEquals(16, top.collectSweets(10,
				new int[]{1, 3, 5, 7},
		new int[]{1, 6, 2, 8},
		new int[]{2, 4, 1, 2},
		new int[]{4, 1, 7, 4}
				));
		assertEquals(129, top.collectSweets(3,
				new int[]{80, 20, 15, 13, 10, 7, 8, 9, 1, 4, 3, 15, 14, 19, 22, 12, 6, 15, 10, 30, 1, 1},
new int[]{2, 8, 11, 17, 20, 14, 10, 16, 8, 14, 19, 6, 6, 6, 6, 15, 15, 15, 14, 20, 20, 20},
new int[]{1, 2, 3, 2, 1, 4, 6, 7, 8, 8, 8, 9, 10, 11, 12, 9, 10, 11, 12, 9, 10, 11},
new int[]{2, 2, 2, 2, 2, 2, 3, 2, 3, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
		assertEquals(0, top.collectSweets(10, new int[]{0, 10, 11, 2, 0},
new int[]{1, 26, 29, 22, 3},
new int[]{1, 83, 88, 22, 5},
new int[]{11, 1, 23, 15, 8}));
		assertEquals(7, top.collectSweets(5, new int[]{2, 0, 5},
new int[]{1, 8, 9},
new int[]{6, 6, 1},
new int[]{3, 6, 3}));
		assertEquals(47, top.collectSweets(2, new int[]{2, 9, 9, 1, 9, 9, 8},
new int[]{10, 8, 6, 6, 8, 7, 3},
new int[]{6, 7, 5, 4, 5, 2, 5},
new int[]{1, 1, 1, 1, 1, 1, 1}));
	}
}