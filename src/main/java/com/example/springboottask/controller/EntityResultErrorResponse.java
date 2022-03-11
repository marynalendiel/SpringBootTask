package com.example.springboottask.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntityResultErrorResponse {
	private int status;
	private String message;
	private long timeStamp;
}