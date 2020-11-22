package wang.sunnly.modules.fileupload.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * FileUploadServerApplication
 *
 * @author Sunnly
 * @since 2020/10/30 11:31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FileUploadServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploadServerApplication.class, args);
    }
}
