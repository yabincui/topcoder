class CorrectingParenthesization:
    def getMinErrors(self, s):
        n = len(s)
        if n == 0:
            return 0
        dp = [[-1 for x in range(n)] for x in range(n)]
        for i in range(0, n-1):
            dp[i][i+1] = self.fix(s[i], s[i+1])
        for groupLen in range(4, n+1, 2):
            for i in range(0, n - groupLen + 1):
                j = i + groupLen - 1
                curValue = dp[i+1][j-1] + self.fix(s[i], s[j])
                for k in range(i+1, j-1, 2):
                    value = dp[i][k] + dp[k+1][j]
                    curValue = min(value, curValue)
                dp[i][j] = curValue
        return dp[0][len(s) - 1]
    
    def fix(self, a, b):
        if a == '(' and b == ')':
            return 0
        elif a == '[' and b == ']':
            return 0
        elif a == '{' and b == '}':
            return 0
        if a in '([{' or b in ')]}':
            return 1
        else:
            return 2