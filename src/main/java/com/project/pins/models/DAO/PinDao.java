package com.project.pins.models.DAO;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.pins.entities.PinEntity;

import jakarta.transaction.Transactional;

public interface PinDao extends CrudRepository<PinEntity, Long> {


    @Transactional
    @Modifying
    @Query("UPDATE PinEntity SET estadoPin = false WHERE idPin = ?1")
    public void changeState(Long idPin);

}
