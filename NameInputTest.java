import org.junit.*;
import static org.junit.Assert.*;

public class NameInputTest {
	@Test
	public void test() {
		NameInput input = new NameInput();
		assertEquals(1, input.countUpDownKeyPresses(new String[]
				{"Jjhon"}, new String[]
				{"Jn"}));
		assertEquals(5, input.countUpDownKeyPresses(new String[]
				{"Jjhon"}, new String[]
				{"John"}));
		assertEquals(186, input.countUpDownKeyPresses(new String[]
				{"abcdefghijklmnopqrstuvwxyz","ABCDEFGHIJKLMNOPQRSTUVWXYZ","0123456789"},
				new String[]
		{"Joh","nAndFr","iends"}
				));
		assertEquals(16, input.countUpDownKeyPresses(new String[]
				{"aaaabbbab","baabbabaabba"}, new String[]
		{"bbaaababba","baababababbb"}));
		assertEquals(-1, input.countUpDownKeyPresses(new String[]
				{"john"}, new String[]
		{"John"}));
		assertEquals(0, input.countUpDownKeyPresses(new String[]
				{"4"}, new String[]
		{"4444444444444"}));
		assertEquals(38, input.countUpDownKeyPresses(new String[]
				{"abcABC123","cbaCBA321"}, new String[]
		{"aB32C2AaB3c","c32bA"}));
	}
}