// License: LGPL-3.0 License (c) find-sec-bugs
package injection;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.SqlConnection;

import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class SqlInjection {
    private static final String CLIENT_FIELDS = "client_id, client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_client_details (" + CLIENT_FIELDS + ")"
            + "values (?,?,?,?,?,?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    public class UserEntity {
        private Long id;
        private String test;

        public String getTest() {
            return test;
        }

        public void setTest(String test) {
            this.test = test;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    private static final PersistenceManagerFactory pmfInstance = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    public static PersistenceManager getPM() {
        return pmfInstance.getPersistenceManager();
    }

    public void testJdoQueries(String input) {
        PersistenceManager pm = getPM();

        pm.newQuery("select * from Users where name = " + input);

        pm.newQuery("sql", "select * from Products where name = " + input);

        // Test for false positive

        pm.newQuery("select * from Config");

        final String query = "select * from Config";
        pm.newQuery(query);

        pm.newQuery("sql", query);
    }

    public void testJdoQueriesAdditionalMethodSig(String input) {
        PersistenceManager pm = getPM();

        pm.newQuery(UserEntity.class, new ArrayList(), "id == " + input); // Injection?

        pm.newQuery(UserEntity.class, new ArrayList(), "id == 1");

        pm.newQuery(UserEntity.class, "id == " + input); // Injection?

        pm.newQuery(UserEntity.class, "id == 1");

        pm.newQuery((Extent) null, "id == " + input); // Injection?

        pm.newQuery((Extent) null, "id == 1");

    }

    public void testHibernate(SessionFactory sessionFactory, String input) {
        Session session = sessionFactory.openSession();
        String instring = String.format("%s", input);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> query = null;
        // should not be reported
        session.createQuery(query);
        // should be reported
        session.createQuery(instring);
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);


        Criteria criteria = session.createCriteria(UserEntity.class);

        //The following would need to be audited

        criteria.add(Restrictions.sqlRestriction("test=1234" + instring));

        session.createQuery("select t from UserEntity t where id = " + instring);


        //More sqlRestriction signatures

        criteria.add(Restrictions.sqlRestriction("param1  = ? and param2 = " + instring,instring, StandardBasicTypes.STRING));
        criteria.add(Restrictions.sqlRestriction("param1  = ? and param2 = " + instring,new String[] {instring}, new Type[] {StandardBasicTypes.STRING}));

        //OK nothing risky here..

        criteria.add(Restrictions.sqlRestriction("test=1234"));

        final String localSafe = "where id=1337";

        session.createQuery("select t from UserEntity t " + localSafe);

        final String localSql = "select * from TestEntity " + localSafe;

        session.createSQLQuery(localSql);

        //More sqlRestriction signatures (with safe binding)

        criteria.add(Restrictions.sqlRestriction("param1  = ?",instring, StandardBasicTypes.STRING));
        criteria.add(Restrictions.sqlRestriction("param1  = ? and param2 = ?", new String[] {instring}, new Type[] {StandardBasicTypes.STRING}));

    }

    public void testVertx(SqlConnection conn, SqlClient client, String injection) {
        // true positives
        client.query(injection);
        client.preparedQuery(injection);
        conn.prepare(injection);

        // false positives
        String constantValue = "SELECT * FROM test";
        client.query(constantValue);
        conn.query(constantValue);
    }

    public void testPreparedStmt(PreparedStatement stmt, String input) throws SQLException {
        stmt.execute("select * from users where email = " + input);
        stmt.execute("select * from users where email = " + input, Statement.RETURN_GENERATED_KEYS);
        stmt.execute("select * from users where email = " + input, new int[]{Statement.RETURN_GENERATED_KEYS});
        stmt.execute("select * from users where email = " + input, new int[]{Statement.RETURN_GENERATED_KEYS});
        stmt.executeQuery("select * from users where email = " + input);
        stmt.executeQuery("select * from users where email = '" + input +"' AND name != NULL");
        stmt.executeUpdate("update from users set email = '" + input +"' where name != NULL");
        stmt.executeLargeUpdate("update from users set email = '" + input +"' where name != NULL");
        stmt.addBatch("update from users set email = '" + input +"' where name != NULL");
    }

    public void good(String clientDetails) {
        final String statementUsingConstants = "insert into oauth_client_details (" + CLIENT_FIELDS + ")"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(statementUsingConstants, clientDetails);
    }

    public void good2(String clientDetails) {
        jdbcTemplate.update(DEFAULT_INSERT_STATEMENT, clientDetails);
    }

    public void bad(String clientDetails) {
        String stmtUsingFuncParam = "test" + clientDetails + "test";
        jdbcTemplate.update(stmtUsingFuncParam, clientDetails);
    }

    public void badInline(String clientDetails) {
        jdbcTemplate.update("test" + clientDetails + "test", clientDetails);
    }

}
