import org.junit.*;
import static org.junit.Assert.*;

public class CarelessSecretaryTest {
	CarelessSecretary secretary = new CarelessSecretary();
	
	@Test
	public void test() {
		assertEquals(1, secretary.howMany(2,  1));
		assertEquals(4, secretary.howMany(3,  1));
		assertEquals(2, secretary.howMany(3,  3));
		assertEquals(2790, secretary.howMany(7,  4));
		assertEquals(322560, secretary.howMany(9,  1));
		assertEquals(466134693, secretary.howMany(714,  9));
	}
}