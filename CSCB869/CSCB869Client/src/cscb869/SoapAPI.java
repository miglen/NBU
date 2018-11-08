package cscb869;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author Miglen Evlogiev
 */
//Service Endpoint Interface
@WebService(name = "SoapAPI")
@SOAPBinding(style = Style.RPC)
public interface SoapAPI {

    /**
     *
     * @param input
     */
    @WebMethod
    int getNumberOfWords(String input);

    /**
     *
     * @param input
     */
    @WebMethod
    long getNumberOfSymbols(String input);

    /**
     *
     * @param input
     */
    @WebMethod
    String invertString(String input);

    /**
     *
     * @param input
     * @param needle
     */
    @WebMethod
    int findSubstr(String input, String needle);
}
