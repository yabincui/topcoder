class BettingStrategy:
    def finalSum(self, initSum, outcome):
        curSum = initSum
        bet = 1
        for c in outcome:
            if bet > curSum:
                break
            if c == 'W':
                curSum += bet
                bet = 1
            else:
                curSum -= bet
                bet *= 2
        return curSum