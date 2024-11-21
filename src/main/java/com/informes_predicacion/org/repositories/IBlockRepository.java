package com.informes_predicacion.org.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.informes_predicacion.org.dtos.res.BlockDto;
import com.informes_predicacion.org.entities.Block;


public interface IBlockRepository extends JpaRepository<Block, Long> {

    @Query("SELECT new com.informes_predicacion.org.dtos.res.BlockDto(b.id, b.name, b.description, b.enabled, b.territory.id, b.territory.name) FROM Block b WHERE b.territory.congregation.id = :congregationId")
    Set<BlockDto> findAllByCongregationId(Long congregationId);

    @Query("SELECT COUNT(b.id) = :#{#ids.size} " +
       "FROM Block b " +
       "WHERE b.id IN :ids AND b.territory.congregation.id = :congregationId")
    Boolean existsAllBlocksByIdAndCongregationId(@Param("ids") Set<Long> ids, @Param("congregationId") Long congregationId);

}
