package com.ma.rest.webservices.restfulwebservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering/static")
	public SomeBean retriveSomeBean() {
		return new SomeBean("Value1", "Value2", "Value3");
	}

	@GetMapping("/filtering/dynamic")
	public MappingJacksonValue retriveOtherBean() {
		OtherBean otherBean = new OtherBean("Value1", "Value2", "Value3");
		
		MappingJacksonValue mapping = new MappingJacksonValue(otherBean);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");

		FilterProvider filters = new SimpleFilterProvider().addFilter("OtherBeanFilter", filter);

		mapping.setFilters(filters);
		
		return mapping;
	}
}
