package zz.hujing.baseboot.service.impl;

import zz.hujing.baseboot.domain.Company;
import zz.hujing.baseboot.mapper.CompanyMapper;
import zz.hujing.baseboot.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司表 服务实现类
 * </p>
 *
 * @author hujing
 * @since 2020-04-06
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
