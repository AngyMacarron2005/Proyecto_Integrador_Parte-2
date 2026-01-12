package ec.edu.ups.dao;

import ec.edu.ups.models.NotificationModel;
import jakarta.ejb.Stateless;

@Stateless
public class NotificationDAO extends GenericDAO<NotificationModel> {

    public NotificationDAO() {
        super(NotificationModel.class);
    }
}
