package pl.lodz.p.it.uci.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import pl.lodz.p.it.uci.wsdl.VerifySignature;
import pl.lodz.p.it.uci.wsdl.VerifySignatureResponse;

public class Client extends WebServiceGatewaySupport {

    public VerifySignatureResponse verify(byte[] file) {
        VerifySignature request = new VerifySignature();
        request.setDoc(file);
        return (VerifySignatureResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://pz.gov.pl/pz-services/SignatureVerification", request,
                        new SoapActionCallback(""));
    }
}
