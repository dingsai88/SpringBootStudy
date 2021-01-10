package com.ding.study.nowcoder.a19Array_MaxsumofSubarray;

/**
 * https://www.nowcoder.com/practice/554aa508dd5d4fefbf0f86e5fe953abd?tpId=190&&tqId=35386&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 */
public class SolutionMaxsumofSubarray {
    /**
     *
     * @param arr
     * @return
     */
    public int maxsumofSubarray (int[] arr) {
        int sum=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>0){
                sum+=arr[i];
            }
        }
        return sum;
    }


    public int maxsumofSubarray2 (int[] arr) {
        // write code here
        int sum=0;
        for(int i=0;i<arr.length;i++){
            if(sum+arr[i]>arr[i]){
                sum+=arr[i];
            } else{
                sum=arr[i];
            }
        }

        return sum;
    }
}
