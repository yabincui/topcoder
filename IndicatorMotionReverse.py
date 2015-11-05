class IndicatorMotionReverse:
    def getMinProgram(self, actions):
        text = ''.join(actions)
        dp = []
        dp.append('')
        for i in range(1, len(text)):
            min_prev_end = max(0, i - 99)
            action = self.getAction(text[i-1], text[i])
            count = 1
            curValue = dp[i-1] + action + ('%02d' % count)
            for prev_end in range(i-2, min_prev_end - 1, -1):
                if self.getAction(text[prev_end], text[prev_end+1]) != action:
                    break
                count += 1
                value = dp[prev_end] + action + ('%02d' % count)
                if len(curValue) > len(value) or (len(curValue) == len(value) and curValue > value):
                    curValue = value
            dp.append(curValue)
        result = dp[len(text) - 1]
        if len(result) > 100:
            result = result[:97] + '...'
        return result
    
    def getAction(self, a, b):
        va = self.getValue(a)
        vb = self.getValue(b)
        diff = (vb - va + 4) % 4
        if diff == 0:
            return 'S'
        elif diff == 1:
            return 'R'
        elif diff == 2:
            return 'F'
        elif diff == 3:
            return 'L'
    
    def getValue(self, a):
        if a == '|':
            return 0
        elif a == '/':
            return 1
        elif a == '-':
            return 2
        elif a == 'N':
            return 3