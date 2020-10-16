class Solution(object):
    def find_first_occurrence(self, array, target):
        if array == None or len(array) == 0:
            return -1
        lo, hi = 0, len(array) - 1
        while lo < hi - 1:
            mid = lo + (hi - lo) // 2
            n = array[mid]
            if target == n:
                hi = mid
            elif target < n:
                hi = mid - 1
            else:
                lo = mid + 1
        if array[lo] == target:
            return lo
        elif array[hi] == target:
            return hi
        else:
            return -1