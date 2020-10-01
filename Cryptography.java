import java.util.ArrayList;
import java.util.List;

public class Cryptography {
	public static void main(String[] args) {
		System.out.println(productCipherEncrypt("helloworld", 3));
		System.out.println(productCipherDecrypt(productCipherEncrypt("helloworld", 3), 3));
	}

	static String productCipherEncrypt(String plainText, int key) {
		return transpositionCipherEncryption(substitutionCipherEncryption(plainText, key));
	}

	static String productCipherDecrypt(String cipherText, int key) {
		return substitutionCipherDecryption(transpositionCipherDecryption(cipherText), key);
	}

	static String substitutionCipherEncryption(String plainText, int key) {
		char[] plainTextArray = plainText.toCharArray();
		char[] cipherTextArray = new char[plainText.length()];
		for (int i = 0; i < plainText.length(); i++) {
			int num = plainTextArray[i] - 97;
			cipherTextArray[i] = (char) ((num + key) % 26 + 97);
		}
		return String.valueOf(cipherTextArray);
	}

	static String substitutionCipherDecryption(String cipherText, int key) {
		char[] cipherTextArray = cipherText.toCharArray();
		char[] plainTextArray = new char[cipherText.length()];
		for (int i = 0; i < cipherText.length(); i++) {
			int num = cipherTextArray[i] - 97;
			int fin = (num - key) > 0 ? (num - key) : (26 + ((num - key)));
			plainTextArray[i] = (char) (fin % 26 + 97);
		}
		return String.valueOf(plainTextArray);
	}

	static String transpositionCipherEncryption(String cipherText1) {
		char[] cipherText1Array = cipherText1.toCharArray();
		List<Character> cipherText1List = new ArrayList<Character>();
		for (int i = 0; i < cipherText1.length(); i++) {
			cipherText1List.add(new Character(cipherText1Array[i]));
		}
		if (cipherText1.length() % 2 != 0)
			cipherText1List.add(new Character('x'));
		char[] oddArray = new char[cipherText1.length() % 2 != 0 ? ((cipherText1.length() + 1) / 2)
				: cipherText1.length() / 2];
		char[] evenArray = new char[cipherText1.length() % 2 != 0 ? ((cipherText1.length() + 1) / 2)
				: cipherText1.length() / 2];
		int oddArrayCount = 0, evenArrayCount = 0;
		for (int i = 0; i < (cipherText1.length() % 2 != 0 ? cipherText1.length() + 1 : cipherText1.length()); i++) {
			if ((i + 1) % 2 != 0) {
				oddArray[oddArrayCount] = cipherText1List.get(i);
				oddArrayCount++;
			} else {
				evenArray[evenArrayCount] = cipherText1List.get(i);
				evenArrayCount++;
			}
		}
		cipherText1List.clear();
		oddArrayCount = evenArrayCount = 0;
		for (int i = 0; i < (cipherText1.length() % 2 != 0 ? ((cipherText1.length() + 1) / 2)
				: cipherText1.length() / 2); i++) {
			cipherText1List.add(new Character(oddArray[i]));
		}
		for (int i = 0; i < (cipherText1.length() % 2 != 0 ? ((cipherText1.length() + 1) / 2)
				: cipherText1.length() / 2); i++) {
			cipherText1List.add(new Character(evenArray[i]));
		}
		char[] finalCipher = new char[cipherText1.length() % 2 != 0 ? cipherText1.length() + 1 : cipherText1.length()];
		for (int i = 0; i < (cipherText1.length() % 2 != 0 ? cipherText1.length() + 1 : cipherText1.length()); i++) {
			finalCipher[i] = cipherText1List.get(i);
		}
		return String.valueOf(finalCipher);
	}

	static String transpositionCipherDecryption(String cipherText2) {
		char[] cipherText2Array = cipherText2.toCharArray();
		if (cipherText2.length() % 2 != 0) {
			System.err.println("Length cannot be odd");
			return null;
		} else {
			char[] oddArray = new char[cipherText2.length() / 2];
			char[] evenArray = new char[cipherText2.length() / 2];
			for (int i = 0; i < cipherText2.length() / 2; i++) {
				oddArray[i] = cipherText2Array[i];
			}
			int evenArrayCount = 0;
			for (int i = cipherText2.length() / 2; i < cipherText2.length(); i++) {
				evenArray[evenArrayCount] = cipherText2Array[i];
				evenArrayCount++;
			}
			int oddArrayCount = 0;
			evenArrayCount = 0;
			List<Character> finalPlainText = new ArrayList<Character>();
			for (int i = 0; i < cipherText2.length(); i++) {
				if ((i + 1) % 2 != 0) {
					finalPlainText.add(new Character(oddArray[oddArrayCount]));
					oddArrayCount++;
				} else {
					finalPlainText.add(new Character(evenArray[evenArrayCount]));
					evenArrayCount++;
				}
			}
			if (finalPlainText.get(cipherText2.length() - 1) == 'x') {
				finalPlainText.remove(cipherText2.length() - 1);
			}
			char[] finalPlain = new char[finalPlainText.size()];
			for (int i = 0; i < finalPlainText.size(); i++) {
				finalPlain[i] = finalPlainText.get(i);
			}
			return String.valueOf(finalPlain);
		}
	}
}
