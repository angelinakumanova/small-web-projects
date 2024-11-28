package bg.doctorly.doctorlyapp.service.entityService;

import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;

public interface UserService extends BaseService {

    boolean validateRegisterModel(UserRegisterModel userRegisterModel);

    void registerUser(UserRegisterModel userRegisterModel);
}
