randomMySQLData
===============

Grenerates a bunch of Random Data into a MySQL Database.

Two ways to use this.

    Program Arguments:
      randomMySQLData "jdbc:mysql://<hostname>/<database>" "<DB USERNAME>" "<PASSWORD>"
      randomMySQLData "jdbc:mysql://172.16.75.182/adm201" "admintest" "mypass"
    
    Other option is hard code the values and recompile
      Set the following:
          DB_URL = "jdbc:mysql://<hostname>/<databas>"
          USER = "<USERNAME>"
          PASS = "<PASSWORD>"
