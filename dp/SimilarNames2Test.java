import org.junit.*;
import static org.junit.Assert.*;

public class SimilarNames2Test {
  SimilarNames2 name = new SimilarNames2();

  @Test
  public void test() {
    assertEquals(3, name.count(new String[]{"kenta", "kentaro", "ken"}, 2));
    assertEquals(6, name.count(new String[]{"hideo", "hideto", "hideki", "hide"}, 2));
    assertEquals(24, name.count(new String[]
    {"aya", "saku", "emi", "ayane", "sakura", "emika", "sakurako"}, 3));
    assertEquals(0, name.count(new String[]{"taro", "jiro", "hanako"}, 2));
    assertEquals(6, name.count(new String[]{"alice", "bob", "charlie"}, 1));
    assertEquals(276818566, name.count(new String[]
    {"ryota", "ryohei", "ryotaro", "ryo", "ryoga", "ryoma", "ryoko", "ryosuke", "ciel", "lun",
 "ryuta", "ryuji", "ryuma", "ryujiro", "ryusuke", "ryutaro", "ryu", "ryuhei", "ryuichi", "evima"},
      3));
  }
}
