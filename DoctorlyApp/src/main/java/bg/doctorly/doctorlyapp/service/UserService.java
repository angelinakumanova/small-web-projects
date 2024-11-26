package bg.doctorly.doctorlyapp.service;

import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;

public interface UserService extends BaseService {

    boolean validateRegisterModel(UserRegisterModel userRegisterModel);

    void registerUser(UserRegisterModel userRegisterModel);
}
