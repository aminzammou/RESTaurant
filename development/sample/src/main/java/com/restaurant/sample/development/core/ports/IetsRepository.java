package com.restaurant.sample.development.core.ports;

import com.restaurant.sample.development.core.ports.datatransfer.IetsDTO;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface IetsRepository extends Repository<IetsDTO, Long> {

}
