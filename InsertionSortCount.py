class InsertionSortCount:
    def countMoves(self, A):
        moves = 0
        A = list(A)
        for i in range(1, len(A)):
            cur = A[i]
            j = i - 1
            while j >= 0:
                if A[j] > cur:
                    A[j+1] = A[j]
                    moves += 1
                else:
                    break
                j -= 1
            A[j+1] = cur
        return moves