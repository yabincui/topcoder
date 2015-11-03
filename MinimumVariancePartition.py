class MinimumVariancePartition:
    def minDev(self, mixedSamples, k):
        MaxInt = (1 << 31) - 1
        n = len(mixedSamples)
        mixedSamples = list(mixedSamples)
        mixedSamples.sort()
        sum = [0 for x in range(n+1)]
        for i in range(1, n+1):
            sum[i] = sum[i-1] + mixedSamples[i-1]
        variant = [[0 for x in range(n)] for x in range(n)]
        for i in range(0, n):
            for j in range(i, n):
                curLen = j - i + 1
                mean = (sum[j+1] - sum[i]) / float(curLen)
                value = 0.0
                for t in range(i, j+1):
                    diff = abs(mixedSamples[t] - mean)
                    value += diff * diff
                value /= float(curLen)
                variant[i][j] = value
        
        dp = [[MaxInt for x in range(n+1)] for x in range(k+1)]
        dp[0][0] = 0
        for i in range(1, k+1):
            for j in range(1, n+1):
                for curLen in range(1, j+1):
                    dp[i][j] = min(dp[i][j], dp[i-1][j-curLen] + variant[j-curLen][j-1])
        return dp[k][n]
