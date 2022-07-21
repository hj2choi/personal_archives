from collections import deque

class Solution:

    def parenthesis_match(self, open, close):
        return open == "{" and close == "}" or open == "[" and close == "]" or open == "(" and close == ")"

    #Function to check if brackets are balanced or not.
    def ispar(self,str):
        # code here
        stack = deque()
        for s in str:
            if stack and self.parenthesis_match(stack[-1], s):
                stack.pop()
            else:
                stack.append(s)
        return len(stack) == 0
