package com.kodilla.Service_Cars.repository;

import com.kodilla.Service_Cars.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CarRepository extends CrudRepository<Car,Long> {

    Optional<Car> findById(long carId);

    @Override
    List<Car> findAll();

    @Override
    Car save(Car car);

    @Override
    void deleteById(Long carId);

    @Override
    long count();
}
