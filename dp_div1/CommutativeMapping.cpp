/*
CommutativeMapping结合了DP和Graph的知识，分析如下：
Graph的部分:
f[g[i]] = g[f[i]], 所以对于每一个i值，g[i]值决定了g[f[i]]的值。
故n个点有n条有向边，画成Graph，可以组成一到多个环，每个环上可以引入
一些支线。

DP的部分:
每个点有一个array，branchMuls[N]，branchMuls[i]代表当该点取值为i时，它的
parent支线的所有取值情况数。有意思的是，branchMuls[]的值是对图中的点后序遍历
dp得到的。
对于每个环，以一个点为起点，枚举它的所有可能取值。对于起点的每个取值，可以确定整个
环上的取值，将环上每个点的取值对应的branchMuls值相乘，可以得到起点该取值得到的
所有情况数。将取值情况数相加，得到该环对应的情况数。将所有环的情况数相乘，得到结果。
*/

#include <stdio.h>
#include <string.h>

#include <algorithm>
#include <unordered_set>
#include <vector>

using namespace std;

const int N = 2001;
const int MOD = 1000000007;

enum State {
    UNINITIALIZED,
    IN_CIRCLE,
    NOT_IN_CIRCLE,
    USED,
};

struct Node {
    int state;
    bool branchMulsReady;
    // If Node value is i, mul branchMuls[i].
    int branchMuls[N];
    int child;
    vector<int> parents;
};

Node nodes[N];

class CommutativeMapping {
public:
    int count(vector<int> f) {
        n = f.size();
        this->f = f;
        setupGraph(f);
        calculateBranchMuls();
        gothroughCircles();
        return totalMappings;
    }

private:
    void setupGraph(const vector<int>& f) {
        for (int i = 0; i < n; ++i) {
            nodes[i].branchMulsReady = false;
            nodes[i].parents.clear();
        }
        for (int i = 0; i < n; ++i) {
            nodes[i].state = UNINITIALIZED;
            nodes[i].child = f[i];
            nodes[f[i]].parents.push_back(i);
        }
        for (int i = 0; i < n; ++i) {
            if (nodes[i].state != UNINITIALIZED) {
                continue;
            }
            unordered_set<int> visited;
            for (int j = i; nodes[j].state != IN_CIRCLE; j = nodes[j].child) {
                if (visited.find(j) != visited.end()) {
                    int k = j;
                    do {
                        nodes[k].state = IN_CIRCLE;
                        k = nodes[k].child;
                    } while (k != j);
                    break;
                }
                visited.insert(j);
                nodes[j].state = NOT_IN_CIRCLE;
            }
        }
    }

    void calculateBranchMuls() {
        for (int i = 0; i < n; ++i) {
            if (nodes[i].branchMulsReady) {
                continue;
            }
            calcBranchMulsDFS(i);
        }
    }

    void calcBranchMulsDFS(int cur) {
        for (int i = 0; i < n; ++i) {
            nodes[cur].branchMuls[i] = 1;
        }
        for (auto parent : nodes[cur].parents) {
            if (nodes[parent].state == IN_CIRCLE) {
                continue;
            }
            calcBranchMulsDFS(parent);
        }
        if (nodes[cur].state != IN_CIRCLE) {
            int child = nodes[cur].child;
            vector<int> tmpMuls(n, 0);
            for (int i = 0; i < n; ++i) {
                if (nodes[cur].branchMuls[i]) {
                    int childValue = f[i];
                    tmpMuls[childValue] = add(tmpMuls[childValue], nodes[cur].branchMuls[i]);
                }
            }
            for (int i = 0; i < n; ++i) {
                nodes[child].branchMuls[i] = mul(nodes[child].branchMuls[i], tmpMuls[i]);
            }
        }
        nodes[cur].branchMulsReady = true;
    }

    void gothroughCircles() {
        totalMappings = 1;
        for (int i = 0; i < n; ++i) {
            if (nodes[i].state == IN_CIRCLE) {
                useCircle(i);
            }
        }
    }

    void useCircle(int start) {
        int circleTotal = 0;
        for (int i = 0; i < n; ++i) {
            if (nodes[start].branchMuls[i]) {
                int circleMul = 1;
                int j = start;
                int curValue = i;
                do {
                    circleMul = mul(circleMul, nodes[j].branchMuls[curValue]);
                    int nextValue = f[curValue];
                    j = nodes[j].child;
                    curValue = nextValue;
                } while (j != start);
                if (curValue != i || circleMul == 0) {
                    continue;
                }
                circleTotal = add(circleTotal, circleMul);
            }
        }
        totalMappings = mul(totalMappings, circleTotal);
        for (int i = start; nodes[i].state == IN_CIRCLE; i = nodes[i].child) {
            nodes[i].state = USED;
        }
    }

    int add(int a, int b) {
        return (a + b) % MOD;
    }

    int mul(int a, int b) {
        return ((int64_t)a * b) % MOD;
    }

    int n;
    vector<int> f;
    int totalMappings;
};

int main() {
    CommutativeMapping mapping;
    vector<int> f = {0, 0, 0, 0};
    int res = mapping.count(f);
    printf("res = %d\n", res);
    f = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    res = mapping.count(f);
    printf("res = %d\n", res);
    f = {1, 2, 3, 0};
    res = mapping.count(f);
    printf("res = %d\n", res);
    f = {8,5,5,15,17,1,5,8,16,7,17,4,0,7,15,9,12,2,0,16,11,16,11,16,10,16,2,14,0,10,15,1,9,16,14,7,4,10,4,0,1,9,15,1,8,17,1,16,9,15,17,7,14,7,4,3,14,7};
    res = mapping.count(f);
    printf("res = %d\n", res);
    f = {0};
    res = mapping.count(f);
    printf("res = %d\n", res);
    return 0;
}