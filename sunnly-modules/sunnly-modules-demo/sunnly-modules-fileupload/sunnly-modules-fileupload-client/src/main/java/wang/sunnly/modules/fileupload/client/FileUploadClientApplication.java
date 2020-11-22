package wang.sunnly.modules.fileupload.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * FileUploadClientApplication
 *
 * @author Sunnly
 * @since 2020/10/30 9:50
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan({"wang.sunnly.feign.uploadfile.config","wang.sunnly.modules.fileupload.client"})
public class FileUploadClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadClientApplication.class, args);
    }
}
