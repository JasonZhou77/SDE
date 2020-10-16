class Solution(object):
    def find_last_occurrence(self, array, target):
        if array == None or len(array) == 0:
            return -1
        lo, hi = 0, len(array) - 1
        while lo < hi - 1:
            mid = lo + (hi - lo) // 2
            n = array[mid]
            if target == n:
                lo = mid
            elif target < n:
                hi = mid - 1
            else:
                lo = mid + 1
        if array[hi] == target:
            return hi
        elif array[lo] == target:
            return lo
        else:
            return -1