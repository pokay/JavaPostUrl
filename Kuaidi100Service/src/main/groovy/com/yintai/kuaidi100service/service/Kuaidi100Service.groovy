package com.yintai.kuaidi100service.service
/**
 * Created by PangQian on 2016/8/4.
 */

interface Kuaidi100Service {
    /*
    * 供应商操作出库的订单的物流信息订阅到快递100系统
    * 每半小时 执行一次
    * 订阅结果记录有日志
    * */
    void postOrder()
}

