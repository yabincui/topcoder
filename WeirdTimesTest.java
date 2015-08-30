import org.junit.*;
import static org.junit.Assert.*;

public class WeirdTimesTest {
	@Test
	public void test() {
		WeirdTimes times = new WeirdTimes();
		assertArrayEquals(new int[]{0, 1, 3},
				times.hourValues(new int[]{22,  11, 33}, 3));
		assertArrayEquals(new int[]{1},
				times.hourValues(new int[]{10}, 2));
		assertArrayEquals(new int[]{0, 19},
				times.hourValues(new int[]{1,  2}, 20));
		assertArrayEquals(new int[]{-1},
				times.hourValues(new int[]
						{25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 15, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
						1));
		assertArrayEquals(new int[]{0, 1, 2, 2, 3, 3, 4, 5, 12, 13, 18, 18 },
				times.hourValues(new int[]
						{45, 12, 0, 3, 2, 7, 4, 9, 23, 6, 17, 33},
						12345));
		assertArrayEquals(new int[]{-1}, times.hourValues(new int[]{43, 58}, 318));
	}
}