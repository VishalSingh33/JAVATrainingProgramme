import java.util.*;

// You are given a list of projects and a list of dependencies, which is a list of pairs of projects, where 
// the second project is dependent on the first project. All of a project's dependencies must be built before the project is.

// Find a build order that will allow the projects to be built according to their dependencies. 
// If there is no valid build order, print -1.

// Input format
// First line contains two space separated integers N and D, where N is the number of projects and D 
// is the number of dependencies, respectively.

// Second line contains N space separated strings which are the names of the N projects.

// Next D lines contain two space separated strings P and Q, which says project Q depends on project P 
// (P and Q will be among the project names specified on the second line).

public class BuildOrder {

    public static ArrayList<String> buildOrder(
            ArrayList<String> project, ArrayList<ArrayList<String>> dependencies) {

        HashMap<String, List<String>> graph = new HashMap<>();
        HashMap<String, Integer> inDegrees = new HashMap<>();

        // Initialize graph and in-degrees
        for (String proj : project) {
            graph.put(proj, new ArrayList<>());
            inDegrees.put(proj, 0);
        }

        // Build the graph and calculate in-degrees
        for (ArrayList<String> dependency : dependencies) {
            String u = dependency.get(0);
            String v = dependency.get(1);
            graph.get(u).add(v); // Add directed edge u -> v
            inDegrees.put(v, inDegrees.getOrDefault(v, 0) + 1);
            // Increment in-degree of v
        }

        Queue<String> queue = new LinkedList<>();
        ArrayList<String> buildOrder = new ArrayList<>();

        // Add projects with in-degree 0 to the queue
        for (String proj : project) {
            if (inDegrees.get(proj) == 0) {
                queue.offer(proj);
            }
        }

        // Perform topological sorting using BFS
        while (!queue.isEmpty()) {
            String currProj = queue.poll();
            buildOrder.add(currProj); // Add current project to build order

            // Process adjacent projects and update in-degrees
            for (String depProj : graph.get(currProj)) {
                inDegrees.put(depProj, inDegrees.get(depProj) - 1);
                if (inDegrees.get(depProj) == 0) {
                    queue.offer(depProj); // Enqueue project with in-degree 0
                }
            }
        }

        // Check if all projects are included in the build order
        if (buildOrder.size() == project.size()) {
            return buildOrder;
        } else {
            ArrayList<String> result = new ArrayList<>();
            result.add("-1"); // Add "-1" as a string to the result ArrayList
            return result;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> projects = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        ArrayList<ArrayList<String>> dependencies = new ArrayList<>();
        dependencies.add(new ArrayList<>(Arrays.asList("A", "B")));
        dependencies.add(new ArrayList<>(Arrays.asList("B", "C")));
        dependencies.add(new ArrayList<>(Arrays.asList("C", "D")));
        dependencies.add(new ArrayList<>(Arrays.asList("D", "A"))); // Create a cycle

        ArrayList<String> buildOrder = buildOrder(projects, dependencies);

        System.out.println("Build Order:");
        if (buildOrder.size() == 1 && buildOrder.get(0).equals("-1")) {
            System.out.println("-1"); // No valid build order exists
        } else {
            System.out.println(buildOrder);
        }
    }
}


// Sample Input 1
// 5 3

// A xy a c b

// a xy

// b A

// xy c

// Sample Output 1
// a b xy A c

// Explanation 1
// In this example, there are 3 dependencies. The projects can be built in any order satisfying these 3 dependencies 
// i.e. a should be built before xy, b should be built before A, and xy should be built before c.

// The order "a b xy A c" is one such order.
