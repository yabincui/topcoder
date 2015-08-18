import org.junit.*;
import static org.junit.Assert.*;

public class SubAnagramsTest {
	SubAnagrams anagrams = new SubAnagrams();
	
	@Test
	public void test() {
		assertEquals(3, anagrams.maximumParts(new String[]
				{"ABABAB"}));
		assertEquals(4, anagrams.maximumParts(new String[]
				{"AAXAAAABX"}));
		assertEquals(1, anagrams.maximumParts(new String[]
				{"ABCDEFGHIJKL"}));
		assertEquals(2, anagrams.maximumParts(new String[]
				{"ABBAB","B","BBX","Z"}));
	}
}