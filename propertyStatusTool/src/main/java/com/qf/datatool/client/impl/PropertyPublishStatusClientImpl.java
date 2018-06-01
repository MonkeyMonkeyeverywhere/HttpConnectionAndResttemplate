package com.qf.datatool.client.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qf.datatool.bean.UpdatePublishStatusResponse;
import com.qf.datatool.client.PropertyPublishStatusClient;
import com.qf.datatool.configuration.CfgProperties;
import com.qf.datatool.constants.PublishStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyPublishStatusClientImpl implements PropertyPublishStatusClient {
    private static final Log log = LogFactory.getLog(PropertyPublishStatusClientImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CfgProperties cfgProperties;

    @Override
    public int countProPublishProperty() {
        String companyId = "102240";
        String cookie = "loginMethod=empName; yifangwangtaianzongb_company21fea22b6|MoneyAfterDecimalIsWeakShow=1; gr_user_id=8074b724-2d2d-4698-83a3-700101698f2d; moniupdateperson=yifangwangtaianzongb_company21fea22b6_9999; yifangwangyibinceshi_companycd12312e3|MoneyAfterDecimalIsWeakShow=1; qingdaokeweibudongch_company8d9d1aae2|MoneyAfterDecimalIsWeakShow=0; frontcache_apollotov10=0ndefined; domain_v10=gz.qiaofangyun.com; srv-id=c876059a1d15fbf13ccb4efc25de3f04; TY_SESSION_ID=2b700fde-33a0-40a8-af04-b9fcf7ce78d8; Hm_lvt_9a2d0d442569b6f5c27fb2283feec8a8=1525748837,1526386784,1526910154; companyUuid=yifangwangyibinceshi_companycd12312e3; updateperson=yifangwangyibinceshi_companycd12312e3_99999999999999999999999999999999; qid=EN8kBe%2BUFuiCNAEx5xxv5S5ppUGbCEoGgSEh1Cw0Wg1MqOz8UjgLeO%2B%2FFohI5c0GPCzIZE0XUcOt%0A%2B%2B0qGBZP8cAsut%2FmaGUL%2FRKxVxGlcNUDwEi%2FlPz0D3GomVCrob9PECZJ7CAyLlIwdELBEobgFQk8%0AtGXsq74lUi4%2F%2FBhM2Lkr3%2FCmD%2F1hC%2B8BtwZHIZ5u; qf-auth-token=yifangwangyibinceshi_companycd12312e3--item--2--item--99999999999999999999999999999999--item--37974a30342b42fbb4f3102bbde09db8; 9ae6bee472427901_gr_last_sent_sid_with_cs1=16ae46ec-8801-48c7-87f5-600fa6001ccf; 9ae6bee472427901_gr_last_sent_cs1=99999999999999999999999999999999; 9ae6bee472427901_gr_session_id=16ae46ec-8801-48c7-87f5-600fa6001ccf_true; QFSID=5a42976b-9be8-45c8-99b5-095e09ffc401; 9ae6bee472427901_gr_cs1=99999999999999999999999999999999; Hm_lpvt_9a2d0d442569b6f5c27fb2283feec8a8=1526910934";
        try {
            String sql = "select count(publishPropertyUuid) as count from pro_publish_property where createdUserUuid='7777timer'";
            HttpURLConnection conn = (HttpURLConnection) new URL(
                    "http://gz.qiaofangyun.com/execute/sql.do?companyId=" + companyId + "&executeSql=" + URLEncoder.encode(sql, "UTF-8"))
                    .openConnection();
            conn.setRequestProperty("Accept", "application/json, text/plain, */*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Host", "gz.qiaofangyun.com");
            conn.setRequestProperty("Referer", "http://gz.qiaofangyun.com//index.html");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            conn.setRequestProperty("X-Tingyun-Id", "Q7tI9wrjrZM;r=931996803");
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String temp;
            String s = "";
            while ((temp = r.readLine()) != null) {
                s = temp;
            }
            log.info(s);
            JSONObject json = JSONObject.parseObject(s);
            JSONArray arr = json.getJSONArray("values");
            JSONObject o = (JSONObject) arr.get(0);
            return (int) o.get("count");
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> listProPublishProperty() {
        int count = countProPublishProperty();
        log.info("共有"+count+"条");
        int times = count % 20 == 0 ? count / 20 : count / 20 + 1;
        List<String> publishPropertyUuids = new ArrayList<>();
        String companyId = "102240";
        String cookie = "loginMethod=empName; yifangwangtaianzongb_company21fea22b6|MoneyAfterDecimalIsWeakShow=1; gr_user_id=8074b724-2d2d-4698-83a3-700101698f2d; moniupdateperson=yifangwangtaianzongb_company21fea22b6_9999; yifangwangyibinceshi_companycd12312e3|MoneyAfterDecimalIsWeakShow=1; qingdaokeweibudongch_company8d9d1aae2|MoneyAfterDecimalIsWeakShow=0; frontcache_apollotov10=0ndefined; domain_v10=gz.qiaofangyun.com; srv-id=c876059a1d15fbf13ccb4efc25de3f04; TY_SESSION_ID=2b700fde-33a0-40a8-af04-b9fcf7ce78d8; Hm_lvt_9a2d0d442569b6f5c27fb2283feec8a8=1525748837,1526386784,1526910154; companyUuid=yifangwangyibinceshi_companycd12312e3; updateperson=yifangwangyibinceshi_companycd12312e3_99999999999999999999999999999999; qid=EN8kBe%2BUFuiCNAEx5xxv5S5ppUGbCEoGgSEh1Cw0Wg1MqOz8UjgLeO%2B%2FFohI5c0GPCzIZE0XUcOt%0A%2B%2B0qGBZP8cAsut%2FmaGUL%2FRKxVxGlcNUDwEi%2FlPz0D3GomVCrob9PECZJ7CAyLlIwdELBEobgFQk8%0AtGXsq74lUi4%2F%2FBhM2Lkr3%2FCmD%2F1hC%2B8BtwZHIZ5u; qf-auth-token=yifangwangyibinceshi_companycd12312e3--item--2--item--99999999999999999999999999999999--item--37974a30342b42fbb4f3102bbde09db8; 9ae6bee472427901_gr_last_sent_sid_with_cs1=16ae46ec-8801-48c7-87f5-600fa6001ccf; 9ae6bee472427901_gr_last_sent_cs1=99999999999999999999999999999999; 9ae6bee472427901_gr_session_id=16ae46ec-8801-48c7-87f5-600fa6001ccf_true; QFSID=5a42976b-9be8-45c8-99b5-095e09ffc401; 9ae6bee472427901_gr_cs1=99999999999999999999999999999999; Hm_lpvt_9a2d0d442569b6f5c27fb2283feec8a8=1526910934";

        for(int i = 0;i<times;i++){
            try {
                String sql = "select publishPropertyUuid from pro_publish_property where createdUserUuid='7777timer'";
                HttpURLConnection conn = (HttpURLConnection) new URL(
                        "http://gz.qiaofangyun.com/execute/sql.do?companyId=" + companyId + "&executeSql=" + URLEncoder.encode(sql, "UTF-8"))
                        .openConnection();
                conn.setRequestProperty("Accept", "application/json, text/plain, */*");
                conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
                conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                conn.setRequestProperty("Connection", "keep-alive");
                conn.setRequestProperty("Cookie", cookie);
                conn.setRequestProperty("Host", "gz.qiaofangyun.com");
                conn.setRequestProperty("Referer", "http://gz.qiaofangyun.com//index.html");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
                conn.setRequestProperty("X-Tingyun-Id", "Q7tI9wrjrZM;r=931996803");
                BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String temp;
                String s = "";
                while ((temp = r.readLine()) != null) {
                    s = temp;
                }
                log.info(s);
                JSONObject json = JSONObject.parseObject(s);
                JSONArray values = json.getJSONArray("values");
                for(int j = 0;j<values.size();j++){
                    JSONObject jsonObject = (JSONObject) values.get(j);
                    publishPropertyUuids.add((String) jsonObject.get("publishPropertyUuid"));
                }
                return  publishPropertyUuids;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public void offPublishStatus(String companyUuid,String publishPropertyUuid) {
        UpdatePublishStatusResponse response = null;
        try {
            HttpHeaders headers = getHeaders(companyUuid);
            Map<String, Object> body = new HashMap<>();
            body.put("publishPropertyUuid", publishPropertyUuid);
            body.put("publishStatus", "off");
            response = restTemplate.postForObject(cfgProperties.getUpdatePublishStatusUrl(), new HttpEntity<>(body, headers),
                    UpdatePublishStatusResponse.class);
            log.info("==========>updatePublishStatusUrl --> responseCode:"+ response.getResponseCode()+" responseMessage:"+ response.getResponseMessage());
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public int countAutoTakeoffProperty() {
        String companyId = "101602";
        String cookie = "loginMethod=empName; yifangwangtaianzongb_company21fea22b6|MoneyAfterDecimalIsWeakShow=1; gr_user_id=8074b724-2d2d-4698-83a3-700101698f2d; moniupdateperson=yifangwangtaianzongb_company21fea22b6_9999; yifangwangyibinceshi_companycd12312e3|MoneyAfterDecimalIsWeakShow=1; qingdaokeweibudongch_company8d9d1aae2|MoneyAfterDecimalIsWeakShow=0; frontcache_apollotov10=0ndefined; companyUuid=yifangwangtaianzongb_company21fea22b6; updateperson=yifangwangtaianzongb_company21fea22b6_9999; qid=EN8kBe%2BUFuiCNAEx5xxv5S5ppUGbCEoG2F1i9JR9CIhhn694wpDO5uUdIWKtimEZ1ZT8r4GVP4wt%0AtT4GfkahySIgWiPmsyYGiXmMabWiCBQP4YKbKuCQ9fUN6228odOiAY3bsMqmDcM4O1rTyUM%2FdA%3D%3D; qf-auth-token=yifangwangtaianzongb_company21fea22b6--item--2--item--9999--item--fd5a15f2fd014e699689b903b063fb6a; domain_v10=gz.qiaofangyun.com; 9ae6bee472427901_gr_last_sent_sid_with_cs1=5b9c2ca9-7282-4a3a-8d5c-1ff5654b7b1e; 9ae6bee472427901_gr_last_sent_cs1=9999; 9ae6bee472427901_gr_cs1=9999; 9ae6bee472427901_gr_session_id=5b9c2ca9-7282-4a3a-8d5c-1ff5654b7b1e_true; QFSID=d76cece0-a819-4d20-886e-d34dd7325e8a; srv-id=c876059a1d15fbf13ccb4efc25de3f04; TY_SESSION_ID=2b700fde-33a0-40a8-af04-b9fcf7ce78d8; Hm_lvt_9a2d0d442569b6f5c27fb2283feec8a8=1525748837,1526386784,1526910154; Hm_lpvt_9a2d0d442569b6f5c27fb2283feec8a8=1526910154";
        try {
            String sql = "select  count(publishPropertyUuid) as count  from pro_publish_property where propertyUuid in (SELECT\n" +
                    "a.propertyUuid\n" +
                    "FROM pro_property a\n" +
                    "LEFT JOIN pro_publish_property b on a.propertyUuid = b.propertyUuid\n" +
                    "LEFT JOIN org_employee e on a.employeeId1 = e.employeeId\n" +
                    "LEFT JOIN org_position f on e.positionId = f.positionId\n" +
                    "LEFT JOIN pro_manual_publish_status g on a.propertyUuid = g.propertyUuid\n" +
                    "where (b.publishStatus='release' or b.publishStatus='on')\n" +
                    "and (( g.publishStatus = '0' )\n" +
                    "    OR (a.privy=3 or (a.privy=1 and DATEDIFF( hour, a.createdTime ,GETDATE()) <=72) )\n" +
                    "    OR ( a.tradeStatus != '有效')\n" +
                    "    OR ( (DATEDIFF ( day , a.lastFollowDate,GETDATE() ) >30 or a.lastFollowDate is null ) AND (DATEDIFF( day , a.lastInspectionDate,GETDATE() ) >30 or a.lastInspectionDate is null ) )\n" +
                    "    OR (a.photoCount<5)\n" +
                    "    OR (a.propertyId NOT IN (select DISTINCT(y.propertyId )\n" +
                    "    from pro_propertyphoto x\n" +
                    "    JOIN pro_property y on x.businessId = y.propertyId\n" +
                    "    JOIN com_photocategory z on x.photoCategoryId = z.categoryId\n" +
                    "    where z.categoryName='fangxing' and x.deleted = 0))\n" +
                    "    OR (f.business!=1 AND f.shopMgr!=1)\n" +
                    "    OR a.deleted = 1))";
            HttpURLConnection conn = (HttpURLConnection) new URL(
                    "http://gz.qiaofangyun.com/execute/sql.do?companyId=" + companyId + "&executeSql=" + URLEncoder.encode(sql, "UTF-8"))
                    .openConnection();
            conn.setRequestProperty("Accept", "application/json, text/plain, */*");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Host", "gz.qiaofangyun.com");
            conn.setRequestProperty("Referer", "http://gz.qiaofangyun.com//index.html");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            conn.setRequestProperty("X-Tingyun-Id", "Q7tI9wrjrZM;r=931996803");
            BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String temp;
            String s = "";
            while ((temp = r.readLine()) != null) {
                s = temp;
            }
            log.info(s);
            JSONObject json = JSONObject.parseObject(s);
            JSONArray arr = json.getJSONArray("values");
            JSONObject o = (JSONObject) arr.get(0);
            return (int) o.get("count");
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> listAutoTakeoffProperty() {
        int count = countAutoTakeoffProperty();
        log.info("共有"+count+"条");
        int times = count % 20 == 0 ? count / 20 : count / 20 + 1;
        List<String> publishPropertyUuids = new ArrayList<>();
        String companyId = "101602";
        String cookie = "loginMethod=empName; yifangwangtaianzongb_company21fea22b6|MoneyAfterDecimalIsWeakShow=1; gr_user_id=8074b724-2d2d-4698-83a3-700101698f2d; moniupdateperson=yifangwangtaianzongb_company21fea22b6_9999; yifangwangyibinceshi_companycd12312e3|MoneyAfterDecimalIsWeakShow=1; qingdaokeweibudongch_company8d9d1aae2|MoneyAfterDecimalIsWeakShow=0; frontcache_apollotov10=0ndefined; companyUuid=yifangwangtaianzongb_company21fea22b6; updateperson=yifangwangtaianzongb_company21fea22b6_9999; qid=EN8kBe%2BUFuiCNAEx5xxv5S5ppUGbCEoG2F1i9JR9CIhhn694wpDO5uUdIWKtimEZ1ZT8r4GVP4wt%0AtT4GfkahySIgWiPmsyYGiXmMabWiCBQP4YKbKuCQ9fUN6228odOiAY3bsMqmDcM4O1rTyUM%2FdA%3D%3D; qf-auth-token=yifangwangtaianzongb_company21fea22b6--item--2--item--9999--item--fd5a15f2fd014e699689b903b063fb6a; domain_v10=gz.qiaofangyun.com; 9ae6bee472427901_gr_last_sent_sid_with_cs1=5b9c2ca9-7282-4a3a-8d5c-1ff5654b7b1e; 9ae6bee472427901_gr_last_sent_cs1=9999; 9ae6bee472427901_gr_cs1=9999; 9ae6bee472427901_gr_session_id=5b9c2ca9-7282-4a3a-8d5c-1ff5654b7b1e_true; QFSID=d76cece0-a819-4d20-886e-d34dd7325e8a; srv-id=c876059a1d15fbf13ccb4efc25de3f04; TY_SESSION_ID=2b700fde-33a0-40a8-af04-b9fcf7ce78d8; Hm_lvt_9a2d0d442569b6f5c27fb2283feec8a8=1525748837,1526386784,1526910154; Hm_lpvt_9a2d0d442569b6f5c27fb2283feec8a8=1526910154";

        for(int i = 0;i<times;i++){
            try {
                String sql = "select  publishPropertyUuid  from pro_publish_property where propertyUuid in (SELECT\n" +
                        "a.propertyUuid\n" +
                        "FROM pro_property a\n" +
                        "LEFT JOIN pro_publish_property b on a.propertyUuid = b.propertyUuid\n" +
                        "LEFT JOIN org_employee e on a.employeeId1 = e.employeeId\n" +
                        "LEFT JOIN org_position f on e.positionId = f.positionId\n" +
                        "LEFT JOIN pro_manual_publish_status g on a.propertyUuid = g.propertyUuid\n" +
                        "where (b.publishStatus='release' or b.publishStatus='on')\n" +
                        "and (( g.publishStatus = '0' )\n" +
                        "    OR (a.privy=3 or (a.privy=1 and DATEDIFF( hour, a.createdTime ,GETDATE()) <=72) )\n" +
                        "    OR ( a.tradeStatus != '有效')\n" +
                        "    OR ( (DATEDIFF ( day , a.lastFollowDate,GETDATE() ) >30 or a.lastFollowDate is null ) AND (DATEDIFF( day , a.lastInspectionDate,GETDATE() ) >30 or a.lastInspectionDate is null ) )\n" +
                        "    OR (a.photoCount<5)\n" +
                        "    OR (a.propertyId NOT IN (select DISTINCT(y.propertyId )\n" +
                        "    from pro_propertyphoto x\n" +
                        "    JOIN pro_property y on x.businessId = y.propertyId\n" +
                        "    JOIN com_photocategory z on x.photoCategoryId = z.categoryId\n" +
                        "    where z.categoryName='fangxing' and x.deleted = 0))\n" +
                        "    OR (f.business!=1 AND f.shopMgr!=1)\n" +
                        "    OR a.deleted = 1))";
                HttpURLConnection conn = (HttpURLConnection) new URL(
                        "http://gz.qiaofangyun.com/execute/sql.do?companyId=" + companyId + "&executeSql=" + URLEncoder.encode(sql, "UTF-8"))
                        .openConnection();
                conn.setRequestProperty("Accept", "application/json, text/plain, */*");
                conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
                conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
                conn.setRequestProperty("Connection", "keep-alive");
                conn.setRequestProperty("Cookie", cookie);
                conn.setRequestProperty("Host", "gz.qiaofangyun.com");
                conn.setRequestProperty("Referer", "http://gz.qiaofangyun.com//index.html");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
                conn.setRequestProperty("X-Tingyun-Id", "Q7tI9wrjrZM;r=931996803");
                BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String temp;
                String s = "";
                while ((temp = r.readLine()) != null) {
                    s = temp;
                }
                log.info(s);
                JSONObject json = JSONObject.parseObject(s);
                JSONArray values = json.getJSONArray("values");
                for(int j = 0;j<values.size();j++){
                    JSONObject jsonObject = (JSONObject) values.get(j);
                    publishPropertyUuids.add((String) jsonObject.get("publishPropertyUuid"));
                }
                return  publishPropertyUuids;
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private HttpHeaders getHeaders(String companyUuid) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("X-AUTH-APP-ID", cfgProperties.getAppid());
        headers.add("X-AUTH-KEY", cfgProperties.getAppkey());
        headers.add("companyUuid", companyUuid);
        return headers;
    }
}
