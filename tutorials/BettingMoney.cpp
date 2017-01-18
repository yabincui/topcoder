#include <vector>
using namespace std;

class BettingMoney {
public:
  int moneyMade(vector<int> amounts, vector<int> centsPerDollar, int finalResult) {
  	int result = 0;
  	for (size_t i = 0; i < amounts.size(); ++i) {
  		if (i == finalResult) {
  			result -= amounts[i] * centsPerDollar[i];
  		} else {
  			result += amounts[i] * 100;
  		}
  	}
  	return result;
  }
};
