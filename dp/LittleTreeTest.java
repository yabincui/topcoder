import org.junit.*;
import static org.junit.Assert.*;

public class LittleTreeTest {
	@Test
	public void test() {
		LittleTree tree = new LittleTree();
		assertEquals(1, tree.minCost(5, new String[]{"0,1 1,2 2,3 2,4"}, 2));
		assertEquals(3, tree.minCost(5, new String[]{"0,1 1,2 2,3 2,4"}, 1));
		assertEquals(0, tree.minCost(3, new String[]{"0,1 1,2"}, 2));
		assertEquals(2, tree.minCost(9, new String[]{"0,3 3,1 1,8 ","8,","6 2,7 4,2 0,4 7",",5"}, 3));
	}
}