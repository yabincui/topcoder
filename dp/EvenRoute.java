public class EvenRoute {
  public String isItPossible(int[] x, int[] y, int wantedParity) {
    boolean allEven = true;
    for (int i = 0; i < x.length - 1; ++i) {
      if ((int)Math.abs(x[i] - x[i + 1] + y[i] - y[i + 1]) % 2 != 0) {
        allEven = false;
        break;
      }
    }
    if (allEven) {
      boolean odd = (int)Math.abs(x[0] + y[0]) % 2 != 0;
      if (odd && wantedParity == 0 || !odd && wantedParity == 1) {
        return "CANNOT";
      }
    }
    return "CAN";
  }
}
