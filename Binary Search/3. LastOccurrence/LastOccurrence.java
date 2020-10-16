class Solution {
    public int findLastOccurrence(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int lo = 0, hi = array.length - 1;
        while (lo < hi - 1) {
            int mid = lo + (hi - lo) / 2;
            int n = array[mid];
            if (target == n) {
                lo = mid;
            } else if (target < n) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (array[hi] == target) {
            return hi;
        } else if (array[lo] == target) {
            return lo;
        } else {
            return -1;
        }
    }
}