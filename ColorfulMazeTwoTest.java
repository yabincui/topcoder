import org.junit.*;
import static org.junit.Assert.*;

public class ColorfulMazeTwoTest {
	@Test
	public void test() {
		ColorfulMazeTwo maze = new ColorfulMazeTwo();
		assertEquals(0.5, maze.getProbability(new String[]
				{ ".$.",
						  "A#B",
						  "A#B",
						  ".!." }, new int[]
						{ 50, 50, 0, 0, 0, 0, 0 }
				), 1e-9);
		assertEquals(0.6,
				maze.getProbability(new String[]
						{ ".$.",
								  "A#B",
								  "A#B",
								  ".!." },
						new int[]
								{ 50, 40, 0, 0, 0, 0, 0 }
						), 1e-9
				);
		assertEquals(0.0,
				maze.getProbability(new String[]
						{ "$A#",
								  ".#.",
								  "#B!" }, new int[]
								{ 10, 10, 10, 10, 10, 10, 10 }
						), 1e-9);
		assertEquals(0.5,
				maze.getProbability(new String[]
						{ "$A..",
								  "##.A",
								  "..B!" }, new int[]
								{ 50, 50, 0, 0, 0, 0, 0 }
						), 1e-9
				);
		assertEquals(0.0,
				maze.getProbability(new String[]
						{ "$C..",
								  "##.A",
								  "..B!" }, new int[]
								{ 50, 50, 100, 0, 0, 0, 0 }
						), 1e-9
				);
		assertEquals(0.036000000000000004,
				maze.getProbability(new String[]
						{ ".$.D.E.F.G.!." }, new int[]
				{ 10, 20, 30, 40, 50, 60, 70 }
						), 1e-9);
	}
}