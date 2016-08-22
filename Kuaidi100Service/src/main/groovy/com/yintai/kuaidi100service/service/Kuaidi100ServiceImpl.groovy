package com.yintai.kuaidi100service.service
import com.yintai.kuaidi100service.common.DataManage
import com.yintai.kuaidi100service.model.ResultEntity
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
/**
 * Created by PangQian on 2016/8/8.
 */
@Service
@Component
@Slf4j
@CompileStatic
class Kuaidi100ServiceImpl implements Kuaidi100Service{

    @Autowired
    DataManage dm

    @Override
    void postOrder() {
        log.info("Begin a postOrder;")
        def orderlist = dm.getOrderList()

        for(orderinfo in orderlist){
             ResultEntity retinfo = dm.postData(orderinfo)
             dm.backTrackingByNumberOver(retinfo)
         }
    }
}
