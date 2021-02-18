package org.example.buyticket.app.persistence.schema;

import org.example.buyticket.app.model.entity.geography.Address;
import org.example.buyticket.app.model.entity.geography.City;
import org.example.buyticket.app.model.entity.geography.Coordinate;
import org.example.buyticket.app.model.entity.geography.Station;
import org.example.buyticket.app.model.entity.person.Account;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link Export} dynamically generates SQL schema;
 *
 * @author Gulyamov Rustam
 */
public class Export {
    /**
     *
     *
     *
     * @param folder
     * @param dialect
     */
    public static void exportDatabase(String folder, Class<? extends Dialect> dialect) {
        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder().applySetting("hibernate.dialect", dialect.getName()).build());

        Set<Class<?>> entityClasses = new HashSet<>();
        entityClasses.add(City.class);
        entityClasses.add(Coordinate.class);
        entityClasses.add(Station.class);
        entityClasses.add(Account.class);
        entityClasses.add(Address.class);
        entityClasses.forEach(metadata::addAnnotatedClass);

        SchemaExport schema = new SchemaExport();
        schema.setDelimiter(";");
        schema.setOutputFile(folder + "schema_" + dialect.getSimpleName() + ".sql");

        schema.create(EnumSet.of(TargetType.SCRIPT), metadata.buildMetadata());
    }

    public static void main(String[] args) {
        exportDatabase("", PostgreSQLDialect.class);
    }
}
