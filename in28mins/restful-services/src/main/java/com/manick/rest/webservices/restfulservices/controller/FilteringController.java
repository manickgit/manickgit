package com.manick.rest.webservices.restfulservices.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.manick.rest.webservices.restfulservices.dto.DynamicFilterBeanExample;
import com.manick.rest.webservices.restfulservices.dto.FilterBeanExample;

@RestController
public class FilteringController {

	@GetMapping(path = "/static-filter")
	public FilterBeanExample getStaticFieldsFiltered() {
		return new FilterBeanExample("manick", "qa02test", LocalDateTime.now(), 39, true);
	}

	@GetMapping(path = "/static-filter-list")
	public List<FilterBeanExample> getStaticFieldsFilteredList() {

		List<FilterBeanExample> list = new ArrayList<>();
		list.add(new FilterBeanExample("manick", "qa02test", LocalDateTime.now(), 39, true));
		list.add(new FilterBeanExample("raj", "p@s$w0rd", LocalDateTime.now(), 41, false));

		return list;
	}

	@GetMapping(path = "/dynamic-filter")
	public MappingJacksonValue getDynamicFieldsFiltered() {

		DynamicFilterBeanExample beanExample = new DynamicFilterBeanExample("manick", "qa02test", LocalDateTime.now(), 39, true);
		MappingJacksonValue mappJackVal = new MappingJacksonValue(beanExample);
		
		// The "age" field will be filtered since it is JsonIgnore in the bean level
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userName","age","birth_date","active");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("MyBeanFilter", filter);
		mappJackVal.setFilters(filterProvider);
		return mappJackVal;
	}
	
	@GetMapping(path = "/dynamic-filter-list")
	public MappingJacksonValue getDynamicFieldsFilteredList() {

		
		List<DynamicFilterBeanExample> list = new ArrayList<>();
		list.add(new DynamicFilterBeanExample("manick", "qa02test", LocalDateTime.now(), 39, true));
		list.add(new DynamicFilterBeanExample("raj", "p@s$w0rd", LocalDateTime.now(), 41, false));
		
		MappingJacksonValue mappJackVal = new MappingJacksonValue(list);
		
		// The "age" field will be filtered since it is JsonIgnore in the bean level
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userName","age","birth_date","active");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("MyBeanFilter", filter);
		mappJackVal.setFilters(filterProvider);
		return mappJackVal;
	}
}
