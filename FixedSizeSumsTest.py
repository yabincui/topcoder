import unittest

class FixedSizeSums:
    def kthElement(self, sum, count, index):
        # dp[sum+1][count+1][sum+1].
        # dp[i][j][k] is the ways to make sum i using j values <= k.
        dp = []
        for i in range(sum+1):
            dp.append([[0 for x in range(sum+1)] for x in range(count+1)])
        dp[0][0][0] = 1
        for i in range(1, sum+1):
            for j in range(1, count+1):
                for k in range(1, i+1):
                    dp[i][j][k] += dp[i][j][k-1]
                    # Try using number k.
                    dp[i][j][k] += dp[i-k][j-1][min(i-k, k)]
        if dp[sum][count][sum] <= index:
            return ''
        result = []
        curSum = sum
        curCount = count
        curValue = sum
        curIndex = index
        while curSum != 0:
            # Try each number.
            for value in range(curValue, 0, -1):
                ways = dp[curSum-value][curCount-1][min(curSum-value, value)]
                if curIndex < ways:
                    result.append(value)
                    curSum -= value
                    curCount -= 1
                    curValue = min(curSum, value)
                    break
                else:
                    curIndex -= ways
        return str(sum) + '=' + '+'.join([str(x) for x in result])


class FixedSizeSumsTest(unittest.TestCase):
    def test1(self):
        sum = FixedSizeSums()
        self.assertEquals('150=121+10+10+1+1+1+1+1+1+1+1+1',
                          sum.kthElement(150, 12, 1234))
        self.assertEquals('',
                          sum.kthElement(150, 150, 987654321))

if __name__ == '__main__':
    unittest.main()