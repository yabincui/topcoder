import org.junit.*;
import static org.junit.Assert.*;

public class CheapestRouteTest {
	@Test
	public void test() {
		CheapestRoute route = new CheapestRoute();
		assertArrayEquals(new int[]{0, 0}, route.routePrice(new int[]{100}, new int[]{0}, new int[]{0}, 1000));
		assertArrayEquals(new int[]{1000, 2}, route.routePrice(new int[]{0, -1, 0, 0}, new int[]{0}, new int[]{2}, 1000));
		assertArrayEquals(new int[]{5, 2}, route.routePrice(new int[]{1, 2, 3}, new int[]{}, new int[]{}, 100));
		assertArrayEquals(new int[]{}, route.routePrice(new int[]{1, 0, -1}, new int[]{0}, new int[]{2}, 0));
		assertArrayEquals(new int[]{14, 6}, route.routePrice(new int[]{4,2,1,0,5,6,0,3,0},
				new int[]{4,4,3,7,5,4,2,5,8,4}, new int[]{7,3,5,0,5,4,5,0,8,3}, 8));
		assertArrayEquals(new int[]{111, 3}, route.routePrice(
				//        0   1    2  3   4   5   6   7   8   9   10  11  12  13
				new int[]{85, 76, 26, 41, 38, -1, 38, 14, 95, 51, 84, -1, 67, 4},
				new int[]{8, 11, 0, 0, 13, 10, 9, 2, 1, 7, 10, 11, 6, 8, 0, 3, 6, 3, 6, 12, 13}, 
				new int[]{4, 12, 11, 6, 0, 2, 2, 13, 11, 0, 0, 6, 10, 13, 3, 3, 5, 9, 9, 5, 4}, 
				42));
	}
}