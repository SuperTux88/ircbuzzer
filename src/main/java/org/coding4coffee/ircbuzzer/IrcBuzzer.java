package org.coding4coffee.ircbuzzer;

import org.jibble.pircbot.PircBot;

public class IrcBuzzer extends PircBot {

	private static final String BOT_NAME = "gamebot";
	private static final String START_MESSAGE = "Los gehts!";
	private static final String STOP_MESSAGE = "Runde beendet!";
	private static final String BUZZER_SUCCESS_MESSAGE = " ist dran!";

	private final BuzzerListener gui;

	private final String channel;

	public IrcBuzzer(final BuzzerListener gui, final String nick, final String channel) {
		setName(nick);
		this.gui = gui;
		this.channel = channel;
	}

	@Override
	protected void onConnect() {
		joinChannel(channel);
	}

	@Override
	protected void onMessage(final String channel, final String sender, final String login, final String hostname,
			final String message) {
		if (BOT_NAME.equals(sender)) {
			System.out.println(message);
			if (START_MESSAGE.equals(message)) {
				gui.setActive(true);
			} else if (STOP_MESSAGE.equals(message) || message.contains(BUZZER_SUCCESS_MESSAGE)) {
				gui.setActive(false);
			}
		}
	}

	public void buzz() {
		sendMessage(channel, "MÖÖÖP");
	}
}