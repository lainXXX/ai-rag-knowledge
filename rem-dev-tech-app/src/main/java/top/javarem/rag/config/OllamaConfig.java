package top.javarem.rag.config;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.ollama.OllamaEmbeddingClient;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.PgVectorStore;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class OllamaConfig {

    /**
     * 创建与 Ollama 服务的连接
     * @param baseUrl  Ollama 服务地址
     * @return 可用于与 Ollama 服务交互的 API 客户端
     */
    @Bean
    public OllamaApi ollamaApi(@Value("${spring.ai.ollama.base-url}") String baseUrl) {
        return new OllamaApi(baseUrl);
    }

    /**
     * 创建聊天功能客户端
     * @param ollamaApi 创建的 OllamaApi
     * @return 可以发送消息给 Ollama 模型并接收回复
     */
    @Bean
    public OllamaChatClient ollamaChatClient(OllamaApi ollamaApi) {
        return new OllamaChatClient(ollamaApi);
    }

    /**
     * 将长文本分割成适合模型处理的片段
     * @return 文本分割器
     */
    @Bean
    public TokenTextSplitter tokenTextSplitter() {
        return new TokenTextSplitter();
    }

    /**
     * 创建文本嵌入(embedding)客户端
     * 使用 "nomic-embed-text" 模型将文本转换为向量
     * @param ollamaApi 读取Ollama服务的地址对象
     * @return 简单仓库
     */
    @Bean
    public SimpleVectorStore simpleVectorStore(OllamaApi ollamaApi) {
        OllamaEmbeddingClient embeddingClient = new OllamaEmbeddingClient(ollamaApi);
        embeddingClient.withDefaultOptions(OllamaOptions.create().withModel("nomic-embed-text"));
        return new SimpleVectorStore(embeddingClient);
    }

    /**
     * 基于 PostgreSQL 的向量存储
     * 支持大规模向量数据
     * 支持向量相似度搜索
     * @param ollamaApi 与 Ollama 服务的连接
     * @param jdbcTemplate Spring JDBC 的 JdbcTemplate
     * @return 数据库仓库
     */
    @Bean
    public PgVectorStore pgVectorStore(OllamaApi ollamaApi, JdbcTemplate jdbcTemplate) {
        OllamaEmbeddingClient embeddingClient = new OllamaEmbeddingClient(ollamaApi);
        embeddingClient.withDefaultOptions(OllamaOptions.create().withModel("nomic-embed-text"));
        return new PgVectorStore(jdbcTemplate, embeddingClient);
    }

}
