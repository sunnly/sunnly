package wang.sunnly.modules.sqlite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.modules.sqlite.entity.Company;
import wang.sunnly.modules.sqlite.mapper.CompanyMapper;

import java.util.List;

/**
 * CompanyController
 *
 * @author Sunnly
 * @since 2020/12/6 0006
 */
@RestController
@RequestMapping("/com")
public class CompanyController {
    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping("/get/{id}")
    public Company get(@PathVariable("id") Integer id) {
        return companyMapper.findById(id);
    }

    @RequestMapping("/list")
    public List<Company> list() {
        return companyMapper.findList();
    }

    @RequestMapping("/listBy")
    public List<Company> listBy(Company company) {
        return companyMapper.findListByCondition(company);
    }

    @RequestMapping("/add")
    public Integer add(@RequestBody(required = false) Company company, Company company1) {
        if (company1 != null) {
            companyMapper.insert(company1);
        }
        return companyMapper.insert(company);
    }

    @RequestMapping("/modify")
    public Integer modify(Company company) {
        return companyMapper.update(company);
    }

    @RequestMapping("/delete/{id}")
    public Integer delete(@PathVariable("id") Integer id) {
        return companyMapper.delete(id);
    }
}
