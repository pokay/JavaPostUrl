package com.yintai.kuaidi100service.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by PangQian on 2016/8/12.
 */
class OrderEntity {
    @JsonProperty("Area")
     String area

    @JsonProperty("City")
     String city

    @JsonProperty("Province")
     String province

    @JsonProperty("TrackingNumber")
     String trackingNumber

    @JsonProperty("ExpressCompanyCode")
     String expressCompanyCode
}
