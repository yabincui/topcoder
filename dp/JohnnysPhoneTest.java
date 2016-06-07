import org.junit.*;
import static org.junit.Assert.*;

public class JohnnysPhoneTest {
	@Test
	public void test() {
		JohnnysPhone phone = new JohnnysPhone();
		assertEquals(8, phone.minimizePushes(new String[]{
				"age messagd messagd message"
		}, "message"));
		assertEquals(23, phone.minimizePushes(new String[]{
				"b a c d"	
		}, "abcd  dcba "));
		assertEquals(-1, phone.minimizePushes(new String[]{
				"a b c"
		}, "d"));
		assertEquals(-1, phone.minimizePushes(new String[]{
				"gajdkwifpcks iclfabc"
		}, "gajf"));
		assertEquals(5, phone.minimizePushes(new String[]{
				"a ", "aa ", "aaa ", "aaaa ", "ab"
		}, "ab"));
		assertEquals(5, phone.minimizePushes(new String[]{
				"aaa ", "bbb ", "ccc"
		}, "ccc"));
		assertEquals(35, phone.minimizePushes(new String[]{
				"abc bca ", "bac", " cba"
		}, "abac acab a b c cab"));
	}
}