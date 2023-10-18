package com.project.pins.models.services;

import java.util.List;

import com.project.pins.entities.PinEntity;

public interface IPinService {

    public List<PinEntity> findAll();

    public PinEntity findById(Long id);

    public void save(PinEntity pin);

    public void deleteById(Long id);

    public PinEntity actualizaPinEntity(PinEntity pin);

    public void changeState(Long id);

}
