//package com.cml.framework.reactor.reactornetty;
//
//import org.reactivestreams.Subscriber;
//import org.reactivestreams.Subscription;
//import reactor.core.publisher.Mono;
//import reactor.netty.Connection;
//import reactor.netty.tcp.TcpClient;
//
//import java.util.concurrent.locks.LockSupport;
//
//public class ClientApp {
//    public static void main(String[] args) {
//
//        Connection connection = TcpClient
//                .create()
//                .port(8080)
//                .doOnConnected(t -> {
//                    System.out.println("=====doOnConnected==============");
//                    t.outbound().options(s -> s.flushOnEach()).sendString(Mono.just("client send init!!!")).then().subscribe(new Subscriber<Void>() {
//                        @Override
//                        public void onSubscribe(Subscription s) {
//
//                        }
//
//                        @Override
//                        public void onNext(Void aVoid) {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable t) {
//                            System.out.println("send error");
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            System.out.println("send completed");
//                        }
//                    });
//                })
//                .handle((in, out) -> {
//                    System.out.println("===========client handle======");
//                    out.options(s -> s.flushOnEach()).sendString(Mono.just("client send init!!!")).then();
//                    return in.receive().asString().flatMap(s -> {
//                        String str = s.toString();
//                        System.out.println("===============receive==>" + str);
//                        return Mono.just(str);
//                    }).flatMap(s -> out.options(t -> t.flushOnEach(false)).sendString(Mono.just("response from client!!!")));
//                })
//                .doOnDisconnected(t -> {
//                    System.out.println("==doOnDisconnected=====");
//                })
//                .doOnConnect(t -> {
//                    System.out.println("===doOnConnect====");
//                })
//                .connectNow();
//        System.out.println("----client start--------");
////        connection.onDispose().block();
//        LockSupport.park();
//    }
//}
