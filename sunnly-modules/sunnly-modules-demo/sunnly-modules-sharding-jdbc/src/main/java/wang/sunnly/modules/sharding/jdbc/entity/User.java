package wang.sunnly.modules.sharding.jdbc.entity;

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
@Table(name = "t_user_")
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
}
