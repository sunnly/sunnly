package wang.sunnly.modules.fileupload.client.controller;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import wang.sunnly.common.web.msg.result.ObjectResult;
import wang.sunnly.feign.uploadfile.encoder.FeignEncoder;
import wang.sunnly.modules.fileupload.client.feign.FileUploadDynamicFeign;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<Map<String, String>> upload(@RequestPart(value = "file") MultipartFile file) {

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());

        String labelUrl = "http://sunnly-modules-fileserver";
        List<ServiceInstance> instances = discoveryClient.getInstances("sunnly-modules-fileserver");
        labelUrl = "http://".concat(instances.get(0).getHost()).concat(":").concat(instances.get(0).getPort() + "");

        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignEncoder());
        FileUploadDynamicFeign uploadFeignService = encoder.target(FileUploadDynamicFeign.class, labelUrl);

        return uploadFeignService.upload(file);
    }

    @PostMapping(value = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<List<Map<String, String>>> upload(@RequestPart(value = "files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            System.out.println(file.getName());
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
        }
        String labelUrl = "http://sunnly-modules-fileserver";
        List<ServiceInstance> instances = discoveryClient.getInstances("sunnly-modules-fileserver");
        labelUrl = "http://".concat(instances.get(0).getHost()).concat(":").concat(instances.get(0).getPort() + "");

        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignEncoder());
        FileUploadDynamicFeign uploadFeignService = encoder.target(FileUploadDynamicFeign.class, labelUrl);

        return uploadFeignService.upload(files);
    }

    @PostMapping(value = "send")
    public ObjectResult<Map<String, String>> send() {

        MultipartFile file = getMulFile(new File("G:\\2019年个人年终工作总结.doc"));
        String labelUrl = "http://sunnly-modules-fileserver";
        List<ServiceInstance> instances = discoveryClient.getInstances("sunnly-modules-fileserver");
        labelUrl = "http://".concat(instances.get(0).getHost()).concat(":").concat(instances.get(0).getPort() + "");

        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignEncoder());
        FileUploadDynamicFeign uploadFeignService = encoder.target(FileUploadDynamicFeign.class, labelUrl);
        return uploadFeignService.upload(file);
    }

    @PostMapping(value = "sends")
    public ObjectResult<List<Map<String, String>>> sends() {

        MultipartFile[] files = {
                getMulFile(new File("G:\\2019年个人年终工作总结.doc")),
                getMulFile(new File("G:\\0801 自选股同花顺导入文档.sel"))
        };
        String labelUrl = "http://sunnly-modules-fileserver";
        List<ServiceInstance> instances = discoveryClient.getInstances("sunnly-modules-fileserver");
        labelUrl = "http://".concat(instances.get(0).getHost()).concat(":").concat(instances.get(0).getPort() + "");

        Feign.Builder encoder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new FeignEncoder());
        FileUploadDynamicFeign uploadFeignService = encoder.target(FileUploadDynamicFeign.class, labelUrl);
        return uploadFeignService.upload(files);
    }

    private FileItem createFileItem(File file, String fieldName) {
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        FileItem item = factory.createItem(fieldName, "text/plain", true, file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }

    public MultipartFile getMulFile(File file) {
        FileItem fileItem = createFileItem(file);
        MultipartFile mfile = new CommonsMultipartFile(fileItem);
        return mfile;
    }

    public FileItem createFileItem(File file) {
        String filePath = file.getPath();
        FileItemFactory factory = new DiskFileItemFactory(16, null);
        String textFieldName = "file";
        int num = filePath.lastIndexOf(".");
        String extFile = filePath.substring(num);
        FileItem item = factory.createItem(textFieldName, "multipart/form-data", true,
                file.getName());
        int bytesRead = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = item.getOutputStream();
            while ((bytesRead = fis.read(buffer, 0, BUFFER_SIZE))
                    != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }
}
