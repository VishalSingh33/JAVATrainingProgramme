// Problem Description
// A robot is located at the top-left corner(1,1) of a m x n grid(m rows , n columns).
// The robot can only move either down or right at any point in time.
// The robot is trying to reach the bottom-right corner(m,n) of the grid.
// How many possible unique paths are there?

// Input format: Single line containing two space separated integers representing m and n respectively.

public class UniquePaths {
    private static final int MOD = 1000000007;

    public int uniquePaths(int m, int n) {
        return binomialCoefficient(m + n - 2, m - 1);
    }

    // Helper function to calculate binomial coefficient C(n, k) % MOD
    private int binomialCoefficient(int n, int k) {
        if (k > n - k) {
            k = n - k; // C(n, k) == C(n, n-k)
        }
        long result = 1;
        for (int i = 0; i < k; i++) {
            result = result * (n - i) % MOD;
            result = result * modInverse(i + 1, MOD) % MOD;
        }
        return (int) result;
    }

    // Function to calculate modular inverse using Fermat's Little Theorem
    private int modInverse(int a, int mod) {
        return power(a, mod - 2, mod);
    }

    // Function to calculate (base^exp) % mod using iterative method
    private int power(int base, int exp, int mod) {
        long result = 1;
        long b = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * b % mod;
            }
            b = b * b % mod;
            exp >>= 1;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();
        // Example: Read m and n from input, here hardcoded for demonstration
        int m = 1000;
        int n = 1000;
        System.out.println("Number of unique paths: " + up.uniquePaths(m, n));
    }
}

// UniquePaths Method:
// The uniquePaths method calculates the number of unique paths from the
// top-left to the bottom-right corner of an m×n grid.
// It uses the binomial coefficient formula, where the total number of unique
// paths is C(m+n−2,m−1).

// Binomial Coefficient Calculation:

// The binomialCoefficient method calculates C(n,k) modulo MOD.
// To avoid overflow and keep the results manageable, we use modulo operations
// throughout the calculation.

// Optimize Calculation:
// We reduce the calculation complexity by leveraging symmetry: C(n,k)=C(n,n−k),
// thus if k>n−k, we replacek with n−k.
// We calculate the result by multiplying terms and taking modulo MOD after each
// multiplication to prevent overflow.
// For division in modular arithmetic, we use the modular inverse.

// Modular Inverse:
// The modInverse method computes the modular inverse of a number using Fermat's
// Little Theorem.
// Fermat's Little Theorem states that for a prime number p, a^(p−1) ≡ 1 (mod
// p).
// Thus a^(p-2) ≡ a^-1(mod p)
// This theorem allows us to compute 𝑎^-1(mod p) as a^(MOD-2)(mod MOD)

// Power Function:

// The power method calculates base^exp(mod mod) using an efficient iterative
// method
// (exponentiation by squaring).
// This method reduces the number of multiplications needed, making it efficient
// even for large exponents.

// Main Method:
// The main method demonstrates how to use the uniquePaths method. It can be
// tested with various input values to ensure correctness.
