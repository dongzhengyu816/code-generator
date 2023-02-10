package xdpp.example.templatetest;

import lombok.extern.slf4j.Slf4j;
import xdpp.entity.MessageConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import xdpp.protocol.b2a.decode.BCNAV11Decode;
//import xdpp.protocol.b2a.entity.BCNAV11Entity;
import xdpp.protocol.common.error.MsgFieldRangeException;

import java.util.Map;

@SpringBootTest
@Slf4j
class TemplateTestApplicationTests {
    @Autowired
    private MessageConstruct messageConstruct;

    @Test
    void contextLoads() {
        System.out.println(messageConstruct);
    }

    /***
     * 测试反射
     */
    @Test
    void testReflect(){

    }

    @Test
    void testDecode() throws IllegalAccessException {
//        byte[] msg = new byte[]{0b01101011,0b01001010,0b00100111};
//        BCNAV11Entity entity = BCNAV11Decode.getEntity(msg);
//        System.out.println(entity);
//        try {
//            Map<String, Double> doubleMap = BCNAV11Decode.getRealNumberMap(entity);
//            System.out.println(doubleMap);
//        }catch (MsgFieldRangeException e){
//            System.out.println(e.getErrCode());
//            System.out.println(e.getMessage());
//            System.out.println(e.getAppDataInfo());
//        }
    }

}
