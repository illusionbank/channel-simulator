package io.illusionbank.channel.command;

import io.illusionbank.common.book.TransferBook;
import io.illusionbank.common.enumeration.Books;
import io.illusionbank.common.mapper.BookMapper;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransferMainframeCommand  implements Command {
	private BookMapper bookMapper = new BookMapper();

	public void execute(Vertx vertx, String client) {
		NetClient tcpClient = vertx.createNetClient();
		
		tcpClient.connect(7171, "localhost", connectHandler-> {
			
			if(connectHandler.succeeded()) {
				System.out.println("CONNCTED");
				NetSocket socket =  connectHandler.result();
				socket.handler(buffer-> {
					System.out.println(buffer.toString());
					tcpClient.close();
					vertx.close();
				});
				
				socket.exceptionHandler(ex->{
					log.error("{}", ex);
				});
				
				String transferBookAsString = bookMapper.serializeTransferBook(transferBookUser(client));
				socket.write(Buffer.buffer(transferBookAsString));
				
			} else {
				tcpClient.close();
				log.error("{}", connectHandler.cause());
			}
		});
	}
	
	private TransferBook transferBookUser(String client) {
		TransferBook transferBook =  new TransferBook();
		transferBook.setAgency("00007871");
		transferBook.setAccount("00419889");
		transferBook.setClient(client);
		transferBook.setBankFavored("089");
		transferBook.setAgencyFavored("00001499");
		transferBook.setAccountFavored("00989098");
		transferBook.setTransactionSec(Books.IBCTATRA.toString());
		transferBook.setTransactionBank("012345678910");
		transferBook.setIp("109.167.198.098");
		transferBook.setDeviceName("Device X");
		transferBook.setLocation("00200689,-346909816");
		transferBook.setValue(100.56);
		return transferBook;
	}

}
