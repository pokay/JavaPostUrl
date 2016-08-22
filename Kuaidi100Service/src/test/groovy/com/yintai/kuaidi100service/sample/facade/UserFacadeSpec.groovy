package com.yintai.kuaidi100service.sample.facade
import com.fasterxml.jackson.databind.ObjectMapper
import com.yintai.kuaidi100service.Kuaidi100ServiceApplication
import org.mockserver.integration.ClientAndServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

@WebAppConfiguration
@ActiveProfiles(value = "test")
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = [Kuaidi100ServiceApplication])
class UserFacadeSpec extends Specification {

    @Autowired
    private ObjectMapper objectMapper

    private ClientAndServer mockServer
    private def baseUrl

    def setup() {
        mockServer = ClientAndServer.startClientAndServer(9998)
        baseUrl = "http://localhost:9998"
    }

    def cleanup() {
        mockServer.stop()
    }
/*
    void "测试 Kuaidi100Service.postData 的情况"() {
        //when:
            kuaidiFacade.postOrder()
       // then:
       //     thrown(Exception)
    }
*/
}
