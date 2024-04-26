package com.notification.service.b2bProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("unused")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListingRequest {
	@NotNull(message = "Please provide page number")
	@Range(min = 0, message = "Please provide valid page number")
	private Integer page;

}