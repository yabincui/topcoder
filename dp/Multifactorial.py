class Multifactorial:
    def calcMultiFact(self, n, k):
        result = 1
        cur = n
        while cur > k and result <= int(1e18):
            result *= cur
            cur -= k
        if cur <= k and result <= int(1e18):
            result *= cur
        return str(result) if result <= int(1e18) else "overflow"