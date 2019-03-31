package razilov.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import razilov.models.Car;
import razilov.repo.Repo;

@Component
public class DatabaseLogic {
    private Repo repo;

    @Autowired
    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    public Car findById(int id) {
        return repo.findById(id);
    }
}
