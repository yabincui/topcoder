class Palindromist:
    def palindrome(self, text, words):
        textOne = text + text[::-1]
        textTwo = text + text[:-1][::-1]
        wordDict = {}
        for word in words:
            wordDict[word] = True
        splitOne = self.addSpace(textOne, wordDict)
        splitTwo = self.addSpace(textTwo, wordDict)
        if len(splitOne) == 0:
            return splitTwo
        elif len(splitTwo) == 0:
            return splitOne
        else:
            return splitOne if splitOne < splitTwo else splitTwo
    
    def addSpace(self, text, wordDict):
        n = len(text)
        # dp[i] is the first split pos of text[i:].
        dp = [-1 for x in range(n+1)]
        dp[n] = 0
        for i in range(n-1, -1, -1):
            for j in range(i+1, n+1):
                if dp[j] != -1 and wordDict.has_key(text[i:j]):
                    dp[i] = j
                    break
        if dp[0] == -1:
            return ''
            
        result = []
        spacePos = dp[0]
        for i in range(0, n):
            if spacePos == i:
                result.append(' ')
                spacePos = dp[i]
            result.append(text[i])
        return ''.join(result)
    
        