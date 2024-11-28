package com.informes_predicacion.org.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.informes_predicacion.org.dtos.req.GetUsersQueryParamsDto;
import com.informes_predicacion.org.entities.User;
import com.informes_predicacion.org.util.StringUtils;

public interface IUserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u LEFT JOIN u.congregation c WHERE u.id = ?1 AND c.id = ?2 AND u.enabled = true")
  Optional<User> findByIdAndCongregationId(Long id, Long congregationId); 


  @Query("""
    SELECT u FROM User u LEFT JOIN u.congregation c WHERE c.id = :congregationId 
    AND (:completeName IS NULL OR LOWER(CONCAT(u.names, ' ', u.lastNames)) like %:completeName%)
    AND u.enabled = true
  """)
  Page<User> findAllByCongregationIdAndCompleteNameFilter(@Param("congregationId") Long congregationId, @Param("completeName") String completeName, Pageable pageable);

  default Page<User> findAllByCongregationId(Long congregationId, GetUsersQueryParamsDto queryParams) {
    String completeName = StringUtils.optionalString(queryParams.getCompleteNameFilter());
    return findAllByCongregationIdAndCompleteNameFilter(congregationId, completeName, queryParams.toPageable());
  }
}
