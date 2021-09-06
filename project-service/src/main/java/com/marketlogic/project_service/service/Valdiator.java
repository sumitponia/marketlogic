package com.marketlogic.project_service.service;

import java.util.function.Predicate;

import com.marketlogic.project_service.exception.BusinessException;

public final class Valdiator {

	public static <T> void validate(Predicate<T> validator, T content, String message) throws BusinessException {
		if (!validator.test(content)) {
			throw new BusinessException(message);
		}
	}
}
