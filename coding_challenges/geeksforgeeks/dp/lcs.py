"""
Input:
A = 6, B = 6
str1 = ABCDGH
str2 = AEDFHR
Output: 3
Explanation: LCS for input Sequences
“ABCDGH” and “AEDFHR” is “ADH” of
length 3.


Input:
A = 3, B = 2
str1 = ABC
str2 = AC
Output: 2
Explanation: LCS of "ABC" and "AC" is
"AC" of length 2.
"""
class Solution:

    def lcs_it(self,x,y,s1,s2):
        return self.lcs_rc_tb(x, y, s1, s2)

    #Function to find the length of longest common subsequence in two strings.
    def lcs_it(self,x,y,s1,s2):
        # memoization version
        lcs_table = [[0 for _ in range(y+1)] for _ in range(x+1)]
        for i in range(x):
            for j in range(y):
                if s1[i] == s2[j]:
                    lcs_table[i + 1][j + 1] = lcs_table[i][j] + 1
                else:
                    lcs_table[i + 1][j + 1] = max(lcs_table[i][j+1],lcs_table[i+1][j])
        return lcs_table[-1][-1]


    #Function to find the length of longest common subsequence in two strings.
    lcs_table_g = [[0 for _ in range(y+1)] for _ in range(x+1)]
    def lcs_rc_tb(self,x,y,s1,s2):
        # recursive, memoization version
        if x == 0 or y == 0:
            return 0

        if s1[x - 1] == s2[y - 1]:
            lcs_table_g[x][y] = self.lcs_r(x -1, y - 1, s1, s2) + 1
            return lcs_table_g[x][y]
        else:
            lcs_table_g[x][y] = max(self.lcs_r(x, y - 1, s1, s2), self.lcs_r(x - 1, y, s1, s2))
            return lcs_table_g[x][y]


    #Function to find the length of longest common subsequence in two strings.
    def lcs_rc(self,x,y,s1,s2):
        # recursive version
        if x == 0 or y == 0:
            return 0

        if s1[x - 1] == s2[y - 1]:
            return self.lcs_rc(x -1, y - 1, s1, s2) + 1
        else:
            return max(self.lcs_rc(x, y - 1, s1, s2), self.lcs_rc(x - 1, y, s1, s2))
