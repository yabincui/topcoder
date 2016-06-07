import org.junit.*;
import static org.junit.Assert.*;

public class SquaresCoveringTest {
	SquaresCovering squares = new SquaresCovering();
	
	@Test
	public void test() {
		assertEquals(2, squares.minCost(new int[]{1,  100},
				new int[]{1, 100},
				new int[]{3, 1},
				new int[]{100, 1}));
		assertEquals(1, squares.minCost(new int[]{1,  100},
				new int[]{1, 100},
				new int[]{1, 1},
				new int[]{100, 1}));
		assertEquals(22, squares.minCost(new int[]
				{0,100,201,300}, new int[]
		{0,0,1,0}, new int[]
		{6,100,10}, new int[]
		{1,100,99}
				));
		assertEquals(738, squares.minCost(new int[]
				{41,6334,19169,11478,26962,5705,23281,41}, new int[]
		{18467,26500,15724,29358,24464,28145,16827,18467}, new int[]
		{292,11943,5437,14605,154,12383,18717,19896,21727,11539,19913,26300,9895,23812,30334,4665,7712,6869,27645,32758}, new int[]
		{9962,2996,4828,32392,33,293,17422,19719,5448,14772,1870,25668,17036,28704,31323,17674,15142,28254,25548,32663}
				));
		assertEquals(84, squares.minCost(new int[]
				{41,6334,9169,1478,6962,5705,3281}, new int[]
		{8467,6500,5724,9358,4464,8145,6827}, new int[]
		{92,43,37,15,54,83,17,96,27,39,13,100,95,12,34,65,12,69,45,58}, new int[]
		{962,996,828,392,903,293,422,719,448,772,870,668,36,704,323,674,142,254,548,663}
				));
	}
}