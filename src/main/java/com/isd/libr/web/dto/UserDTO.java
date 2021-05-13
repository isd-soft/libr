package com.isd.libr.web.dto;

import com.isd.libr.web.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface UserDTO extends CrudRepository<Person, Long> {

}
