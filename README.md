Instructions to run this project!

Ensure that you have an IDE (such as Eclipse or IntelliJ) and PostgreSQL installed on your computer.

Download the project from the GitHub repository and extract it to a location on your computer.

Open your IDE and import the project by selecting the extracted folder as the project directory.
Download the JDBC driver for PostgreSQL and add it as a dependency to your project in your IDE. Ensure that the version of the driver matches the version of PostgreSQL that you have installed.

Create a new database in PostgreSQL with the following columns: _id, yearofbirth, gender, ethnicity, childsfirstname, count, and rank. You can get the details for the database from the babyNamesDB.csv file.
Import the babyNamesDB.csv file into the newly created database. You can do this using pgAdmin or your terminal. For example, you can use the following command in your terminal:
css. Copy code psql -U <username> -d <databasename> -c "COPY tablename FROM '/path/to/file.csv' DELIMITER ',' CSV HEADER;"
Replace <username> with your PostgreSQL username, <databasename> with the name of the database you created in step 5, and /path/to/file.csv with the file path to the babyNamesDB.csv file on your computer.

Navigate to the DataBaseConnection.java file in the project and change the database details so that the database is taken from your own localhost and database. 
For example, if you named your database babynames,your localhost is localhost, your username is postgres, and your password is password123, then the string should be:
Copy code: String url = "jdbc:postgresql://localhost/babynames?user=postgres&password=password123";

Run the project and ensure that it is able to connect to the database and retrieve data. You should be able to view the result of the database query on the user interface.
