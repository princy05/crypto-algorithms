
import java.io.IOException;
import java.util.*;

public class DES {
	
	public static int[] C = new int[28];
	public static int[] D = new int[28];

	public static String encryptedData = "";
	public static String decryptedData = "";
	public static String key = "";

	public static int[][] subkey = new int[16][48];
	
	// look up table for initial permutation
	public static final byte[] InitialPermutation = { 
		58, 50, 42, 34, 26, 18, 10, 2,
		60, 52, 44, 36, 28, 20, 12, 4,
		62, 54, 46, 38, 30, 22, 14, 6,
		64, 56, 48, 40, 32, 24, 16, 8,
		57, 49, 41, 33, 25, 17, 9,  1,
		59, 51, 43, 35, 27, 19, 11, 3,
		61, 53, 45, 37, 29, 21, 13, 5,
		63, 55, 47, 39, 31, 23, 15, 7
	};
	
	// look up table for permuted choice 1
	public static final byte[] PermutedChoice1 = {
		57, 49, 41, 33, 25, 17, 9,
		1,  58, 50, 42, 34, 26, 18,
		10, 2,  59, 51, 43, 35, 27,
		19, 11, 3,  60, 52, 44, 36,
		63, 55, 47, 39, 31, 23, 15,
		7,  62, 54, 46, 38, 30, 22,
		14, 6,  61, 53, 45, 37, 29,
		21, 13, 5,  28, 20, 12, 4
	};
	
	// look up table for permuted choice 2
	public static final byte[] PermutedChoice2 = {
		14, 17, 11, 24, 1,  5,
		3,  28, 15, 6,  21, 10,
		23, 19, 12, 4,  26, 8,
		16, 7,  27, 20, 13, 2,
		41, 52, 31, 37, 47, 55,
		30, 40, 51, 45, 33, 48,
		44, 49, 39, 56, 34, 53,
		46, 42, 50, 36, 29, 32
	};
	
	// number of shifts needed based on the round of transformation
	public static final byte[] shifting = {
		1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
	};
	
	// look up table for expansion
	public static final byte[] Expansion = {
		32, 1,  2,  3,  4,  5,
		4,  5,  6,  7,  8,  9,
		8,  9,  10, 11, 12, 13,
		12, 13, 14, 15, 16, 17,
		16, 17, 18, 19, 20, 21,
		20, 21, 22, 23, 24, 25,
		24, 25, 26, 27, 28, 29,
		28, 29, 30, 31, 32, 1
	};
	
	// look up table for permutation
	public static final byte[] P = {
			16, 7,  20, 21,
			29, 12, 28, 17,
			1,  15, 23, 26,
			5,  18, 31, 10,
			2,  8,  24, 14,
			32, 27, 3,  9,
			19, 13, 30, 6,
			22, 11, 4,  25
		};
		
	
	// look up table for Substitution function
	public static final byte[][] SBox = { {
		14, 4,  13, 1,  2,  15, 11, 8,  3,  10, 6,  12, 5,  9,  0,  7,
		0,  15, 7,  4,  14, 2,  13, 1,  10, 6,  12, 11, 9,  5,  3,  8,
		4,  1,  14, 8,  13, 6,  2,  11, 15, 12, 9,  7,  3,  10, 5,  0,
		15, 12, 8,  2,  4,  9,  1,  7,  5,  11, 3,  14, 10, 0,  6,  13
	}, {
		15, 1,  8,  14, 6,  11, 3,  4,  9,  7,  2,  13, 12, 0,  5,  10,
		3,  13, 4,  7,  15, 2,  8,  14, 12, 0,  1,  10, 6,  9,  11, 5,
		0,  14, 7,  11, 10, 4,  13, 1,  5,  8,  12, 6,  9,  3,  2,  15,
		13, 8,  10, 1,  3,  15, 4,  2,  11, 6,  7,  12, 0,  5,  14, 9
	}, {
		10, 0,  9,  14, 6,  3,  15, 5,  1,  13, 12, 7,  11, 4,  2,  8,
		13, 7,  0,  9,  3,  4,  6,  10, 2,  8,  5,  14, 12, 11, 15, 1,
		13, 6,  4,  9,  8,  15, 3,  0,  11, 1,  2,  12, 5,  10, 14, 7,
		1,  10, 13, 0,  6,  9,  8,  7,  4,  15, 14, 3,  11, 5,  2,  12
	}, {
		7,  13, 14, 3,  0,  6,  9,  10, 1,  2,  8,  5,  11, 12, 4,  15,
		13, 8,  11, 5,  6,  15, 0,  3,  4,  7,  2,  12, 1,  10, 14, 9,
		10, 6,  9,  0,  12, 11, 7,  13, 15, 1,  3,  14, 5,  2,  8,  4,
		3,  15, 0,  6,  10, 1,  13, 8,  9,  4,  5,  11, 12, 7,  2,  14
	}, {
		2,  12, 4,  1,  7,  10, 11, 6,  8,  5,  3,  15, 13, 0,  14, 9,
		14, 11, 2,  12, 4,  7,  13, 1,  5,  0,  15, 10, 3,  9,  8,  6,
		4,  2,  1,  11, 10, 13, 7,  8,  15, 9,  12, 5,  6,  3,  0,  14,
		11, 8,  12, 7,  1,  14, 2,  13, 6,  15, 0,  9,  10, 4,  5,  3
	}, {
		12, 1,  10, 15, 9,  2,  6,  8,  0,  13, 3,  4,  14, 7,  5,  11,
		10, 15, 4,  2,  7,  12, 9,  5,  6,  1,  13, 14, 0,  11, 3,  8,
		9,  14, 15, 5,  2,  8,  12, 3,  7,  0,  4,  10, 1,  13, 11, 6,
		4,  3,  2,  12, 9,  5,  15, 10, 11, 14, 1,  7,  6,  0,  8,  13
	}, {
		4,  11, 2,  14, 15, 0,  8,  13, 3,  12, 9,  7,  5,  10, 6,  1,
		13, 0,  11, 7,  4,  9,  1,  10, 14, 3,  5,  12, 2,  15, 8,  6,
		1,  4,  11, 13, 12, 3,  7,  14, 10, 15, 6,  8,  0,  5,  9,  2,
		6,  11, 13, 8,  1,  4,  10, 7,  9,  5,  0,  15, 14, 2,  3,  12
	}, {
		13, 2,  8,  4,  6,  15, 11, 1,  10, 9,  3,  14, 5,  0,  12, 7,
		1,  15, 13, 8,  10, 3,  7,  4,  12, 5,  6,  11, 0,  14, 9,  2,
		7,  11, 4,  1,  9,  12, 14, 2,  0,  6,  10, 13, 15, 3,  5,  8,
		2,  1,  14, 7,  4,  10, 8,  13, 15, 12, 9,  0,  3,  5,  6,  11
	} };
	
	
	// look up table for final permutation
	public static final byte[] FinalPermutation= {
		40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31,
		38, 6, 46, 14, 54, 22, 62, 30,
		37, 5, 45, 13, 53, 21, 61, 29,
		36, 4, 44, 12, 52, 20, 60, 28,
		35, 3, 43, 11, 51, 19, 59, 27,
		34, 2, 42, 10, 50, 18, 58, 26,
		33, 1, 41, 9, 49, 17, 57, 25
	};
	
	
	public static int[] firstSplit(String input) {
		int firstInput[] = new int[64];
		for(int i=0 ; i < 16 ; i++) {

			String s = Integer.toBinaryString(Integer.parseInt(input.charAt(i) + "", 16));

			while(s.length() < 4) {
				s = "0" + s;
			}

			for(int j=0 ; j < 4 ; j++) {
				int a = Integer.parseInt(s.charAt(j)+"");
				firstInput[(4*i)+j] = Integer.parseInt(s.charAt(j) + "");
			}
			
		}
		return firstInput;
		
	}
	public static int[] secondSplit(String input) {
		int secondInput[] = new int[64];
		for(int i=0 ; i < 16 ; i++) {

			String s = Integer.toBinaryString(Integer.parseInt(input.charAt(i+16) + "", 16));
			
			while(s.length() < 4) {
				s = "0" + s;
			}

			for(int j=0 ; j < 4 ; j++) {
				int a = Integer.parseInt(s.charAt(j)+"");
				secondInput[(4*i)+j] = Integer.parseInt(s.charAt(j) + "");
			}
			
		}
		return secondInput;
		
	}
	
	public static void main(String args[]) throws IOException {
		//allows the user to enter the input text
		System.out.println("Enter the input as a 32 character hexadecimal value:");
		String input = new Scanner(System.in).nextLine();
		int firstInput[]= new int[64];
		int secondInput[]= new int[64];
		
		int firstKey [] = new int[64];
		int secondKey [] = new int[64];

		
		firstInput = firstSplit(input);
		secondInput = secondSplit(input);
		
		// allows the user to enter the key
		System.out.println("Enter the key as a 32 character hexadecimal value:");
		key = new Scanner(System.in).nextLine();
		firstKey = firstSplit(key);
		secondKey = secondSplit(key);

		int firstOutputBits[] = desAlgo(firstInput, firstKey, false);
		desAlgo(firstOutputBits, firstKey, true);
		int secondOutputBits[] = desAlgo(secondInput, secondKey, false);

		System.out.println("Encrypted Data from DES algorithm >> " + encryptedData);
		AESAlgo aes = new AESAlgo();
		String aesValue = aes.aesAlgo(encryptedData, key);
		int firstDecryptedBits[] = firstSplit(aesValue);
		int secondDecryptedBits[] = secondSplit(aesValue);
		
		desAlgo(secondDecryptedBits, secondKey, true);
		System.out.println("Decrypted Data from DES algorithm >> " + decryptedData);
		
	}
	
	//This method performs both encryption and decryption function of DES algorithm
	public static int[] desAlgo(int[] inputBits, int[] keyBits, boolean isDecrypt) {
		//the input bits are initially permuted based on initial permutation lookup table
		int newBits[] = new int[inputBits.length];
		for(int i=0 ; i < inputBits.length ; i++) {
			newBits[i] = inputBits[InitialPermutation[i]-1];
		}
		
		int L[] = new int[32];
		int R[] = new int[32];
		int i;
		
		// 56-bit key is reduced to 48-bit key using permuted choice 1 look up table 
		for(i=0 ; i < 28 ; i++) {
			C[i] = keyBits[PermutedChoice1[i]-1];
		}
		for( ; i < 56 ; i++) {
			D[i-28] = keyBits[PermutedChoice1[i]-1];
		}

		System.arraycopy(newBits, 0, L, 0, 32);
		System.arraycopy(newBits, 32, R, 0, 32);

		//this loop runs for 16 rounds as per DES algorithm
		for(int n=0 ; n < 16 ; n++) {
			

			int newR[] = new int[0];
			if(isDecrypt) {
				newR = fiestelOperation(R, subkey[15-n]);
			} else {
				newR = fiestelOperation(R, subKeyGenerator(n, keyBits));
			}
			// xor the new right half obtained after processing through fiestel structure with left half of the input text
			int newL[] = xor(L, newR);
			L = R;
			R = newL;
		}
		
		//swapping left and right halves before performing final permutation operation
		int output[] = new int[64];
		System.arraycopy(R, 0, output, 0, 32);
		System.arraycopy(L, 0, output, 32, 32);
		int finalOutput[] = new int[64];
		
		//final output after performing final permutation operation
		for(i=0 ; i < 64 ; i++) {
			finalOutput[i] = output[FinalPermutation[i]-1];
		}
		
		// convert final output in integer type to hexadecimal value
		String hex = new String();
		for(i=0 ; i < 16 ; i++) {
			String binary = new String();
			for(int j=0 ; j < 4 ; j++) {
				binary += finalOutput[(4*i)+j];
			}
			int decimal = Integer.parseInt(binary, 2);
			hex += Integer.toHexString(decimal);
		}
		if(isDecrypt) {
			//System.out.print("Decrypted text: ");
			decryptedData = decryptedData + hex.toUpperCase();
		
		} else {
			//System.out.print("Encrypted text: ");
			encryptedData = encryptedData + hex.toUpperCase();
		}
		
		//System.out.println(hex.toUpperCase());
		return finalOutput;
	}
	
	public static int[] subKeyGenerator(int round, int[] key) {
		// The function generates the sub key needed for XOR operation.
		// C1 and D1 are the new values of C and D which will be generated in
		// this round.
		int C1[] = new int[28];
		int D1[] = new int[28];

		int rotationTimes = (int) shifting[round];

		//This method is used to left shift the key bits
		C1 = lShift(C, rotationTimes);
		D1 = lShift(D, rotationTimes);
		
		// CnDn stores the combined C1 and D1 halves
		int CnDn[] = new int[56];
		System.arraycopy(C1, 0, CnDn, 0, 28);
		System.arraycopy(D1, 0, CnDn, 28, 28);
		
		// Kn stores the subkey, which is generated by applying the PermutedChoice2 table
		int Kn[] = new int[48];
		for(int i=0 ; i < Kn.length ; i++) {
			Kn[i] = CnDn[PermutedChoice2[i]-1];
		}
		subkey[round] = Kn;
		C = C1;
		D = D1;
		return Kn;
	}
	
	public static int[] fiestelOperation(int[] R, int[] roundKey) {
		// This method performs fiestel operation on right half of the input text.
		int expandedR[] = new int[48];
		for(int i=0 ; i < 48 ; i++) {
			expandedR[i] = R[Expansion[i]-1];
		}
		// XOR the newly generated right half and sub key for that particular round
		int temp[] = xor(expandedR, roundKey);
		// apply substitution and permutation using S-Box and P-boxes
		int output[] = sBlock(temp);
		return output;
	}
	
	public static int[] xor(int[] a, int[] b) {
		// performs XOR operation of two bits
		int answer[] = new int[a.length];
		for(int i=0 ; i < a.length ; i++) {
			answer[i] = a[i]^b[i];
		}
		return answer;
	}
	
	public static int[] sBlock(int[] bits) {
		// This method applies substitution  and permutation function.
		int output[] = new int[32];
		
		for(int i=0 ; i < 8 ; i++) {
			// after xor operation of sub key and right half of the data, we have 48 bit output. consider 8 6-bits value from this 48-bit 
			//output. the first and last bit are considered for row index
			int row[] = new int [2];
			row[0] = bits[6*i];
			row[1] = bits[(6*i)+5];
			String rowIndex = row[0] + "" + row[1];
			//the remaining 4 bits in the 6-bit value is used for column index
			int column[] = new int[4];
			column[0] = bits[(6*i)+1];
			column[1] = bits[(6*i)+2];
			column[2] = bits[(6*i)+3];
			column[3] = bits[(6*i)+4];
			String columnIndex = column[0] +""+ column[1] +""+ column[2] +""+ column[3];
			
			int iRow = Integer.parseInt(rowIndex, 2);
			int iColumn = Integer.parseInt(columnIndex, 2);
			int x = SBox[i][(iRow*16) + iColumn];
			// convert decimal value of S-Box to binary
			String s = Integer.toBinaryString(x);
			// Padding operation
			while(s.length() < 4) {
				s = "0" + s;
			}
			// appending the binary bits to the output
			for(int j=0 ; j < 4 ; j++) {
				output[(i*4) + j] = Integer.parseInt(s.charAt(j) + "");
			}
		}
		// Permutation table is applied to the output:
		int finalOutput[] = new int[32];
		for(int i=0 ; i < 32 ; i++) {
			finalOutput[i] = output[P[i]-1];
		}
		return finalOutput;
	}
	
	public static int[] lShift(int[] bits, int n) {
		// Left shift operation is performed to left shift the bits based on the iteration number
		int output[] = new int[bits.length];
		System.arraycopy(bits, 0, output, 0, bits.length);
		for(int i=0 ; i < n ; i++) {
			int temp = output[0];
			for(int j=0 ; j < bits.length-1 ; j++) {
				output[j] = output[j+1];
			}
			output[bits.length-1] = temp;
		}
		return output;
	}
	
	
}