package bg.doctorly.doctorlyapp.service.entityService;

import bg.doctorly.doctorlyapp.data.entites.User;
import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;

import java.util.Optional;

public interface UserService extends BaseService {

    boolean validateRegisterModel(UserRegisterModel userRegisterModel);

    void registerUser(UserRegisterModel userRegisterModel);

    boolean findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);
}
