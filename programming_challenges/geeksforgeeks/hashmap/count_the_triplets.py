#User function Template for python3
#application of two_sum problem
class Solution:
	# code here
	def countTriplet(self, arr, n):
	    arr.sort()
	    #print(arr)
	    count = 0
	    for target in arr:
	        hashmap = {}
	        #print("target =", target)
	        for i in range(len(arr)):
	            if arr[i] == target:
                    continue
                complement = target - arr[i]
                #print(hashmap, arr[i])
                if complement in hashmap:
                    count += 1
                else:
                    hashmap[arr[i]] = i
        return count


	def countTriplet_bf(self, arr, n):
	    count = 0
	    for i in range(len(arr)):
	        for j in range(i, len(arr)):
	            for k in range(len(arr)):
	                if i != j != k:
	                    print(arr[i], arr[j], arr[k])
	                    if arr[i] + arr[j] == arr[k]:
	                        print("triplet found")
	                        count+= 1

	    return count
