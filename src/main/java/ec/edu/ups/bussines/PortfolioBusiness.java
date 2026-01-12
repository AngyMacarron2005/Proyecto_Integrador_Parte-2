package ec.edu.ups.bussines;

import ec.edu.ups.dao.PortfolioDAO;
import ec.edu.ups.models.PortfolioModel;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class PortfolioBusiness {

    @Inject
    private PortfolioDAO portfolioDAO;

    @PostConstruct
    public void init() {
        PortfolioModel p = new PortfolioModel();
        p.setTitle("Portafolio Inicial");
        p.setDescription("Mi primer portafolio en Java");

        portfolioDAO.insert(p);
        System.out.println("Portafolio creado");
    }
}
