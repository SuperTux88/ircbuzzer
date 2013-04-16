package org.coding4coffee.ircbuzzer;

public class BuzzerMain {

	public static void main(final String[] args) {
		if (args.length < 2) {
			System.out.println("usage: java -jar ircbuzzer.runnable.jar nick channel");
			System.exit(1);
		}

		new BuzzerListener().startListening(args[0], args[1]);
	}
}
