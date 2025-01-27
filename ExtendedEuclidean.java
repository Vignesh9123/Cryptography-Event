// 3.Implement Extended Euclidean Algorithm.
import java.util.Scanner;

public class ExtendedEuclidean {

    public static int gcdExtended(int r1, int r2, int[] t) {
        if (r1 == 0) {
            t[0] = 0;
            t[1] = 1;
            return r2;
        }
        int[] temp = new int[2];
        int gcd = gcdExtended(r2 % r1, r1, temp);
        t[0] = temp[1] - (r2 / r1) * temp[0];
        t[1] = temp[0];
        return gcd;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter r1: ");
        int r1 = sc.nextInt();
        
        System.out.print("Enter r2: ");
        int r2 = sc.nextInt();
        
        // Array to hold t1 and t2
        int[] t = new int[2];
        
        int g = gcdExtended(r1, r2, t);
        
        System.out.println("GCD: " + g + ", t1: " + t[0] + ", t2: " + t[1]);
        
        sc.close();
    }
}

// Output
// Enter r1: 7
// Enter r2: 26
// GCD: 1, t1: -11, t2: 3