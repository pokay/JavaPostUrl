package com.yintai.kuaidi100service.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by PangQian on 2016/8/13.
 */
class ResultEntity  implements Serializable {
    @JsonProperty("TrackingNumber")
    String trackingNumber

    @JsonProperty("ExpressCompanyCode")
    String expressCompanyCode

    @JsonProperty("Operation")
    boolean operation

    @JsonProperty("FailReason")
    String failReason
}
