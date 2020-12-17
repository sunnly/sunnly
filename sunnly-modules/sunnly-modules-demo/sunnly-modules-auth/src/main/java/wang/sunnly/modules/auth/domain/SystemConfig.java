package wang.sunnly.modules.auth.domain;

import javax.persistence.*;
import lombok.Data;
/**
 * SystemConfig
 * 系统配置
 *
 * @author Sunnly
 * @since 2020/12/8
 */
@Data
@Table(name = "auth_system_config")
public class SystemConfig {
    /**
     * 配置ID
     */
    @Id
    @Column(name = "sc_id")
    private Long scId;

    /**
     * 系统渠道
     */
    @Column(name = "sc_system_channel")
    private String scSystemChannel;

    /**
     * 是否开启用户名密码登录(0:否,1:是)
     */
    @Column(name = "sc_account")
    private String scAccount;

    /**
     * 用户名密码登录验证方式(0:不开启,1:验证码,2:短信,3:验证码+短信,4:邮箱,5:滑块验证)
     */
    @Column(name = "sc_account_validate")
    private String scAccountValidate;

    /**
     * 第三方登录(如微信、QQ等，多个用逗号隔开)
     */
    @Column(name = "sc_account_social")
    private String scAccountSocial;

    /**
     * 是否开启UKey登录
     */
    @Column(name = "sc_ukey")
    private String scUkey;

    /**
     * 是否UKey登录时输入用户名
     */
    @Column(name = "sc_ukey_username")
    private String scUkeyUsername;

    /**
     * 是否UKey登录时输入密码
     */
    @Column(name = "sc_ukey_password")
    private String scUkeyPassword;
}
