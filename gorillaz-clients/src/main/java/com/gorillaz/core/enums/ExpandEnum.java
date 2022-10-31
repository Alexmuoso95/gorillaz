package com.gorillaz.core.enums;

import java.util.Arrays;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum ExpandEnum {

	NOEXPAND("empty"),
	CLIENT_ADDRESSES("client.addresses");
	
	private String value;
	
	ExpandEnum(String value ) { this.value = value ;}
	
	public static ExpandEnum find(String passedValue,Supplier<? extends ExpandEnum> defualtEnum) {
		log.info("ExpandEnum find expand option for {}" , passedValue);
		return Arrays.stream(ExpandEnum.values()).filter(e-> e.value.equalsIgnoreCase(passedValue)).findFirst().orElseGet(defualtEnum);
				
	}
}
