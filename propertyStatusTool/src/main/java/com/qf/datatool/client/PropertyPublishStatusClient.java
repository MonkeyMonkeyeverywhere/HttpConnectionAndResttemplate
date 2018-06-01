package com.qf.datatool.client;

import java.util.List;

public interface PropertyPublishStatusClient {

    int countProPublishProperty();

    List<String> listProPublishProperty();

    void offPublishStatus(String companyUuid,String publishPropertyUuid);

    /**
     * 待下架房源条数
     * @return
     */
    int countAutoTakeoffProperty();
    /**
     * 获取待下架房源列表
     * @return
     */
    List<String> listAutoTakeoffProperty();
}
