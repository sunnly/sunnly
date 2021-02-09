package wang.sunnly.modules.encrypt.controller;

import com.alibaba.nacos.common.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.sysinfo.info.SystemHardwareInfo;

import javax.annotation.Resource;

/**
 * SystemInfoController
 *
 * @author Sunnly
 * @since 2021/1/18
 */
@RestController
@RequestMapping("system")
public class SystemInfoController {

    @Resource
    private SystemInfo si;

    @Resource
    public HardwareAbstractionLayer hal;

    @Resource
    public OperatingSystem os;

    @Resource
    private SystemHardwareInfo systemHardwareInfo;

    @GetMapping("md5")
    public ObjectResponse<String> getSystemMd5(){

        String boardSerialNumber = hal.getComputerSystem().getBaseboard().getSerialNumber();
        System.out.println("boardSerialNumber:" + boardSerialNumber);

        String identifier = hal.getProcessor().getIdentifier();
        System.out.println("CPU Identifier:" + identifier);

        String processorID = hal.getProcessor().getProcessorID();
        System.out.println("CPU ID:" + processorID);

        String path = SystemInfoController.class.getResource("/").getFile();
//        file:/home/sunnly/sunnly-modules-sercet-generator.jar!/BOOT-INF/classes!/
//        file:/E:/sunnly-modules-sercet-generator.jar!/BOOT-INF/classes!/
        System.out.println("path:" + path);
        System.out.println("getFamily:" + os.getFamily());

        String pf;
        path = path.replace("file:/", "");
        if (path.indexOf("/") == 0){
            path = path.substring(1);
        }
        switch (os.getFamily()) {
            case "Windows":
                pf = path.substring(0, path.indexOf("/") + 1).replace("\\","/");
                break;
            default:
                pf = "/";
        }

        System.out.println("pf:"+pf);
        String uuid = "";

//        String path = "G:/abc";

        FileSystem fileSystem = os.getFileSystem();

        for (OSFileStore fs : fileSystem.getFileStores()){

            System.out.println(fs.getMount()+"=======000========="+fs.getUUID());
            if (StringUtils.equals(pf, fs.getMount().replace("\\","/"))){
                uuid = fs.getUUID();
                break;
            }
        }

        HWDiskStore[] diskStores = hal.getDiskStores();
        System.out.println(diskStores);


//        for (HWDiskStore diskStores : hal.getDiskStores()) {
//            for (HWPartition partition : diskStores.getPartitions()) {
//                System.out.println(partition.getMountPoint() + "------------------"+partition.getUuid());
//
////                /boot
//                if (partition.getMountPoint().replace("\\","/").equals(pf.replace("\\","/"))) {
//                    uuid = partition.getUuid();
//                    System.out.println("=============================");
////                    System.out.println(partition.getMountPoint())
//                    System.out.println("-----------------------------");
//                    System.out.println("Disk UUID:" + uuid);
//                    System.out.println("=============================");
//                } else {
//                    System.out.println(partition.getMountPoint() + "===" + partition.getUuid());
//                }
//
//            }
//        }

        String s = boardSerialNumber + "|" + processorID + "|" + uuid;
        System.out.println(s);
        String md5 = Md5Utils.getMD5(s, "utf-8");

        return new ObjectResponse<String>().setData(md5);
    }

    @GetMapping("info")
    public ObjectResponse<SystemHardwareInfo> gg11(){

        try {
            systemHardwareInfo.copyTo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ObjectResponse<SystemHardwareInfo>().setData(systemHardwareInfo);
    }
}
