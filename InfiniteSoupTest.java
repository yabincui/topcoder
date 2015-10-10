import org.junit.*;
import static org.junit.Assert.*;

public class InfiniteSoupTest {
	@Test
	public void test() {
		InfiniteSoup soup = new InfiniteSoup();
		assertArrayEquals(new int[]{1}, soup.countRays(new String[]{"abc", "def", "ghi"},
				new String[]{"abc"}, 2));
		assertArrayEquals(new int[]{2}, soup.countRays(new String[]{"abc", "def", "ghi"},
				new String[]{"abc"}, 3)); 
		assertArrayEquals(new int[]{3}, soup.countRays(new String[]{"abc", "def", "ghi"},
				new String[]{"abc"}, 4)); 
		assertArrayEquals(new int[]{0, 2, 0, 0, 2, 7, 5, 6, 0, 5 }, soup.countRays(
				new String[]{"ccbbc","baabc","ccbab","cbcaa","aacab"},
				new String[]{"aaccbaaccbaacc","aabbcaabbcaabbc","babccbabccbabc","aaacaaaacaaaaca",
					 "abbcaabbcaab","ccbbcccbbcccbbc","bbacabbacab","caacccaacccaac",
					 "baaccbaaccbaac","caccbcaccbca"}, 10));
		assertArrayEquals(new int[]{0}, soup.countRays(new String[]{"abb", "bbb", "bbb"},
				new String[]{"aaa"}, 2));
	}
}