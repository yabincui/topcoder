class SimplifiedDarts:
    def tryToWin(self, W, N, P1, P2):
        p2 = P1 / 100.0
        p3 = P2 / 100.0
        dp = [0.0 for x in range(W+1)]
        dp[W] = 1.0
        for lastThrowCount in range(N, 0, -1):
            prevDp = [0.0 for x in range(W+1)]
            prevDp[W] = dp[W]
            for j in range(0, W):
                selectTwo = p2 * dp[min(j+2, W)] + (1-p2) * dp[j]
                selectThree = p3 * dp[min(j+3, W)] + (1-p3) * dp[j]
                prevDp[j] = max(selectTwo, selectThree)
            dp = prevDp
        return dp[0] * 100