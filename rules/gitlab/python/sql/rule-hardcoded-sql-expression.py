# License: Apache 2.0 (c) PyCQA
# source: https://github.com/PyCQA/bandit/blob/master/examples/sql_statements.py
# hash:  8eee173

import sqlalchemy

# ruleid: python_sql_rule-hardcoded-sql-expression
cur.execute("SELECT * FROM foo WHERE id = '%s'" % identifier)
# ruleid: python_sql_rule-hardcoded-sql-expression
cur.execute("INSERT INTO foo VALUES ('a', 'b', '%s')" % value)
# ruleid: python_sql_rule-hardcoded-sql-expression
cur.execute("DELETE FROM foo WHERE id = '%s'" % identifier)
# ruleid: python_sql_rule-hardcoded-sql-expression
cur.execute("UPDATE foo SET value = 'b' WHERE id = '%s'" % identifier)
# ruleid: python_sql_rule-hardcoded-sql-expression
cur.execute("SELECT * FROM foo WHERE id = '" + identifier + "'")
# ruleid: python_sql_rule-hardcoded-sql-expression
cur.execute("SELECT * FROM foo WHERE id = '{}'".format(identifier))

# ok: python_sql_rule-hardcoded-sql-expression
cur.execute("SELECT * FROM foo WHERE id = '%s'", identifier)
# ok: python_sql_rule-hardcoded-sql-expression
cur.execute("INSERT INTO foo VALUES ('a', 'b', '%s')", value)
# ok: python_sql_rule-hardcoded-sql-expression
cur.execute("DELETE FROM foo WHERE id = '%s'", identifier)
# ok: python_sql_rule-hardcoded-sql-expression
cur.execute("UPDATE foo SET value = 'b' WHERE id = '%s'", identifier)

# real world false positives
choices=[('server_list', _("Select from active instances"))]
print("delete from the cache as the first argument")

def select_note_by_id(conn, id=None):
	query = "SELECT * FROM notes"
	if id:
		query = query + " WHERE id = '%s'" % id
	cur = conn.cursor()
	# ruleid: python_sql_rule-hardcoded-sql-expression
	cur.execute(query)
	rows = cur.fetchall()
	return rows

# ok: python_sql_rule-hardcoded-sql-expression
foo.execute("unrelated to select and sql" % identifier)

# ok: python_sql_rule-hardcoded-sql-expression
foo.execute("updated" % identifier)

# ok: python_sql_rule-hardcoded-sql-expression
cur.execute('SELECT * FROM dual')

# ok: python_sql_rule-hardcoded-sql-expression
cur.execute('CREATE TABLE %s' % tableName)

ctStm = 'CREATE TABLE '
ctStm = ctStm + ' %s' % tableName
# ok: python_sql_rule-hardcoded-sql-expression
cur.execute(ctStm)

upStm = 'UPDATE foo WHERE user = %s' % 'admin'
# ok: python_sql_rule-hardcoded-sql-expression
cur.execute(upStm)

upStm2 = f'UPDATE foo WHERE user = "admin"'
# ok: python_sql_rule-hardcoded-sql-expression
cur.execute(upStm2)

# ok: python_sql_rule-hardcoded-sql-expression
cur.execute(f'UPDATE foo WHERE user = "admin"')
