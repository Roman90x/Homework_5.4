package dao;

import model.City;

import java.util.List;

public interface CityDao  {
    Integer create(City city);
    City  findById(Integer id);
    List<City> findAll();
    void update(City city);
    void delete(City city);
}
