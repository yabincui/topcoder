import org.junit.*;
import static org.junit.Assert.*;

public class GraduationTest {
	Graduation graduation = new Graduation();
	
	@Test
	public void test() {
		assertEquals("BCD", graduation.moreClasses("A", new String[]
				{"2ABC","2CDE"}
				));
		assertEquals("", graduation.moreClasses("+/NAMT", new String[]
				{"3NAMT","2+/","1M"}
				));
		assertEquals("0", graduation.moreClasses("A", new String[]
				{"100%*Klju"}
				));
		assertEquals(",ABCDE", graduation.moreClasses("", new String[]
				{"5ABCDE","1BCDE,"}));
		assertEquals("AEP", graduation.moreClasses("CDH", new String[]
				{"2AP", "3CDEF", "1CDEFH"}));
	}
}