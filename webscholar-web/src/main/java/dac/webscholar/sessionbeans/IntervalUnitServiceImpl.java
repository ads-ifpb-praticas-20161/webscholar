package dac.webscholar.sessionbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.IntervalUnit;
import dac.webscholar.shared.entities.Lecture;
import dac.webscholar.shared.interfaces.IntervalUnitService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 26/09/2016.
 */
@Stateless
@Remote(IntervalUnitService.class)
@Local(IntervalUnitServiceLocal.class)
public class IntervalUnitServiceImpl implements Serializable, IntervalUnitServiceLocal, IntervalUnitService {

    @Inject
    private ListStrategyBuilder<IntervalUnit> listStrategyBuilder;

    private ListStrategy<IntervalUnit> listStrategy;

    @Override
    public List<IntervalUnit> listIntervalUnits() {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .getListStrategy();
        return listStrategy.getResultList();
    }
}
