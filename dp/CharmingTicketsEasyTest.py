import unittest

class CharmingTicketsEasy:
    def count(self, k, good):
        MOD = 999983
        # Case1 + Case2 - Case3:
        # Case1 is the count of situations that the sum of first k digits
        # equals the sum of last k digits.
        # Case2 is the count of situations that the sum of odd digits
        # equals the sum of event digits.
        # Case3 is the count of situations that fulfills both of the above
        # situations.
        dp = {}
        dp[0] = 1
        
        less_half_dp = {}
        big_half_dp = {}
        if k % 2 == 0:
            less_half_i = k / 2 - 1
            big_half_i = k / 2 - 1
        else:
            less_half_i = (k - 1) / 2 - 1
            big_half_i = (k + 1) / 2 - 1
        
        if less_half_i == -1:
            less_half_dp = dp
         
        for i in range(0, k):
            nextDp = {}
            for a in dp.keys():
                for c in good:
                    value = int(c)
                    na = a + value
                    if na in nextDp:
                        nextDp[na] = (nextDp[na] + dp[a]) % MOD
                    else:
                        nextDp[na] = dp[a] % MOD
            dp = nextDp
            if less_half_i == i:
                less_half_dp = dp
            if big_half_i == i:
                big_half_dp = dp
        
        count_of_case1 = 0
        for a in dp.keys():
            add = (dp[a] * dp[a]) % MOD
            count_of_case1 = (count_of_case1 + add) % MOD
        count_of_case2 = count_of_case1
        
        count_less_half = 0
        for a in less_half_dp.keys():
            add = (less_half_dp[a] * less_half_dp[a]) % MOD
            count_less_half = (count_less_half + add) % MOD
        count_big_half = 0
        for a in big_half_dp.keys():
            add = (big_half_dp[a] * big_half_dp[a]) % MOD
            count_big_half = (count_big_half + add) % MOD
        count_of_case3 = (count_less_half * count_big_half) % MOD
        
        result = (count_of_case1 + count_of_case2 + MOD - count_of_case3) % MOD
        return result  

    def countSlow2(self, k, good):
        MOD = 999983
        BASE = 500
        MAX = 1000
        dp = [[0 for x in range(1000)] for x in range(1000)]
        dp[BASE][BASE] = 1
        for i in range(0, 2 * k):
            nextDp = [[0 for x in range(1000)] for x in range(1000)]
            for a in range(MAX):
                for b in range(MAX):
                    count = dp[a][b]
                    if count == 0:
                        continue
                    for c in good:
                        value = int(c)
                        (na, nb) = (a, b)
                        if i < k:
                            na += value
                        else:
                            na -= value
                        if i % 2 == 0:
                            nb += value
                        else:
                            nb -= value
                        nextDp[na][nb] = (nextDp[na][nb] + count) % MOD
            dp = nextDp
            
        result = 0
        for a in range(MAX):
            for b in range(MAX):
                if a == BASE or b == BASE:
                    result = (result + dp[a][b]) % MOD
        return result
    
    def countSlow(self, k, good):
        MOD = 999983
        dp = {}
        dp[(0,0)] = 1
        for i in range(0, 2 * k):
            nextDp = {}
            for (a, b) in dp.keys():
                count = dp[(a, b)]
                for c in good:
                    value = int(c)
                    (na, nb) = (a, b)
                    if i < k:
                        na += value
                    else:
                        na -= value
                    if i % 2 == 0:
                        nb += value
                    else:
                        nb -= value
                    if na > 450 or na < -450 or nb > 450 or nb < -450:
                        continue
                    ncount = nextDp[(na, nb)] if (na, nb) in nextDp else 0
                    nextDp[(na, nb)] = (ncount + count) % MOD
            dp = nextDp
        result = 0
        for (a, b) in dp.keys():
            if a == 0 or b == 0:
                result = (result + dp[(a, b)]) % MOD
        return result
    
class CharmingTicketsEasyTest(unittest.TestCase):
    def test(self):
        easy = CharmingTicketsEasy()
        self.assertEqual(10, easy.count(1, '0123456789'))
        self.assertEqual(8, easy.count(2, '21'))
        self.assertEqual(1240, easy.count(2, '0123456789'))
        self.assertEqual(207444, easy.count(10, '731'))
        self.assertEqual(367584, easy.count(50, '0123456789'))

if __name__ == '__main__':
    unittest.main()