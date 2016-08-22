package com.yintai.kuaidi100service.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by PangQian on 2016/8/13.
 */
class OrderList {
    @JsonProperty("GetPushKuaiDi100TrackingNumbersResult")
    List<OrderEntity> orderList
}
