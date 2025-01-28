// 4. Implement RSA: Select e, d, n. Encrypt a word using e and decrypt using d.

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RSA {

    private BigInteger p, q, n, phi, e, d;

    public RSA(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        if (p.equals(q)) {
            throw new IllegalArgumentException("p and q must be distinct prime numbers.");
        }
        if (!p.isProbablePrime(10) || !q.isProbablePrime(10)) {
            throw new IllegalArgumentException("Both p and q must be prime numbers.");
        }

        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(2);
        while (phi.gcd(e).intValue() != 1) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    public List<BigInteger> encrypt(String plaintext) {
        List<BigInteger> ciphertext = new ArrayList<>();
        for (char letter : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(letter)) {
                int letterValue = letter - 'A';
                ciphertext.add(BigInteger.valueOf(letterValue).modPow(e, n));
            }
        }
        return ciphertext;
    }

    public String decrypt(List<BigInteger> ciphertext) {
        StringBuilder decryptedText = new StringBuilder();
        for (BigInteger value : ciphertext) {
            int decryptedValue = value.modPow(d, n).intValue();
            decryptedText.append((char) (decryptedValue + 'A'));
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter prime number p: ");
            BigInteger p = scanner.nextBigInteger();

            System.out.print("Enter prime number q: ");
            BigInteger q = scanner.nextBigInteger();

            RSA rsa = new RSA(p, q);

            System.out.println("Public Key (e, n): (" + rsa.e + ", " + rsa.n + ")");
            System.out.println("Private Key (d, n): (" + rsa.d + ", " + rsa.n + ")");

            System.out.print("Enter plaintext to encrypt (only letters): ");
            scanner.nextLine(); 
            String plaintext = scanner.nextLine();

            List<BigInteger> ciphertext = rsa.encrypt(plaintext);
            System.out.println("Ciphertext: " + ciphertext);

            String decryptedText = rsa.decrypt(ciphertext);
            System.out.println("Decrypted: " + decryptedText);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

//OUTPUT
// Enter prime number p: 7
// Enter prime number q: 11
// Public Key (e, n): (7, 77)
// Private Key (d, n): (43, 77)
// Enter plaintext to encrypt (only letters): HELLOWORLD
// Ciphertext: [28, 60, 11, 11, 42, 22, 42, 52, 11, 31]
// Decrypted: HELLOWORLD
