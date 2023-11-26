package com.praveen.springproject.spring6restmvc.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class Customer {

    private UUID id;
    private String name;
    private Date createdDate;
    private String comments;


}
