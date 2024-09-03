public class ContainerWithMostWater {
    public static void main(String[] args) {

        // You are given an array of N non-negative integers where each represents the
        // height of a line. N vertical lines are drawn at points marked 1 to n on the x
        // axis as shown in the diagram. Find two lines, which together with the x axis
        // forms a container, such that the container holds the most water. Assume the
        // width of lines to be negligible.
        // Note: You may not slant the container and n is at least 2.

        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++; // Move the left pointer towards the center
            } else {
                right--; // Move the right pointer towards the center
            }
        }
        // return maxArea;
        System.out.println(maxArea);
    }

}
