import org.junit.*;
import static org.junit.Assert.*;

public class TaroCoinsTest {
  TaroCoins coin = new TaroCoins();

  @Test
  public void test() {
    assertEquals(1, coin.getNumber(1));
    assertEquals(3, coin.getNumber(6));
    assertEquals(2, coin.getNumber(47));
    assertEquals(9, coin.getNumber(256));
    assertEquals(6853, coin.getNumber(8489289L));
    assertEquals(73411, coin.getNumber(1000000000L));
  }
}
