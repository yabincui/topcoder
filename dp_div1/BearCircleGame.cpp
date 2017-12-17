/*
BearCircleGame结合了DP和数论的知识，分析如下：
DP的部分：
开始有n只熊，每次处理减少1只熊，问最后剩下的熊为Limark的概率。
这是DP中概率递推的问题，一般用二维DP。开始有两种DP思路，一种是将
Limark固定在0点，dp[i][j]表示剩下i只熊时，从第j只熊开始查数的情况下Limark能赢的概率。
另一种思路是固定从0点开始查数，dp[i][j]表示剩下i只熊时，Limark在j位置的情况下的概率。
前一种思路是自底向上的，后一种是自顶向下的。

然后是对于每一轮中选择那只熊去掉的考虑：
n只熊围成一圈，假设从start开始，第一次让第((start + k - 1) % n)只熊进行抽签，一半概率去掉，一半
概率继续，然后是第((start + 2 * k - 1) % n), ((start + 3 * k - 1) % n), ... 直到又开始
从start查，这是一个循环。循环的长度是LCM(n, k) / k.
假设我们采取第一种自底向上的思路，假设dp[n-1][?]都求出来了，现在要求dp[n][start].
设环的长度为t, 截止在第((start + k - 1) % n)只熊的概率为(1/2)/(1/2 + 1/4 + 1/8 + ... + 1/2^t),
即(1/2) / (1 - 1/2^t). 可得递推公式：
dp[n][start] = 1/(1 - 1/2^t) * (1/2 * dp[n - 1][((start + k - 1) % n)] +
                                1/4 * dp[n - 1][((start + 2 * k - 1) % n)] +
                                ... +
                                1/2^t * dp[n - 1][((start + t * k - 1) % n)])

这种三重循环的DP方式是超时的, 需要优化，在同一个环上的dp[n][?]是可以通过O(1)的时间递推出来的。
列出dp[n][(start + t * k) % n]的公式.
dp[n][(start + t * k) % n] - 1/2 * dp[n][start] = 1/2 * dp[n - 1][(start + t * k) % n]

这是可以理解的，从(start + t * k)开始查，要么第一次把在(start + t * k + k - 1)的熊给去掉了，要么就
和从start开始查的情况相同。
如此就可以把DP减为两重循环。
相比之下，虽然用第二种思路也许也能推出两重循环的方式，但却隔了一层，因为要先计算去掉了哪只熊，然后算下一轮时
Limark所处的位置，就繁琐了。

数论的部分:
首先用到了欧拉定理, 即如果a和n互质，则 a^(m-1) % m = 1. 证明就不再赘述。
题目要求将结果的概率由X/Y 写为(X * Y^(MOD-2)) % MOD.
这里证明代码中用到的几种操作:

1/2 => 2^(MOD - 2), 所以代码中乘以I2的地方，其实是乘以 (1/2).

x1/y1 => (x1 * y1^(MOD - 2)) % MOD
x2/y2 => (x2 * y2^(MOD - 2)) % MOD
x1/y1 * x2/y2 = (x1 * x2) / (y1 * y2) => (x1 * x2) * (y1 * y2)^(MOD - 2)
可以推出转化后的概率仍然是可以直接相乘的。

x1/y1 + x2/y2 = (x1 * y2 + x2 * y1) / (y1 * y2) =>
  (x1 * y2 + x2 * y1) * (y1 * y2)^(MOD - 2) = x1 * y1^(MOD - 2) + x2 * y2^(MOD - 2)
可以推出转化后的概率仍然是可以直接相加的。

代码中有一处是乘以pow(1 - a + MOD, MOD - 2), 其对应的概率是1/(1-a).
但代码中用的a是转化后的，所以需要一下证明。
1/(1 - x/y) = y / (y - x)
  => y * (y - x)^(MOD - 2)
   = y * (y - x * y^(MOD - 1))^(MOD - 2)  // 因为最后都 % MOD，所以y^(MOD - 1) % MOD = 1.
   = y^(MOD - 1) * (1 - x * y^(MOD - 2))^(MOD - 2)
   = (1 - x * y^(MOD - 2))^(MOD - 2)

*/
#include <math.h>
#include <stdio.h>
#include <string.h>

#include <algorithm>

using namespace std;

bool isPrime(int x) {
    int half = min((int)sqrt(x) + 1, x - 1);
    for (int i = 2; i <= half; ++i) {
        if (x % i == 0) {
            return false;
        }
    }
    return true;
}

int MOD = 1000000007;

int Pow(int a, int b) {
    int res = 1;
    while (b != 0) {
        if (b & 1) {
            res = 1ll * res * a % MOD;
        }
        a = 1ll * a * a % MOD;
        b >>= 1;
    }
    return res;
}

int getMod(int a, int mod) {
    return (a % mod + mod) % mod;
}

const int N = 2001;

static int dp[N][N];
static int die_prob[N];
static int visited[N];

class BearCircleGame {
public:
    int winProbability(int n, int k) {
        int64_t I2 = Pow(2, MOD - 2); // the representation of 1/2.
        dp[1][0] = 1;
        memset(visited, 0, sizeof(visited));
        for (int length = 2; length <= n; ++length) {
            for (int start = 0; start < length; ++start) {
                // What is the prob of win when we die when counting K starting from [start].
                if ((start + k - 1) % length == 0) {
                    // Limark died.
                    die_prob[start] = 0;
                } else {
                    // pos % length, new start pos in current length.
                    // pos % length % (length - 1), new start pos in next round, length - 1.
                    die_prob[start] = I2 * dp[length - 1][(start + k - 1) % length % (length - 1)] % MOD;
                }
            }
            for (int start = 0; start < length; ++start) {
                if (visited[start] != length) {
                    // start is a node of unvisited cycle in current round.
                    // a is coefficient.
                    // b is the accumulated prob.
                    int64_t a = I2;
                    int64_t b = die_prob[start];
                    for (int i = getMod(start + k, length); i != start; i = getMod(i + k, length)) {
                        b = (a * die_prob[i] + b) % MOD;
                        a = I2 * a % MOD;
                    }
                    dp[length][start] = b * Pow(1 - a + MOD, MOD - 2) % MOD;
                    for (int i = getMod(start - k, length); i != start; i = getMod(i - k, length)) {
                        dp[length][i] = (I2 * dp[length][getMod(i + k, length)] + die_prob[i]) % MOD;
                    }
                    visited[start] = length;
                    for (int i = getMod(start + k, length); i != start; i = getMod(i + k, length)) {
                        visited[i] = length;
                    }
                }
            }
        }
        return dp[n][0];
    }
};

int main() {
    BearCircleGame game;
    int res = game.winProbability(2, 1);
    printf("res = %d\n", res);
    res = game.winProbability(2, 2);
    printf("res = %d\n", res);
    res = game.winProbability(3, 2);
    printf("res = %d\n", res);
    res = game.winProbability(3, 1);
    printf("res = %d\n", res);
    res = game.winProbability(4, 4);
    printf("res = %d\n", res);
    res = game.winProbability(5, 1000000000);
    printf("res = %d\n", res);
    res = game.winProbability(2000, 123);
    printf("res = %d\n", res);
    res = game.winProbability(1987, 987654321);
    printf("res = %d\n", res);

    return 0;
}