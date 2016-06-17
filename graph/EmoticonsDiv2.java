import java.util.*;

public class EmoticonsDiv2 {
	public int printSmiles(int smiles) {
		HashSet<Integer> hit_set = new HashSet<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		// 1 in chat, 0 in clipboard.
		int start = (1 << 16) | 0;
		queue.add(start);
		hit_set.add(start);
		int step;
		// bfs
		int count = 0;
		for (step = 1; ; step++) {
			int size = queue.size();
			while (size-- > 0) {
				int cur = queue.poll();
				int chat = cur >> 16;
				int clipboard = cur & 0xffff;
				count++;
				//System.out.printf("count = %d, chat = %d, clipboard = %d\n", count, chat, clipboard);
				// copy it
				if (chat != clipboard) {
					int next = (chat << 16) | chat;
					if (!hit_set.contains(next)) {
						hit_set.add(next);
						queue.add(next);
					}
				}
				// paste it
				if (clipboard != 0 && chat + clipboard <= smiles) {
					if (chat + clipboard == smiles) {
						return step;
					}
					int next = ((chat + clipboard) << 16) | clipboard;
					if (!hit_set.contains(next)) {
						hit_set.add(next);
						queue.add(next);
					}
				}
			}
		}
	}

  public static void main(String[] args) {
    EmoticonsDiv2 div = new EmoticonsDiv2();
    int result = div.printSmiles(11);
    System.out.printf("result = %d\n", result);
  }
}
