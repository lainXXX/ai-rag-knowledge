package top.javrem.rag.api;

import org.springframework.web.multipart.MultipartFile;
import top.javrem.rag.api.response.Response;

import java.util.List;

public interface IRAGService {

    Response<List<String>> queryRagTagList();

    Response<String> uploadFile(String ragTag, List<MultipartFile> files);

}
