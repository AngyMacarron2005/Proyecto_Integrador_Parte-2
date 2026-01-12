package ec.edu.ups.dao;

import ec.edu.ups.models.ProgrammerProfileModel;
import jakarta.ejb.Stateless;

@Stateless
public class ProgrammerProfileDAO extends GenericDAO<ProgrammerProfileModel> {

    public ProgrammerProfileDAO() {
        super(ProgrammerProfileModel.class);
    }
}
