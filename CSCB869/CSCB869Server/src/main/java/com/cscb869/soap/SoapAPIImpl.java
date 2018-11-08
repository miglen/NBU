package com.cscb869.soap;

import javax.jws.WebService;
import com.cscb869.HelperMethods;

/**
 *
 * @author Miglen Evlogiev
 */
//Service Implementation Bean
@WebService(endpointInterface = "com.cscb869.soap.SoapAPI")
public class SoapAPIImpl implements SoapAPI {

    /**
     * This methods encapsulates getNumberOfWords 
     * @param input
     */
    @Override
    public int getNumberOfWords(String input) {
        return HelperMethods.getNumberOfWords(input);
    }

    /**
     * This methods encapsulates getNumberOfSymbols 
     * @param input
     */
    @Override
    public long getNumberOfSymbols(String input) {
        return HelperMethods.getNumberOfSymbols(input);
    }

    /**
     * This methods encapsulates invertString 
     * @param input
     */
    @Override
    public String invertString(String input) {
        return HelperMethods.invertString(input);
    }

    /**
    * This methods encapsulates findSubstr 
     * @param input
     * @param needle
     */
    @Override
    public int findSubstr(String input, String needle) {
        return HelperMethods.findSubstr(input, needle);
    }
}
