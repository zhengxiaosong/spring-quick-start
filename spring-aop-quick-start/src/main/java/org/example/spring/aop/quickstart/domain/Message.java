package org.example.spring.aop.quickstart.domain;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Song.Z
 */
public class Message implements Serializable {
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
