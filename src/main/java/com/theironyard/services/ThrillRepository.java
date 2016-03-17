package com.theironyard.services;

import com.theironyard.entities.Thrill;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by noellemachin on 3/17/16.
 */
public interface ThrillRepository extends CrudRepository<Thrill, Integer> {
}
