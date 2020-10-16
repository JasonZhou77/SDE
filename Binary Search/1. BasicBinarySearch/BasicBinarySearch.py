class Solution(object):
    def binary_search(self, array, target):
        if array == None or len(array) == 0:
            return -1
        lo, hi = 0, len(array) - 1
        while lo <= hi:
            mid = lo + (hi - lo) // 2
            n = array[mid]
            if target == n:
                return mid
            elif target < n:
                hi = mid - 1
            else:
                lo = mid + 1
        return -1