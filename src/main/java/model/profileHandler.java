package model;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class profileHandler {
    Jdbi jdbi = Jdbi.create("jdbc:sqlite:profiles.db")
            .installPlugin(new SqlObjectPlugin());
    Handle handle = jdbi.open();
    profileDao profiledao = handle.attach(profileDao.class);


}
