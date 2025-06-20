package org.scoula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //WebSocketMessageBroker 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //메시지 브로커 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        //구독시 사용할 토픽 접두어
        //메모리 기반 브로커 활성화, 처리할 토픽을 매개변수로 지정
        config.enableSimpleBroker("/topic");

        //클라이언트가 발행 시 사용해야하는 접두어
        //메시지 경로의 접두어 => @MessageMapping("/hello")->/app/hello
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        //클라이언트가 접속할 때 사용할 url 경로 (브로커 url)
        registry.addEndpoint("/chat-app") //접속 엔드포인트, ws://localhost:8080/chat-app
                .setAllowedOrigins("*"); //CORS 허용
    }
}
