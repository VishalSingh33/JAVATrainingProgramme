import java.util.*;

// Problem Description
// Given an array of integers, we have to find the maximum possible XOR value 
// using any two integers in the array.

// Input format
// First line will have an integer N denoting the size of the array.
// Next line will have N space separated integers.

public class MaximumXor {

    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    private static void insert(TrieNode root, int num) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[bit] == null) {
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    private static int findMaximumXOR(TrieNode root, int num) {
        TrieNode node = root;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (node.children[1 - bit] != null) {
                maxXor |= (1 << i);
                node = node.children[1 - bit];
            } else {
                node = node.children[bit];
            }
        }
        return maxXor;
    }

    public static int maximumXor(int n, ArrayList<Integer> a) {
        TrieNode root = new TrieNode();
        for (int num : a) {
            insert(root, num);
        }

        int maxResult = 0;
        for (int num : a) {
            maxResult = Math.max(maxResult, findMaximumXOR(root, num));
        }

        return maxResult;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }
        scanner.close();

        System.out.println(maximumXor(n, a));
    }
}

// Explanation:
// TrieNode Class: This class represents a node in the Trie, which has two
// children (0 and 1).
// insert Method: This method inserts a number into the Trie. It breaks the
// number into bits and inserts each bit into the Trie.
// findMaximumXOR Method: This method finds the maximum XOR value for a given
// number by traversing the Trie and attempting to get the opposite bit at each
// position to maximize the XOR value.
// maximumXor Method: This method inserts all numbers into the Trie and then
// finds the maximum XOR value for each number.
// main Method: This method reads the input, calls the maximumXor method, and
// prints the result.
// This approach ensures that we efficiently find the maximum XOR value in O(N *
// 32) time, where N is the number of elements in the array.
