import org.junit.*;
import static org.junit.Assert.*;

public class SentenceDecompositionTest {
  SentenceDecomposition sentence = new SentenceDecomposition();

  @Test
  public void test() {
    assertEquals(8, sentence.decompose("neotowheret",
        new String[]{"one", "two", "three", "there"}));
    assertEquals(2, sentence.decompose("abba",
        new String[]{"ab", "ac", "ad"}));
    assertEquals(-1, sentence.decompose("thisismeaningless",
        new String[]{"this", "is", "meaningful"}));
    assertEquals(10, sentence.decompose("ommwreehisymkiml",
        new String[]{"we", "were", "here", "my", "is", "mom", "here", "si", "milk", "where", "si"}));
    assertEquals(8, sentence.decompose("ogodtsneeencs",
        new String[]{"go", "good", "do", "sentences", "tense", "scen"}));
    assertEquals(-1, sentence.decompose("sepawaterords",
        new String[]{"separate","words"}));
  }
}
