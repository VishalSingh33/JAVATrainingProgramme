package com.notification.service.b2bProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
// @Getter
// @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListingResponse {

    private List<?> content;
    private int page;
}
