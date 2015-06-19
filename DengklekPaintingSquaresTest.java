import org.junit.*;
import static org.junit.Assert.*;

public class DengklekPaintingSquaresTest {
  DengklekPaintingSquares square = new DengklekPaintingSquares();
  
  @Test
  public void test() {
    assertEquals(2, square.numSolutions(1, 1));
    assertEquals(8, square.numSolutions(2, 2));
    assertEquals(5, square.numSolutions(1, 3));
    assertEquals(944149920, square.numSolutions(47, 7));
    assertEquals(636302453, square.numSolutions(100, 8));
  }
}
