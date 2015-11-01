class Pathfinding:
    def getDirections(self, x, y):
        if x > 0 and y > 0:
            if x % 2 == 0 or y % 2 == 0:
                return x + y
            else:
                return x + y + 2
        elif x > 0 and y == 0:
            return x + y
        elif x > 0 and y < 0:
            if x % 2 == 1 or y % 2 == 0:
                return x - y
            else:
                return x - y + 2
        elif x == 0 and y > 0:
            return x + y
        elif x == 0 and y == 0:
            return x + y
        elif x == 0 and y < 0:
            if y % 2 == 1:
                return x - y + 2
            else:
                return x - y + 4
        elif x < 0 and y > 0:
            if y % 2 == 1 or x % 2 == 0:
                return y - x
            else:
                return y - x + 2
        elif x < 0 and y == 0:
            if x % 2 == 1:
                return y - x + 2
            else:
                return y - x + 4
        elif x < 0 and y < 0:
            if x % 2 == 1 or y % 2 == 1:
                return 2 - x - y
            else:
                return 4 - x - y
