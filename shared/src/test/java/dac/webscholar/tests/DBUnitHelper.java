package dac.webscholar.tests;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

/**
 * Created by vmvini on 10/09/16.
 */
public class DBUnitHelper {

    private Connection conexao;
    private DatabaseConnection conexaoDBUnit;
    private String xmlFolder;


    public DBUnitHelper(String xmlFolder) {
        this.xmlFolder = xmlFolder;

        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dacDB", "user", "123456");
            conexaoDBUnit = new DatabaseConnection(conexao);
            DatabaseConfig config = conexaoDBUnit.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
        } catch (Exception e) {
            throw new RuntimeException("Erro inicializando DBUnit", e);
        }
    }

    public void execute(DatabaseOperation operation, String xml) {
        try {
            InputStream is = getClass().getResourceAsStream("/" + xmlFolder + "/" + xml);
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            IDataSet dataSet = builder.build(is);

            operation.execute(conexaoDBUnit, dataSet);
        } catch (Exception e) {
            throw new RuntimeException("Erro executando DbUnit", e);
        }
    }

    public void close() {
        try {
            conexaoDBUnit.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
