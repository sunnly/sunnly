package wang.sunnly.modules.fileupload.client.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import wang.sunnly.common.web.msg.result.ObjectResult;
import wang.sunnly.modules.fileupload.client.feign.FileUploadGeneralFeign;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * GeneralUploadController
 *
 * @author Sunnly
 * @since 2020/10/30 11:14
 */
@RestController
@RequestMapping("general")
public class GeneralUploadController {
    
    private static final int BUFFER_SIZE = 8192;

    @Autowired
    private FileUploadGeneralFeign fileUploadGeneralFeign;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<Map<String, String>> upload(@RequestPart(value = "file") MultipartFile file) {

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        return fileUploadGeneralFeign.upload(file);
    }

    @PostMapping(value = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<List<Map<String, String>>> upload(@RequestPart(value = "files") MultipartFile[] files) {

        for (MultipartFile file : files) {
            System.out.println(file.getName());
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
        }
        return fileUploadGeneralFeign.upload(files);
    }

    @PostMapping(value = "send")
    public ObjectResult<Map<String, String>> send(HttpServletRequest request) throws IOException, ServletException {
        MultipartFile file = getMulFile(new File("D:\\1.txt"));
        return fileUploadGeneralFeign.upload(file);
    }

    @PostMapping(value = "sends")
    public ObjectResult<List<Map<String, String>>> sends() {

        //TODO 测试未成功，传入参数为空，待审查
        MultipartFile[] files = {
                getMulFile(new File("D:\\1.txt")),
                getMulFile(new File("D:\\2.txt"))
        };
        return fileUploadGeneralFeign.upload(files);
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
