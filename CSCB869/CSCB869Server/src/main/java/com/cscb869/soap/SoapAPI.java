package com.cscb869.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 *
 * @author Miglen Evlogiev
 */
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface SoapAPI {

    /**
     * 
     * @param input
     * @return
     */
    @WebMethod
    int getNumberOfWords(String input);

    /**
     *
     * @param input
     * @return
     */
    @WebMethod
    long getNumberOfSymbols(String input);

    /**
     *
     * @param input
     * @return
     */
    @WebMethod
    String invertString(String input);

    /**
     *
     * @param input
     * @param needle
     * @return
     */
    @WebMethod
    int findSubstr(String input, String needle);
}
