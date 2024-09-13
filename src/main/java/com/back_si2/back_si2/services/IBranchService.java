package com.back_si2.back_si2.services;

import java.util.List;

import com.back_si2.back_si2.entities.Branch;

public interface IBranchService {
    Branch findById(Long id);

    List<Branch> findAll();

    void save(Branch branch);

    void deleteById(Long id);
}
