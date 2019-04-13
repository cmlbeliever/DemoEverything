//package com.cml.framework.reactor.reactornetty;
//
//import reactor.core.publisher.Mono;
//import reactor.netty.DisposableServer;
//import reactor.netty.tcp.TcpServer;
//
//import java.util.concurrent.locks.LockSupport;
//
//public class ServerApp {
//
//    public static void main(String[] args) {
//
//        DisposableServer disposableServer = TcpServer
//                .create()
//                .port(8080)
//                .doOnConnection(connection -> {
////                    connection.addHandler(new ReadTimeoutHandler(10, TimeUnit.SECONDS));
////                    connection.addHandlerLast(new FixedLengthFrameDecoder(1024));
////                    connection.addHandlerLast(new StringDecoder());
////                    connection.addHandlerLast(new StringEncoder());
//                })
//                .handle((in, out) -> {
//                    System.out.println("<<<handle>>>");
//                    in.receive().asString().flatMap(s -> {
//                        String str = s.toString();
//                        System.out.println("===============receive==>" + str);
//                        return Mono.just(str);
//                    }).map(t -> {
//                        System.out.println("======>" + t);
//                        return t;
//                    }).then();
//                    return out.neverComplete();
////                             .flatMap(t -> {
////                        return out.options(s -> s.flushOnEach()).sendString(Mono.just(t + "xxxx"));
////                    });
//                })
//                .bindNow();
//        System.out.println("-----sever start---------");
////        disposableServer.onDispose().block();
//        System.out.println("end");
//        LockSupport.park();
//    }
//}
