package client.main;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

public class ConnectionManager {

    private final Mono<RSocket> socket;

    public ConnectionManager(int ip) {
        socket = RSocketConnector.connectWith(TcpClientTransport.create("localhost",ip));
    }

    public String communication(String string) {
        return socket.block()
                .requestResponse(DefaultPayload.create(string))
                .map(Payload::getDataUtf8)
                .block();
    }
}
