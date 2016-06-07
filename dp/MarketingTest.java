import org.junit.*;
import static org.junit.Assert.*;

public class MarketingTest {
  Marketing market = new Marketing();

  @Test
  public void test() {
    assertEquals(2, market.howMany(new String[]{"1 4","2","3","0",""}));
    assertEquals(-1, market.howMany(new String[]{"1","2","0"}));
    assertEquals(2, market.howMany(new String[]{"1","2","3","0","0 5","1"}));
    assertEquals(1073741824, market.howMany(new String[]
    {"","","","","","","","","","",
 "","","","","","","","","","",
 "","","","","","","","","",""}));
    assertEquals(-1, market.howMany(new String[]
    {"1","2","3","0","5","6","4"}));
  }
}
