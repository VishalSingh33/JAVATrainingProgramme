package com.airlines.british.entites;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo implements Serializable {

    private Integer id;
    private Boolean completed;
    private String Title;
    private Integer userId;
    
}
