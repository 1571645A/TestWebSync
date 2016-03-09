import org.jasypt.util.text.BasicTextEncryptor;

public class TestJasypt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("P6 EPPM");
		String myEncryptedText = textEncryptor.encrypt("psix$PROD1");

		System.out.println("myEncryptedText=" + myEncryptedText);

		String plainText = textEncryptor.decrypt(myEncryptedText);

		System.out.println("plainText=" + plainText);
	}
}
