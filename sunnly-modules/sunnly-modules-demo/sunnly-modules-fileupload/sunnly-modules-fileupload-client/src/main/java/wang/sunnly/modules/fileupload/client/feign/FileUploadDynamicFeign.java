package wang.sunnly.modules.fileupload.client.feign;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.multipart.MultipartFile;
import wang.sunnly.common.web.msg.result.ObjectResponse;

import java.util.List;
import java.util.Map;

/**
 * FileUploadDynamicFeign
 *
 * @author Sunnly
 * @since 2020/10/30 11:47
 */
public interface FileUploadDynamicFeign {

    /**
     * 文件上传feign接口
     * @param file 上传文件
     * @return 上传后返回信息
     */
    @RequestLine("POST /upload/file")
    ObjectResponse<Map<String, String>> upload(@Param("file")  MultipartFile file);

    /**
     * 多文件上传feign接口
     * @param files 文件列表
     * @return 上传后返回信息
     */
    @RequestLine("POST /upload/files")
    ObjectResponse<List<Map<String, String>>> upload(@Param("files")  MultipartFile[] files);
}
