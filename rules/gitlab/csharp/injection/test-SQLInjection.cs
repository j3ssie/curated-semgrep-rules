// License: LGPL-3.0 License (c) security-code-scan
using System.Data.Entity;
using System.Data.Entity.Core.EntityClient;
using System.Data.Odbc;
using System.Data.OleDb;
using System.Data.SqlClient;
using MySql.Data.MySqlClient;
using Npgsql;
using Oracle.ManagedDataAccess.Client;

class SQLInjection
{
    static void QueryWithUserInput(DbContext ctx, string input)
    {
        var cmd = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        ctx.Database.ExecuteSqlCommand(cmd);
        ctx.Database.ExecuteSqlCommandAsync(cmd);
    }

    static void QueryWithUserInputSqlClient(string input)
    {
        var cmd = new SqlCommand("SELECT * FROM Users WHERE username = '" + input + "' and role='user'");
        cmd.ExecuteReader();
    }

    static void QueryWithUserInputCommandText(string input)
    {
        SqlCommand sqlCmd = new SqlCommand();
        sqlCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        sqlCmd.CommandText = "abc";
        sqlCmd.ExecuteReader();

        var oracleCmd = new OracleCommand();
        oracleCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        oracleCmd.CommandText = "abc";
        oracleCmd.ExecuteReader();

        var npgsqlCmd = new NpgsqlCommand();
        npgsqlCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        npgsqlCmd.CommandText = "abc";
        npgsqlCmd.ExecuteReader();

        var mySqlCmd = new MySqlCommand();
        mySqlCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        mySqlCmd.CommandText = "abc";
        mySqlCmd.ExecuteReader();

        var entityCmd = new EntityCommand();
        entityCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        entityCmd.CommandText = "abc";
        entityCmd.ExecuteReader();

        OdbcCommand odbcCmd = new OdbcCommand();
        odbcCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        odbcCmd.CommandText = "abc";
        odbcCmd.ExecuteReader();

        var oleDbCmd = new OleDbCommand();
        oleDbCmd.CommandText = "SELECT * FROM Users WHERE username = '" + input + "' and role='user'";
        oleDbCmd.CommandText = "abc";
        oleDbCmd.ExecuteReader();

        // Check with formatted strings too.
        oleDbCmd.CommandText = String.Format("SELECT * FROM Users WHERE username = '{0}' and role='user'", input);
        oleDbCmd.ExecuteReader();
    }

    static void SafeSanitized(DbContext ctx, string input)
    {
        var cmd = "SELECT * FROM Users WHERE username = @username and role='user'";
        ctx.Database.ExecuteSqlCommand(
            cmd,
            new SqlParameter("@username", input));
    }

    static void SafeConstant(DbContext ctx)
    {
        var cmd = "SELECT * FROM Users";
        ctx.Database.ExecuteSqlCommand(cmd);
    }

    static void SafeConstantSqlClient()
    {
        var cmd = new SqlCommand("SELECT * FROM Users");
        cmd.ExecuteReader();
    }

    static void SafeSanitizedSqlClient(string input)
    {
        var cmd = new SqlCommand("SELECT * FROM Users WHERE username = '@username' and role='user'");
        cmd.Parameters.AddWithValue("@username", input);
        cmd.Parameters["@username"].Value = input;
        cmd.ExecuteReader();
    }
}
