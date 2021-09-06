package com.marketlogic.onesearch_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.marketlogic.onesearch_service.beans.domain.Content;

public interface SearchRepository extends PagingAndSortingRepository<Content, Long> {

	@Query(value = "from content where snapshot like %?1%", nativeQuery = false)
	List<Content> findByContent(String query);

}