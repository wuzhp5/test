
package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import org.tempuri.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ncwsSoap", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface NcwsSoap {


    /**
     * 
     * @param content
     * @param type
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "UploadNCPlan", action = "http://tempuri.org/UploadNCPlan")
    @WebResult(name = "UploadNCPlanResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "UploadNCPlan", targetNamespace = "http://tempuri.org/", className = "org.tempuri.UploadNCPlan")
    @ResponseWrapper(localName = "UploadNCPlanResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.UploadNCPlanResponse")
    public String uploadNCPlan(
        @WebParam(name = "type", targetNamespace = "http://tempuri.org/")
        String type,
        @WebParam(name = "content", targetNamespace = "http://tempuri.org/")
        String content);

}
