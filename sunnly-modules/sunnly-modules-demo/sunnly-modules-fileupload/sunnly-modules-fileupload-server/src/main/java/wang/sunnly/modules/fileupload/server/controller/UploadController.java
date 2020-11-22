package wang.sunnly.modules.fileupload.server.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wang.sunnly.common.web.msg.result.ObjectResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UploadController
 *
 * @author Sunnly
 * @since 2020/10/30 11:33
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<Map<String, String>> upload(@RequestPart(value = "file") MultipartFile file) {

        Map<String, String> map = new HashMap<>(16);
        map.put("name", file.getName());
        map.put("type", file.getContentType());
        map.put("oname", file.getOriginalFilename());

        return new ObjectResult<>(map).success();
    }

    @PostMapping(value = "files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectResult<List<Map<String, String>>> upload(@RequestPart(value = "files") MultipartFile[] files) {

        List<Map<String, String>> list = new ArrayList<>(16);
        for (MultipartFile file : files) {
            Map<String, String> map = new HashMap<>(16);
            map.put("name", file.getName());
            map.put("type", file.getContentType());
            map.put("oname", file.getOriginalFilename());
            list.add(map);
        }

        return new ObjectResult<>(list).success();
    }
}
