import org.junit.*;
import static org.junit.Assert.*;

public class PowerPlantsTest {
	@Test
	public void test() {
		PowerPlants plants = new PowerPlants();
		assertEquals(5, plants.minCost(new String[]{
				"024",
				 "203",
				 "430"	
		}, "YNN", 3));
		assertEquals(21, plants.minCost(new String[]{
				"0AB",
 "A0C",
 "CD0"
		}, "YNN", 3));
		assertEquals(14, plants.minCost(new String[]{
				"1ABCD",
 "35HF8",
 "FDM31",
 "AME93",
 "01390"
		}, "NYNNN", 5));
		assertEquals(2, plants.minCost(new String[]{
				"012",
 "123",
 "234"
		}, "NNY", 2));
		assertEquals(0, plants.minCost(new String[]{
				"1309328",
				 "DS2389U",
				 "92EJFAN",
				 "928FJNS",
				 "FJS0DJF",
				 "9FWJW0E",
				 "23JFNFS"	
		}, "YYNYYNY", 4));
		assertEquals(1, plants.minCost(new String[]{
				"01", "20"
		}, "YN", 2));
		
	}
}