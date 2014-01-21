public class Printer03 {

	private static final Printer03 singleton = new Printer03();

	private Printer03() {
	}

	public static Printer03 getInstance() {
		return singleton;
	}

	public synchronized void print(String name) {

		for (int i = 0; i < 20; i++) {

			System.out.printf("%s %s\n", name, i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void print1(String name) {
		synchronized (this) {

			for (int i = 0; i < 20; i++) {

				System.out.printf("%s %s\n", name, i);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
