package com.chengjf.shiro.demo.controllers;

import com.chengjf.shiro.demo.common.dao.StormtrooperDao;
import com.chengjf.shiro.demo.common.model.Stormtrooper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/troopers",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StormtrooperController {

    private final StormtrooperDao trooperDao;

    @Autowired
    public StormtrooperController(StormtrooperDao trooperDao) {
        this.trooperDao = trooperDao;
    }

    @GetMapping()
    @RequiresPermissions("troopers:read")
    public Collection<Stormtrooper> listTroopers() {
        return trooperDao.listStormtroopers();
    }

    @GetMapping(path = "/{id}")
    @RequiresPermissions("troopers:read")
    public Stormtrooper getTrooper(@PathVariable("id") String id) throws NotFoundException {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException(id);
        }
        return stormtrooper;
    }

    @PostMapping()
    @RequiresPermissions("troopers:create")
    public Stormtrooper createTrooper(@RequestBody Stormtrooper trooper) {

        return trooperDao.addStormtrooper(trooper);
    }

    @PostMapping(path = "/{id}")
    @RequiresPermissions("troopers:update")
    public Stormtrooper updateTrooper(@PathVariable("id") String id, @RequestBody Stormtrooper updatedTrooper) throws NotFoundException {

        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequiresPermissions("troopers:delete")
    public void deleteTrooper(@PathVariable("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }


}
