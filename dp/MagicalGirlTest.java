import org.junit.*;
import static org.junit.Assert.*;

public class MagicalGirlTest {
  MagicalGirl girl = new MagicalGirl();

  @Test
  public void test() {
    assertEquals(2.5, girl.maxExpectation(2, new int[]{1}, new int[]{75}, new int[]{1}), 1e-9);
    assertEquals(14.0, girl.maxExpectation(10, new int[]{5, 5, 5, 5},
        new int[]{100, 100, 100, 100}, new int[]{1, 1, 1, 1}), 1e-9);
    assertEquals(13.0, girl.maxExpectation(10, new int[]{5, 5, 5, 5, 5},
        new int[]{40, 80, 60, 20, 0}, new int[]{10, 10, 10, 10, 10}), 1e-9);
    assertEquals(2.0, girl.maxExpectation(2, new int[]{2}, new int[]{100}, new int[]{2}), 1e-9);
    assertEquals(35.908, girl.maxExpectation(20,
        new int[]{2,13,8,7,9,4,6,21}, new int[]
{18,83,75,23,45,23,10,98}, new int[]
{10,9,8,10,7,16,13,20}), 1e-9);
    assertEquals(101.0, girl.maxExpectation(11, new int[]
    {10,20,30,40,50,60,70,80,90}, new int[]
{100,100,100,100,100,100,100,100,100}, new int[]
{10,10,10,10,10,10,10,10,10}), 1e-9);
    assertEquals(100000.0, girl.maxExpectation(100000, new int[]
    {1000000}, new int[]
{100}, new int[]
{100000}), 1e-9); 
  }
}
