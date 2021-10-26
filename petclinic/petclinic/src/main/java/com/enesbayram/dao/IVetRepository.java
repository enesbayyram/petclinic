package com.enesbayram.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.enesbayram.model.Vet;

public interface IVetRepository extends JpaRepository<Vet,Long>{

}
