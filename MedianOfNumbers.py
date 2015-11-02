class MedianOfNumbers:
    def findMedian(self, numbers):
        numbers = list(numbers)
        numbers.sort()
        candidate = numbers[len(numbers)/2]
        
        leftCount = 0
        rightCount = 0
        for number in numbers:
            if number < candidate:
                leftCount += 1
            elif number > candidate:
                rightCount += 1
        return candidate if leftCount == rightCount else -1