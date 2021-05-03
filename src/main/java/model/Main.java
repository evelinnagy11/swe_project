package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Main {

    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            profiledao dao = handle.attach(profiledao.class);
            dao.createTable();
            dao.insertprofile(new profile(1, "teszt"));
            dao.insertprofile(new profile(2, "teszt2"));
            dao.insertprofile(new profile(3, "teszt3"));
            System.out.println(dao.getprofiles(2).get());
            dao.listprofiles().forEach(System.out::println);
        }
    }
}
