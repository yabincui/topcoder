class TreasuresPacking:
    class Node:
        def __init__(self, weight, value):
            self.weight = weight
            self.value = value
        
        def __lt__(self, other):
            return (self.value * other.weight) > (self.weight * other.value)

    def maximizeCost(self, treasures, W):
        cannotBeDivided = []
        canBeDivided = []
        for treasure in treasures:
            strs = treasure.split()
            (weight, value, divide) = (int(strs[0]), int(strs[1]), strs[2])
            if divide == 'Y':
                canBeDivided.append(self.Node(weight, value))
            else:
                cannotBeDivided.append(self.Node(weight, value))

        n = len(cannotBeDivided)
        # dp[i] is the max value using i weights filling non dividable treasures.
        dp = [0 for x in range(W+1)]
        for node in cannotBeDivided:
            for w in range(W, node.weight-1, -1):
                if dp[w] < dp[w-node.weight] + node.value:
                    dp[w] = dp[w-node.weight] + node.value

        canBeDivided.sort()
        # dpDivided[i] is the max value using i weights filling dividable treasures.
        dpDivided = [0 for x in range(W+1)]
        curPos = 0
        curWeight = 0
        for i in range(1, W+1):
            if curPos >= len(canBeDivided):
                dpDivided[i] = dpDivided[i-1]
            else:
                curWeight += 1
                if curWeight == canBeDivided[curPos].weight:
                    dpDivided[i] = dpDivided[i-curWeight] + canBeDivided[curPos].value
                    curPos += 1
                    curWeight = 0
                else:
                    dpDivided[i] = dpDivided[i-1] + float(canBeDivided[curPos].value) / canBeDivided[curPos].weight
        
        result = 0.0
        for i in range(W+1):
            cur = dp[i] + dpDivided[W-i]
            result = max(result, cur)
        return result