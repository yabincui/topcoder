// SRM 185 Div 2 Easy
#include <vector>

using namespace std;

class PassingGrade {
public:
	int pointsNeeded(vector<int> pointsEarned, vector<int> pointsPossible, int finalExam) {
		int total = finalExam;
		for (auto& p : pointsPossible) {
			total += p;
		}
		double needInFloat = (total * 0.65);
		int need = (int)needInFloat;
		if (need < needInFloat - 1e-9) {
			need++;
		}
		for (auto& p : pointsEarned) {
			need -= p;
		}
		if (need < 0) {
			need = 0;
		} else if (need > finalExam) {
			need = -1;
		}
		return need;
	}
};
