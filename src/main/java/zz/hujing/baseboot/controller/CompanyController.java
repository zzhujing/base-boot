package zz.hujing.baseboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import zz.hujing.baseboot.core.result.CommonResult;
import zz.hujing.baseboot.domain.Company;
import zz.hujing.baseboot.service.CompanyService;

/**
 * <p>
 * 公司表 前端控制器
 * </p>
 *
 * @author hujing
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public CommonResult<Void> add(@RequestBody Company company) {
        return companyService.save(company) ? CommonResult.success() : CommonResult.fail();
    }

}
