// http://community.topcoder.com/stat?c=problem_statement&pm=13159
public class FibonacciDiv2 {
  public int find(int N) {
    int a, b, c;
    a = 0; b = 1;
    while (!(a <= N && b >= N)) {
      c = b;
      b = a + b;
      a = c;
    }
    return Math.min(N - a, b - N);
  }
}
