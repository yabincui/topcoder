import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class WickedTeacherTest {
	@Test
	public void test() {
		WickedTeacher teacher = new WickedTeacher();
		assertEquals("1/3", teacher.cheatProbability(new String[]{"3", "2", "1"}, 2));
		assertEquals("1/1", teacher.cheatProbability(new String[]{
				"10","100","1000","10000","100000"
		}, 10));
		assertEquals("0/1", teacher.cheatProbability(new String[]{
				"11","101","1001","10001","100001"
		}, 10));
		assertEquals("5183/36288", teacher.cheatProbability(new String[]{
				"13","10129414190271203","102","102666818896","1216","1217","1218","101278001","1000021412678412681"
		}, 21));
	}
}