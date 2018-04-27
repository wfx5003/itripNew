/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itrip;

/**
 *
 * @author joshu
 */

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CollectionRepository extends CrudRepository<CollectionModel, Integer> {
    public List<CollectionModel> findByName(String name);
    public List<CollectionModel> findAll();
    public List<CollectionModel> findByOwner(Integer id);

}
