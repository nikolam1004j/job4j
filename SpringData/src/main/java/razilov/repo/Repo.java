package razilov.repo;

import org.springframework.data.repository.CrudRepository;
import razilov.models.Car;

public interface Repo extends CrudRepository<Car, Integer> {
    Car findById(int id);
}
