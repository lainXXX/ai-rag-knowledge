package top.javrem.rag.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: rem
 * @Date: 2025/05/12/14:29
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

    private String code;

    private String info;

    private T data;

    private static final long serialVersionUID = 1L;

}
