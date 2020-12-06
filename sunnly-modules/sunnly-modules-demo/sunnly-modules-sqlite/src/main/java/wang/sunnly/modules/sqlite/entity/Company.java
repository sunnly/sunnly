package wang.sunnly.modules.sqlite.entity;

import org.apache.ibatis.type.Alias;

/**
 * Company
 *
 * @author Sunnly
 * @since 2020/12/6 0006
 */
@Alias("company_alias")
public class Company {
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private Double salary;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
