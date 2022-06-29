

# I.单测 单元测试



##  II.老版本controller单测
package com.xxxxxx.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxxxxx.gaincust.utils.AESUtil;
import com.xxxxxx.gaincust.utils.JsonUtils;
import com.xxxxxx.user.entity.LiveStreamingImg;
import com.xxxxxx.user.service.LiveMaterialsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
*
*/
@Slf4j
@RunWith(PowerMockRunner.class)
public class LiveMaterialsControllerTest {

    protected MockMvc mockMvc;

    @InjectMocks
    private LiveMaterialsController controller;

    @Mock
    private LiveMaterialsService liveMaterialsService;
    /**
     * 对比mock数据用
     */
    private static Integer responseDataId=11111;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        List<LiveStreamingImg> mockList=new ArrayList<>();
        LiveStreamingImg bean=new LiveStreamingImg();
        bean.setId(responseDataId);
        bean.setActivityId(23123);
        mockList.add(bean);
        when(liveMaterialsService.getLiveStreamingAdvertInfoList(anyInt(), anyInt(), anyString(), anyString(), anyInt(), anyInt(),anyString())).thenReturn(mockList);

       // when(liveMaterialsService.getLiveStreamingAdvertInfoList(anyInt(), anyInt(), anyString(), anyString(), anyInt(), anyInt(),anyString())).thenReturn(null);
    }


    @Test
    public void testGetAdvertInfoList() throws Exception {
        try {

            //正常可返回的流程
            String json = "{\"advertId\":\"123455\",\"activityId\":\"123456\",\"userId\":\"12131354\"}";
            MockHttpSession mockHttpSession = new MockHttpSession();
            mockHttpSession.setAttribute("", "");
            log.info(" request :{}", json);
            String resultJson = mockMvc.perform(post("/liveMaterial/getAdvertInfoList").contentType(MediaType.APPLICATION_JSON_UTF8).content(json).session(mockHttpSession)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                    .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
            log.info(" 返回的resultJson = {}", resultJson);

            JSONObject jsonObject = JSONObject.parseObject(resultJson);
            JSONArray ja=  jsonObject.getJSONArray("data");
            List<LiveStreamingImg> userList = JSON.parseArray(ja.toJSONString(),LiveStreamingImg.class);
            log.info(" 返回的 LiveStreamingImg index 0 = {}", JsonUtils.toJson(userList.get(0)));
            assertTrue(responseDataId.equals(userList.get(0).getId()));


         JSONObject jsonObject = JSONObject.parseObject(resultJson);
            JSONObject ja = jsonObject.getJSONObject("data");
            ProductNotice pn = JSON.parseObject(ja.toJSONString(), ProductNotice.class);



            //用户ID为空的情况
            json = "{\"accountName\":\"啊啊\",\"activityId\":\"xxxx\"}";
             resultJson = mockMvc.perform(post("/liveMaterial/getAdvertInfoList").contentType(MediaType.APPLICATION_JSON_UTF8).content(json).session(mockHttpSession)
                    .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                    .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
            log.info(" 返回的resultJson 1 = {}", resultJson);

            assertTrue(resultJson.indexOf("A10003")!=-1);

        } catch (Exception e) {
            log.error("testGetAdvertInfoList单测异常", e);
        }
    }


}










##  II.spring 单测 依赖环境


package com.xxxxxx.app.yyyy.baseservice.controllers.thirdcenter;

import com.xxxxxx.app.yyyy.baseservice.BaseServiceApplication;
import com.xxxxxx.app.yyyy.baseservice.api.thirdcenter.bean.YaoZhenKaGetDetailReq;
import com.xxxxxx.app.yyyy.baseservice.service.thirdcenter.enums.HeXiangEnum;
import com.xxxxxx.app.yyyy.common.model.DataModel;
import com.xxxxxx.app.yyyy.common.model.GatewayBaseRequestParam;
import com.xxxxxx.app.yyyy.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest(classes = BaseServiceApplication.class)
public class YaoZhenKaControllerTest {
@Autowired
private YaoZhenKaController controller;

    @Test
    void getApplicationDetail() {
        try {
            assertNotNull("功能测试非单元测试");
            GatewayBaseRequestParam req = new GatewayBaseRequestParam();
            req.setFuid(300051255l);
            YaoZhenKaGetDetailReq bean = new YaoZhenKaGetDetailReq();
            bean.setProductId(HeXiangEnum.YAO_ZHEN_KA.getName());
            DataModel<Map> map = controller.getApplicationDetail(req, bean, null);
            log.info(" 返回 :{} ", JsonUtils.beanToJson(map));
        } catch (Exception e) {
            log.error("", e);
        }
    }

}






##  II.POM


   <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-core</artifactId>
            <version>2.0.0-RC.4</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-module-junit4</artifactId>
            <version>2.0.0-beta.5</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.powermock</groupId>
            <artifactId>powermock-api-mockito2</artifactId>
            <version>2.0.0-beta.5</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.2.9</version>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-inline</artifactId>
            <version>2.15.0</version>
        </dependency>



-------------------------------------------------------------------------



		<dependency>
			<groupId>org.powermock</groupId>
			<artifactId>powermock-core</artifactId>
			<version>1.6.5</version>
		</dependency>
		<dependency>
			<groupId>org.powermock</groupId>
			<artifactId>powermock-api-mockito</artifactId>
			<version>1.6.5</version>
		</dependency>
		<dependency>
			<groupId>org.powermock</groupId>
			<artifactId>powermock-module-junit4</artifactId>
			<version>1.6.5</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>5.1.11.RELEASE</version>
			<scope>test</scope>
		</dependency>




