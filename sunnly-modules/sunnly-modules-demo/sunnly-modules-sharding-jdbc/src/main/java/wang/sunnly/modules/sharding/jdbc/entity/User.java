package wang.sunnly.modules.sharding.jdbc.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * User
 * 用户实体类
 *
 * @author Sunnly
 * @since 2020/12/3
 */
@Table(name = "t_user")
@Data
public class User<K extends Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private K id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;
}
