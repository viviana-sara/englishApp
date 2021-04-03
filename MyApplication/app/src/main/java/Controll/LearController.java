package Controll;

import java.util.List;
import java.util.Optional;

import aplicatie.Copil;
import aplicatie.Rezultat;
import aplicatie.User;

public interface LearController {
    List<User> getAllUsers();
    List<Rezultat> getAllResults();
    List<Copil> getAllChildren();

    User getByIdUser(int id);
    Copil getByIdCopil(int id, String column);
    Rezultat getByIdRezultat(int id, String column);

    void addUser(User user);
    void addCopil(Copil copil);
    void addRezultat(Rezultat rezultat);

    String getMaxId(String tableName);

}
