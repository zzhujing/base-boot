package zz.hujing.baseboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import zz.hujing.baseboot.core.dynamic.DynamicDS;
import zz.hujing.baseboot.core.dynamic.DynamicDataSourceContext;
import zz.hujing.baseboot.core.result.CommonResult;
import zz.hujing.baseboot.domain.Phone;
import zz.hujing.baseboot.service.PhoneService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hujing
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @PostMapping("/add")
    @DynamicDS(DynamicDataSourceContext.DataSourceType.SLAVER)
    protected CommonResult<Void> add(@RequestBody Phone phone) {
        return phoneService.save(phone) ? CommonResult.success() : CommonResult.fail();
    }
}
