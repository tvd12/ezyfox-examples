package org.youngmonkeys.example.ezyhttp.website.user_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvd12.ezyfox.util.EzyFileUtil;
import com.tvd12.ezyhttp.core.response.ResponseEntity;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;
import lombok.AllArgsConstructor;
import org.youngmonkeys.example.ezyhttp.website.request.StoreJsonRequest;

import java.io.File;
import java.io.FileWriter;

@Controller("/api/v1")
@AllArgsConstructor
public class StoreController {

    private final ObjectMapper objectMapper;

    /**
     * <pre>
     * curl --location --request POST 'http://localhost:8080/api/v1/store/json' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "key": "Trái Đất",
     *     "value": "Màu Xanh Xanh"
     * }'
     * </pre>
     *
     * @param request the request body.
     * @throws Exception throw when there are is error.
     * @return 204 if success.
     */
    @DoPost("/store/json")
    public ResponseEntity storeJsonPost(
        @RequestBody StoreJsonRequest request
    ) throws Exception {
        File filePath = new File("build/store.json");
        EzyFileUtil.createFileIfNotExists(filePath);
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(objectMapper.writeValueAsString(request));
        }
        return ResponseEntity.noContent();
    }
}
