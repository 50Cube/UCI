package pl.lodz.p.it.uci.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import pl.lodz.p.it.uci.wsdl.GetTpUserInfo;
import pl.lodz.p.it.uci.wsdl.GetTpUserInfoResponse;

public class Client extends WebServiceGatewaySupport {

    public GetTpUserInfoResponse getResponse(String tgsid) {
        GetTpUserInfo request = new GetTpUserInfo();
        request.setTgsid(tgsid);
        return (GetTpUserInfoResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://pz.gov.pl/pz-services/tpUserInfo", request,
                new SoapActionCallback(""));
    }
}
