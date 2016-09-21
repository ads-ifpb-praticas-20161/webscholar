package dac.webscholar.tests;

/**
 * Created by marcusviniv on 21/09/2016.
 */
import dac.webscholar.Utils.CpfValidator;
import dac.webscholar.cdiqualifiers.LoginProxy;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.sessionbeans.Initializer;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.utils.RoleUriMap;
import jsfbeans.LoginMB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class ArquillianTest {

    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar" )
                .addPackage(ScholarUser.class.getPackage())
                .addPackage(RoleUriMap.class.getPackage())
                .addPackage(LoginProxy.class.getPackage())
                .addPackage(ListStrategy.class.getPackage())
                .addPackage(Initializer.class.getPackage())
                .addPackage(CpfValidator.class.getPackage())
                .addPackage(LoginMB.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsResource("META-INF/persistence.xml");
    }

}
