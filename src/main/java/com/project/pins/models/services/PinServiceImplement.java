package com.project.pins.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.pins.entities.PinEntity;
import com.project.pins.models.DAO.PinDao;

@Service
public class PinServiceImplement implements IPinService {

    @Autowired
    private PinDao pinDao;

    @Override
    public List<PinEntity> findAll() {
        return (List<PinEntity>) pinDao.findAll();
    }

    @Override
    public PinEntity findById(Long id) {
        return pinDao.findById(id).orElse(null);
    }

    @Override
    public void save(PinEntity pin) {
        pinDao.save(pin);
    }

    @Override
    public void deleteById(Long id) {
        pinDao.deleteById(id);
    }

    @Override
    public PinEntity actualizaPinEntity(PinEntity pin) {
        return pinDao.save(pin);
    }

    @Override
    public void changeState(Long id) {
        pinDao.changeState(id);
    }

}
