import org.junit.*;
import static org.junit.Assert.*;

public class DancingCouplesTest {
	@Test
	public void test() {
		DancingCouples couples = new DancingCouples();
		assertEquals(24, couples.countPairs(new String[]{
				"YYYY", 
				 "YYYY",
				 "YYYY"
		}, 3));
		assertEquals(4, couples.countPairs(new String[]{
				"YYNN", 
 "NYYN", 
 "NNYY"
		}, 3));
		assertEquals(0, couples.countPairs(new String[]{
				"YY", 
 "YY", 
 "YY"
		}, 3));
		assertEquals(112, couples.countPairs(new String[]{
				"YYNNNN",
 "NYYNNN",
 "NNYYNN",
 "NNNYYN",
 "NNNNYY",
 "YNNNNY"
		}, 3));
	}
}