import org.junit.*;
import static org.junit.Assert.*;

public class RandomPancakeStackDiv2Test {
  RandomPancakeStackDiv2 stack = new RandomPancakeStackDiv2();

  @Test
  public void test1() {
    assertEquals(1.6666666666666667, stack.expectedDeliciousness(new int[]{1, 1, 1}), 1e-6);
    assertEquals(20.0, stack.expectedDeliciousness(new int[]{10, 20}), 1e-6);
    assertEquals(9.891666666666667, stack.expectedDeliciousness(new int[]{3, 6, 10, 9, 2}), 1e-6);
    assertEquals(10.999999724426809, stack.expectedDeliciousness(new int[]{10,9,8,7,6,5,4,3,2,1}),
                                      1e-6);
    assertEquals(7.901100088183421, stack.expectedDeliciousness(new int[]{
      1,2,3,4,5,6,7,8,9,10}), 1e-6);
    assertEquals(1.7182818011463845, stack.expectedDeliciousness(new int[]{
      1,1,1,1,1,1,1,1,1,1}), 1e-6);
  }
}
