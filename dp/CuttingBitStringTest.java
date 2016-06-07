import org.junit.*;
import static org.junit.Assert.*;

public class CuttingBitStringTest {
  CuttingBitString cut = new CuttingBitString();

  @Test
  public void test1() {
    assertEquals(3, cut.getmin("101101101"));
    assertEquals(1, cut.getmin("1111101"));
    assertEquals(-1, cut.getmin("00000"));
    assertEquals(3, cut.getmin("110011011"));
    assertEquals(-1, cut.getmin("1000101011"));
    assertEquals(5, cut.getmin("111011100110101100101110111"));
  }
}
