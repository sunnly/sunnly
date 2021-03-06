package wang.sunnly.modules.fileupload.client.feign;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.feign.uploadfile.config.FeignSupportConfig;

import java.util.List;
import java.util.Map;

/**
 * FileUploadGeneralFeign
 *
 * @author Sunnly
 * @since 2020/10/30 11:45
 */
@FeignClient(name = "${macro.feign.file.service}", configuration = FeignSupportConfig.class)
public interface FileUploadGeneralFeign {

    /**
     * 文件上传feign
     * @param file 上传文件
     * @return 返回上传后信息
     */
    @PostMapping(value = "upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ObjectResponse<Map<String, String>> upload(@RequestPart(value = "file") MultipartFile file);

    /**
     * 多文件上传feign，调试未成功
     * @param files 上传文件数组
     * @return 返回上传后信息
     */
    @PostMapping(value = "upload/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ObjectResponse<List<Map<String, String>>> upload(@Param(value = "files") MultipartFile[] files);
}
