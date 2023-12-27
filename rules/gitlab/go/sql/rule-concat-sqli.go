// License: Apache 2.0 (c) gosec
// source: https://github.com/securego/gosec/blob/master/testutils/source.go
// hash: bfb0f42

package main

import (
	"context"
	"database/sql"
	"fmt"
	"strings"
)

func dbUnsafe(tainted string) {
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		panic(err)
	}

	ctx := context.Background()

	// ruleid:go_sql_rule-concat-sqli
	unsafe1 := "select * from foo where bar = '" + tainted + "'"
	// ruleid:go_sql_rule-concat-sqli
	unsafe2 := "select * from foo where id = " + tainted
	// ruleid:go_sql_rule-concat-sqli
	unsafe3 := fmt.Sprintf("select * from foo where bar = %q", tainted)
	unsafe4 := strings.Builder{}
	// ruleid:go_sql_rule-concat-sqli
	unsafe4.WriteString("select * from foo where bar = ")
	unsafe4.WriteString(tainted)

	db.Exec(unsafe1)
	db.Exec(unsafe2)
	db.Exec(unsafe3)
	db.Exec(unsafe4.String())

	// ruleid:go_sql_rule-concat-sqli
	db.Exec("SELECT * FROM foo WHERE bar = '" + tainted + "'")
	// ruleid:go_sql_rule-concat-sqli
	db.ExecContext(ctx, "INSERT INTO foo VALUES('"+tainted+"')")
	// ruleid:go_sql_rule-concat-sqli
	db.Query("UPDATE foo SET bar = '" + tainted + "' WHERE id = 100")
	// ruleid:go_sql_rule-concat-sqli
	db.QueryContext(ctx, "select * from foo where bar = '"+tainted+"'")
	// ruleid:go_sql_rule-concat-sqli
	db.QueryRow("insert into foo values('" + tainted + "')")
	// ruleid:go_sql_rule-concat-sqli
	db.QueryRowContext(ctx, "update foo set bar = '"+tainted+"' where id = 100")
}

func dbSafe() {
	db, err := sql.Open("sqlite3", ":memory:")
	if err != nil {
		panic(err)
	}

	ctx := context.Background()

	// ok:go_sql_rule-concat-sqli
	safe1 := "select * from foo where bar = '" + "safe" + "'"
	// ok:go_sql_rule-concat-sqli
	safe2 := "select * from foo where id = " + "safe"
	// ok:go_sql_rule-concat-sqli
	safe3 := fmt.Sprintf("select * from foo where bar = %q", "safe")

	db.Exec(safe1)
	db.Exec(safe2)
	db.Exec(safe3)

	// ok:go_sql_rule-concat-sqli
	db.Exec("SELECT * FROM foo WHERE bar = '" + "safe" + "'")
	// ok:go_sql_rule-concat-sqli
	db.ExecContext(ctx, "INSERT INTO foo VALUES('"+"safe"+"')")
	// ok:go_sql_rule-concat-sqli
	db.Query("UPDATE foo SET bar = '" + "safe" + "' WHERE id = 100")
	// ok:go_sql_rule-concat-sqli
	db.QueryContext(ctx, "select * from foo where bar = '"+"safe"+"'")
	// ok:go_sql_rule-concat-sqli
	db.QueryRow("insert into foo values('" + "safe" + "')")
	// ok:go_sql_rule-concat-sqli
	db.QueryRowContext(ctx, "update foo set bar = '"+"safe"+"' where id = 100")
}
