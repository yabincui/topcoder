import math

class ReturnToHome:
    def goHome(self, X, Y, D, T):
        dist = math.sqrt(X * X + Y * Y)
        if D * 1.0 / T <= 1.0:
            return dist
        walk_cost = dist
        jump_count = int(dist / D)
        cost2 = T * jump_count + (dist - jump_count * D)
        if jump_count == 0:
            cost3 = min(T + D - dist, 2 * T)
        else:
            cost3 = T * (jump_count + 1)
        return min(walk_cost, cost2, cost3)