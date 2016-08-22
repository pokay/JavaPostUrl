package com.yintai.kuaidi100service.common
import com.fasterxml.jackson.databind.ObjectMapper
import com.yintai.kuaidi100service.model.OrderEntity
import com.yintai.kuaidi100service.model.OrderList
import com.yintai.kuaidi100service.model.ResultEntity
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import groovy.util.logging.Slf4j
/**
 * Created by PangQian on 2016/8/12.
 */
@CompileStatic
@Slf4j
@Component
class DataManage {

    @Autowired
    ObjectMapper mapper

    @Value('${spring.erpOrderServiceRUrl}')
     String erpOrderServiceRUrl

    @Value('${spring.erpKuaidi100BackUrl}')
    String erpKuaidi100BackUrl

    @Value('${spring.erpKuaidi100PostUrl}')
    String erpKuaidi100PostUrl

    @Value('${spring.erpOrderServiceWUrl}')
    String erpOrderServiceWUrl

    @Value('${spring.erpKuaidi100PostKey}')
    String erpKuaidi100PostKey

    /*
    * 通过接口获取 待传入的
    * */
    List<OrderEntity> getOrderList()    {
        String retInfo = HttpRequest.postUrl(erpOrderServiceRUrl,"")
        return mapper.readValue(retInfo, OrderList.class).orderList
    }

    /*
    * 订阅到快递 100
    * */
    ResultEntity postData(OrderEntity orderInfo){
        ResultEntity resultInfo = new ResultEntity()
        resultInfo.trackingNumber = orderInfo.trackingNumber
        resultInfo.expressCompanyCode = orderInfo.expressCompanyCode
        TaskRequest req = new TaskRequest()
        req.setCompany(orderInfo.expressCompanyCode)
        req.setFrom("")
        req.setTo(orderInfo.province+orderInfo.city+orderInfo.area)
        req.setNumber(orderInfo.trackingNumber);
        req.getParameters().put("callbackurl", erpKuaidi100BackUrl);
        req.setKey(erpKuaidi100PostKey);

        HashMap<String, String> p = new HashMap<String, String>();
        p.put("schema", "json");
        p.put("param", JacksonHelper.toJSON(req));
        try {
            String ret = HttpRequest.postData(erpKuaidi100PostUrl, p, "UTF-8");
            TaskResponse resp = mapper.readValue(ret, TaskResponse.class);
            if (resp.getResult() == true) {
                resultInfo.operation = true
            } else {
                resultInfo.operation = false
                resultInfo.failReason = resp.message
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.failReason = e.message
            resultInfo.operation =false
        }
       return  resultInfo
    }

    /*
    * 日志回写
    * */
    void backTrackingByNumberOver(ResultEntity result){
        String resultStr = JacksonHelper.toJSON(result)
        def ret = HttpRequest.postUrl(erpOrderServiceWUrl,resultStr)
        if(!ret as boolean){
            log.debug("日志回写失败;msg=." + resultStr)
        }
    }
}
