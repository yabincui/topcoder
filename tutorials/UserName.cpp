// srm 203, div 2, easy
#include <vector>
#include <string>

using namespace std;

class UserName {
  public:
	string newMember(vector<string> existingNames, string newName) {
		int number = 0;
		while (true) {
			string name = newName;
			if (number != 0) {
				name += std::to_string(number);
			}
			number++;
			bool exist = false;
			for (size_t i = 0; i < existingNames.size(); ++i) {
				if (existingNames[i] == name) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				return name;
			}
		}
	}
};
