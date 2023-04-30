package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Role;
import com.example.easydonatemaster.repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    @Autowired
    IRoleRepository roleRepository;

    public Role saveNewRole(Role role)
    {
        return this.roleRepository.save(role);
    }

    public List<Role> getAllRoles()
    {
        return this.roleRepository.findAll();
    }



}
