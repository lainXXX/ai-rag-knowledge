package top.javarem.rag.trigger.http;

import org.springframework.ai.chat.ChatResponse;
import reactor.core.publisher.Flux;
import top.javrem.rag.api.IAIService;

/**
 * @Author: rem
 * @Date: 2025/05/09/17:36
 * @Description:
 */
public class OllamaController implements IAIService {
    @Override
    public ChatResponse generate(String model, String message) {
        return null;
    }

    @Override
    public Flux<ChatResponse> generateStream(String model, String message) {
        return null;
    }
}
