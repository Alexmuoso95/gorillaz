package com.gorillaz.core.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gorillaz.core.enums.ExpandEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiHelper {

	public static List<ExpandEnum> buildExpandEnumCollection(String expand){
		String expandNotNull = Optional.ofNullable(expand).orElse("");
		log.info("Controller Get Client  : expand  {}" , expand);
		return Arrays.stream(expandNotNull.split(","))
				.map(e -> ExpandEnum.find(e.trim(),() -> ExpandEnum.NOEXPAND))
				.collect(Collectors.toList());
	}
}
