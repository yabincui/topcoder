class BiggestRectangleEasy:
    def findArea(self, N):
        max_area = 0
        for i in range(0, N/2 + 1):
            j = (N - 2 * i) / 2
            area = i * j
            max_area = max(area, max_area)
        return max_area