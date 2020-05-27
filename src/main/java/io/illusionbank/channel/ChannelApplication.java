package io.illusionbank.channel;

import io.illusionbank.channel.command.ChargebackCommand;
import io.illusionbank.channel.command.PaymentCommand;
import io.illusionbank.channel.command.TransferMainframeCommand;
import io.illusionbank.channel.repository.ClientRepositoty;
import io.vertx.core.Vertx;

public class ChannelApplication {
	
	public static void main(String[] args) {
		step0();	
	}
	
	/**
	 * Para todas as transações o canal chama o mainframe diretamente
	 */
	private static void step0() { //TODOS apontam para o main frame
		var repositoty = new ClientRepositoty();
		
		var vertx = Vertx.vertx();
		
		var transferCommand = new TransferMainframeCommand();
		var paymentCommand = new PaymentCommand();
		var chargebackCommand = new ChargebackCommand();
		
		repositoty.getClients().forEach(client-> {			
			transferCommand.execute(vertx, client);
			paymentCommand.execute(vertx, client);
			chargebackCommand.execute(vertx, client);
		});
		
	}
	
	/**
	 * Algumas transações são chamadas pelo gateway, o gateway chama o mainframe
	 */
	private static void step1() {
		var repositoty = new ClientRepositoty();
		
		var vertx = Vertx.vertx();
		
		var transferCommand = new TransferMainframeCommand();
		var paymentCommand = new PaymentCommand();
		var chargebackCommand = new ChargebackCommand();
		
		repositoty.getClients().forEach(client->{			
			transferCommand.execute(vertx, client);
			paymentCommand.execute(vertx, client);
			chargebackCommand.execute(vertx, client);
		});
	}
	
	/**
	 * Todas as analises anti-fraudes devem passar pelo gateway
	 */
	private static void step2() {
		var repositoty = new ClientRepositoty();
		
		var vertx = Vertx.vertx();
		
		var transferCommand = new TransferMainframeCommand();
		var paymentCommand = new PaymentCommand();
		var chargebackCommand = new ChargebackCommand();
		
		repositoty.getClients().forEach(client->{			
			transferCommand.execute(vertx, client);
			paymentCommand.execute(vertx, client);
			chargebackCommand.execute(vertx, client);
		});
		
	}
}
