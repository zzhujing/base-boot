package zz.hujing.baseboot.service.impl;

import zz.hujing.baseboot.domain.Phone;
import zz.hujing.baseboot.mapper.PhoneMapper;
import zz.hujing.baseboot.service.PhoneService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hujing
 * @since 2020-04-06
 */
@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {

}
