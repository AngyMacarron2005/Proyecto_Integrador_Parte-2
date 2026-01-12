package ec.edu.ups.bussines;

import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.models.UserModel;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class UserBusiness {

    @Inject
    private UserDAO userDAO;

    @PostConstruct
    public void init() {
        UserModel u = new UserModel();
        u.setEmail("demo@correo.com");
        u.setDisplayName("Usuario Demo");
        u.setRole("USER");

        userDAO.insert(u);
        System.out.println("Usuario creado");
    }
}
