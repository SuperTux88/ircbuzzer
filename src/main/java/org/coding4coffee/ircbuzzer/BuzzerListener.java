package org.coding4coffee.ircbuzzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BuzzerListener implements Runnable {

	private IrcBuzzer client;

	private boolean active;

	public void startListening(final String nick, final String channel) {
		client = new IrcBuzzer(this, nick, channel);
		client.setAutoNickChange(true);
		try {
			client.connect("irc.theradio.cc");
		} catch (final Exception e) {
			e.printStackTrace();
		}

		new Thread(this).start();
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

	@Override
	public void run() {
		final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				reader.readLine();
			} catch (final IOException e) {
				e.printStackTrace();
			}

			if (active) {
				client.buzz();
			}
		}
	}
}
