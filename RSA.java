// 4. Implement RSA: Select e, d, n. Encrypt a word using e and decrypt using d.

import java.util.*;
import java.math.*;
import java.nio.charset.*;

public class RSA{
	public static void main(String[] args){
		BigInteger p,q,N,phi,e,d;
		
		p = BigInteger.probablePrime(1024,new Random());
		q = BigInteger.probablePrime(1024,new Random());
		N = p.multiply(q);
		phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		e = BigInteger.probablePrime(512,new Random());
		
		while( phi.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(phi)<0){
			e = e.add(BigInteger.ONE);
		}
		
		d = e.modInverse(phi);
		
		System.out.println("Prime number p: "+ p);
		System.out.println("Prime number q: "+ q);
		System.out.println("Public key e is: "+ e);
		System.out.println("Private key d is: "+ d);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the plain text: ");
		String testString = sc.nextLine();
		System.out.println("Encrypting String: "+ testString);
		
		byte[] encrypted = new BigInteger(testString.getBytes()).modPow(e,N).toByteArray();
		byte[] decrypted = new BigInteger(encrypted).modPow(d,N).toByteArray();
		

		System.out.print("Encrypted Bytes: ");
		for(int i=0; i<encrypted.length; i++){ 
			System.out.print(encrypted[i]); 
		}
		System.out.println();
		
		System.out.print("Decrypted Bytes: ");
		for(int i=0; i<decrypted.length; i++){ 
			System.out.print(decrypted[i]); 
		}
		System.out.println();
				
		System.out.println("Decrypted String: " + new String(decrypted, StandardCharsets.UTF_8));
	}
}

// Output

// Prime number p: 153358769602590672509160787099339313308077807544289994323106406933690378628183778615215051291054844224874243131231393382664869003857945207876095541182822260327554630663425566971655582688256348041876200789100148714284116976036565460512577184160237488778463120715805422933847406333352769155784957431729135279373
// Prime number q: 165988857965473903067945645239492032395347202056183236310610159945610001930211910486019377119111134311583834522769777390183256621776070397829311097470370405066600357190459310321629510689672347022527115510497850400204385398365881032152240870304228614563112990081863251070290683226716663113464076817975908467323
// Public key e is: 11086272280958369767129937224924255336464086703813481418218587288836153825476511235436193350450988539219759384739066000240597726544334066281704253120872779
// Private key d is: 14300611381388019628529578700582196588974721425649558216044340675002370359159835251044013259348946849239566516683373051681324636648605210107449843471720008357903413333266559099698599114597878396987764290270831731689870328623002003888917217952399366645709415831272Enter the plain text: HeyThisIsRSA
// Encrypting String: HeyThisIsRSA
// Encrypted Bytes: 16-65-8952-4311298119-5676-64-12457115-66513-110-68-58-7094-1045281-88-9124-4996-50-70-6-126-7702951-97-105-75-9553-17-116122-30-71-28-2-11412549-39-12630-5525-81-33658098-72-18-91-6861-10410738-103-4654-48-126-127-10910971091017-3-5521-9032-92-73-36-127-115-59108-56151811091020-16111-17-3517262055127-4185-116-33124-7090-127-102-1099899-117-2511122-119-15-16301911246-114-12178551-461278472-305230-87-56-10141-126-32-66125-41-72-50-6148-20-8-62111-12337122-13-53-14-89803066-63158115114-35-6047-1263851-58-516561-72118-124-54-107-72-5094-3-87-92-62-5-8682-4111514459-52-109-81103-87-20-1-103-34-34-1252027-104423-7174-63-122-36-118-38-1151041-26219-83-45-69-29-44-34314810620111-27-5764-47-67
// Decrypted Bytes: 721011218410410511573115828365
// Decrypted String: HeyThisIsRSA


// V2: Take e, d and n from user. Encrypt message using e and decrypt using d

import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        BigInteger e, d, n;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the public key (e): ");
        e = new BigInteger(sc.next());
        System.out.print("Enter the private key (d): ");
        d = new BigInteger(sc.next());
        System.out.print("Enter the modulus (n): ");
        n = new BigInteger(sc.next());

        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        String word = "Hi";
        System.out.println("Original Word: " + word);

        BigInteger message = new BigInteger(word.getBytes());
        if (message.compareTo(n) >= 0) {
            System.out.println("Error: Message is too large for the modulus n. Choose a larger n.");
            return;
        }
        System.out.println("Original Message as Number: " + message);

        BigInteger ciphertext = message.modPow(e, n);
        System.out.println("Encrypted Message: " + ciphertext);

        BigInteger decryptedMessage = ciphertext.modPow(d, n);

        String decryptedWord = new String(decryptedMessage.toByteArray());
        System.out.println("Decrypted Word: " + decryptedWord);
    }
}

// Output:
// Enter the public key (e): 65537
// Enter the private key (d): 803633
// Enter the modulus (n): 1040399
// Public Key (e, n): (65537, 1040399)
// Private Key (d, n): (803633, 1040399)
// Original Word: Hi
// Original Message as Number: 18537
// Encrypted Message: 28975
// Decrypted Word: Hi
