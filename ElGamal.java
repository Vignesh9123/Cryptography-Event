//5. Implement El-Gamal: Select e1, e2, r, d. Compute c1, c2. Decrypt using d. 
import java.math.BigInteger;
import java.util.Scanner;

public class ElGamal {

    // Function to compute modular exponentiation
    public static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger mod) {
        return base.modPow(exp, mod);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter prime number 'p': ");
        BigInteger p = new BigInteger(scanner.nextLine());

        System.out.print("Enter primitive root 'e1': ");
        BigInteger e1 = new BigInteger(scanner.nextLine());

        System.out.print("Enter private key 'd': ");
        BigInteger d = new BigInteger(scanner.nextLine());

        BigInteger e2 = modExp(e1, d, p);

        System.out.println("Public key (e1, e2, p): (" + e1 + ", " + e2 + ", " + p + ")");

        System.out.print("Enter random number 'r': ");
        BigInteger r = new BigInteger(scanner.nextLine());

        System.out.print("Enter message to encrypt (as a number): ");
        BigInteger message = new BigInteger(scanner.nextLine());

        // Encryption
        BigInteger c1 = modExp(e1, r, p);
        BigInteger c2 = message.multiply(modExp(e2, r, p)).mod(p);

        // Decryption
        BigInteger decrypted = c2.multiply(modExp(c1, p.subtract(BigInteger.ONE).subtract(d), p)).mod(p);

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted (c1, c2): (" + c1 + ", " + c2 + ")");
        System.out.println("Decrypted: " + decrypted);
    }
}

// Output
// Enter prime number 'p': 11
// Enter primitive root 'e1': 2
// Enter private key 'd': 3
// Public key (e1, e2, p): (2, 8, 11)
// Enter random number 'r': 4
// Enter message to encrypt (as a number): 7
// Original Message: 7
// Encrypted (c1, c2): (5, 6)
// Decrypted: 7