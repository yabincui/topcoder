// SRM 162 Div 1 Easy
#include <vector>

using namespace std;

class PaperFold {
public:
	int numFolds(vector<int> paper, vector<int> box) {
		int a0 = getNeed(paper[0], box[0]);
		int a1 = getNeed(paper[1], box[1]);
		int b0 = getNeed(paper[0], box[1]);
		int b1 = getNeed(paper[1], box[0]);
		int ret = min(a0 + a1, b0 + b1);
		if (ret > 8) {
			ret = -1;
		}
		return ret;
	}
	
	int getNeed(int from, int to) {
		int fold = 0;
		while (from > to) {
			to *= 2;
			fold++;
		}
		return fold;
	}
};
