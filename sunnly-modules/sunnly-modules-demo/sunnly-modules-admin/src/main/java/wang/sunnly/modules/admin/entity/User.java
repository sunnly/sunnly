package wang.sunnly.modules.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User
 * 用户实体类
 *
 * @author Sunnly
 * @since 2020/12/3
 */
@Table(name = "base_user")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "sex")
    private int sex;
}
