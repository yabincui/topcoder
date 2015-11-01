class OnTime:
    class Node:
        def __init__(self, a, b, departure, time, cost):
            self.a = a
            self.b = b
            self.departure = departure
            self.time = time
            self.cost = cost

    def minCost(self, N, T, buses):
        nodes = []
        for bus in buses:
            (a, b, departure, time, cost) = [int(x) for x in bus.split()]
            nodes.append(self.Node(a, b, departure, time, cost))
        # dp[i][j] is the min cost to arrive station j before or at time i.
        dp = [[-1 for x in range(N)] for x in range(T+1)]
        dp[0][0] = 0
        for t in range(T+1):
            for i in range(N):
                # Arrive by waiting in the station.
                if dp[t-1][i] != -1:
                    dp[t][i] = dp[t-1][i]
                # Arrive by taking bus j.
                for node in nodes:
                    if node.b != i or (node.departure + node.time) != t:
                        continue
                    if dp[max(node.departure-1, 0)][node.a] == -1:
                        continue
                    cost = dp[max(node.departure-1, 0)][node.a] + node.cost
                    if dp[t][i] == -1 or dp[t][i] > cost:
                        dp[t][i] = cost

        return dp[T][N-1]