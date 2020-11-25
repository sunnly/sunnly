package wang.sunnly.modules.fileupload.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wang.sunnly.common.web.msg.result.ObjectResult;
import wang.sunnly.feign.uploadfile.utils.MacroFeignUtils;
import wang.sunnly.feign.uploadfile.utils.MacroFileUtils;
import wang.sunnly.modules.fileupload.client.feign.FileUploadDynamicFeign;
import wang.sunnly.nacos.support.monitor.service.NacosRegistrateService;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * DynamicUploadController
 *
 * @author Sunnly
 * @since 2020/10/30 11:14
 */
@RestController
@RequestMapping("dynamic")
public class DynamicUploadController {

    private static final int BUFFER_SIZE = 8192;

    @Value("${macro.feign.file.service}")
    private String feingService;

    @Resource
    private MacroFileUtils marcoFileUtils;

    @Resource
    private MacroFeignUtils macroFeignUtils;

    @Resource
    private NacosRegistrateService nacosRegistrateService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<Map<String, String>> upload(@RequestPart(value = "file") MultipartFile file) {

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());

        FileUploadDynamicFeign uploadFeignService =
                macroFeignUtils.getFeignService(FileUploadDynamicFeign.class, feingService);
        return uploadFeignService.upload(file);
    }

    @PostMapping(value = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<List<Map<String, String>>> upload(@RequestPart(value = "files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            System.out.println(file.getName());
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
        }
        FileUploadDynamicFeign uploadFeignService =
                macroFeignUtils.getFeignService(FileUploadDynamicFeign.class, feingService);
        return uploadFeignService.upload(files);
    }

    @PostMapping(value = "send")
    public ObjectResult<Map<String, String>> send() {

        MultipartFile file = marcoFileUtils.getMultipartFile(new File("D:\\1.txt"));
        FileUploadDynamicFeign uploadFeignService =
                macroFeignUtils.getFeignService(FileUploadDynamicFeign.class, feingService);
        return uploadFeignService.upload(file);
    }

    @PostMapping(value = "sends")
    public ObjectResult<List<Map<String, String>>> sends() {

        MultipartFile[] files = {
                marcoFileUtils.getMultipartFile(new File("D:\\2.txt")),
                marcoFileUtils.getMultipartFile(new File("D:\\1.txt"))
        };
        FileUploadDynamicFeign uploadFeignService =
                macroFeignUtils.getFeignService(FileUploadDynamicFeign.class, feingService);
        return uploadFeignService.upload(files);
    }
}
