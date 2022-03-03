
/**
 *  > Section 11: Deserialization 
 * 64. Strategies in Parsing Complex nested
 *  > Refer section11 for json saved in personal document
 *  > To generate setter and getters shortcut keys are alt + shift + s
 */
package com.volvo.pojo.classesDeserialization;

import java.util.List;

/**
 * nested json class (main class GetCourses) 
 * To generate setter and getters shortcut keys are alt + shift + s
 */
public class Courses {

    private List<WebAutomation> webAutomation;
    private List<Api> api;
    private List<Mobile> mobile;

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }

    public List<Api> getApi() {
        return api;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }

    public List<Mobile> getMobile() {
        return mobile;
    }

}
