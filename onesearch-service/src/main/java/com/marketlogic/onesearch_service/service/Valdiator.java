package com.marketlogic.onesearch_service.service;

import java.util.function.Predicate;

import com.marketlogic.onesearch_service.exception.BusinessException;

public final class Valdiator {

	public static <T> void validate(Predicate<T> validator, T contentRequest, String message) throws BusinessException {
		if (!validator.test(contentRequest)) {
			throw new BusinessException(message);
		}
	}

}
