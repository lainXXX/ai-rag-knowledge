package top.javrem.rag.api;

import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;

/**
 * @Author: rem
 * @Date: 2025/05/08/17:12
 * @Description:
 */
public interface IAIService {

    ChatResponse generate(String model, String message);

    Flux<ChatResponse> generateStream(String model, String message);



}
