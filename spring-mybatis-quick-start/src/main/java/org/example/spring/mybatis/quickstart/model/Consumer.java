package org.example.spring.mybatis.quickstart.model;

import lombok.Data;

import java.util.Date;

/**
 * Description:
 *
 * @author Song.Z
 */
@Data
public class Consumer {
    private long id;
    private String nickname;
    private Date registerTime;
    private Date createdOn;
    private int createdBy;
    private Date updatedOn;
    private int updatedBy;
}
