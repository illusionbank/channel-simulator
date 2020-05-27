package io.illusionbank.channel.command;

import io.vertx.core.Vertx;

public interface Command {
	void execute(Vertx vertx, String client);
}
