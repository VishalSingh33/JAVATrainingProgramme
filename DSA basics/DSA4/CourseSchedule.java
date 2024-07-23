import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// There are a total of numCourses courses you have to take, labeled from 1 to numCourses.

// Some courses may have prerequisites, for example to take course 1 you have to first take course 2, 
// which is expressed as a pair: [1,2]

// Given the totalNumber of courses & a list of prerequisite pairs, is it possible for you to finish all courses?

// Input format
// First line contains two integers representing the value of numCourse and the number of prerequisite pairs.

// Next number of prerequisite pair lines contains two space separated integers representing a and b 
// where to take course a you have to take course b first.

public class CourseSchedule {

    public static boolean courseSchedule(int numCourses, int[][] prerequisites) {
        // Initialize the adjacency list representation of the graph
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Count the indegree of each course
        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int courseA = prerequisite[0];
            int courseB = prerequisite[1];
            adjacencyList.get(courseB).add(courseA); // Add directed edge from B to A
            indegree[courseA]++; // Increment indegree of course A
        }

        // Perform a BFS starting from courses with zero indegree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int coursesTaken = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            coursesTaken++;

            for (int nextCourse : adjacencyList.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // If all courses can be taken, return true; otherwise, return false
        return coursesTaken == numCourses;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };

        boolean canFinish = courseSchedule(numCourses, prerequisites);
        System.out.println(canFinish); // Output: true
    }
}
