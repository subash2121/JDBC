
import java.sql.* ;
import java.util.*;

public class JDBC{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        try {
            //1: Register the mysql jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver registered");
            String URL = "jdbc:mysql://localhost:3306/?user=root";
            //2: Connect to the mysql database
            Connection con = DriverManager.getConnection(URL, "root", "subash2002");
            Statement s = con.createStatement();
            System.out.println("Connection to Database Established");
            System.out.println("*******************************************");
            System.out.println();
            System.out.println("Welcome to the Great Kirikalan Magic Show");
            System.out.println();
            System.out.println("*******************************************");
            System.out.println("Store all your favourite movie detials here:");
            int ch=0;
            s.executeUpdate("USE movie");
            while(ch<5){
                System.out.println("### Menu ###");
                System.out.println("1: Add & Use a new Database and Table");
                System.out.println("2: Add Movie");
                System.out.println("3: Get all Movies");
                System.out.println("4: Get Movies of specific hero");
                System.out.println("5: Exit");
                System.out.println("Enter Your Choice");
                ch=sc.nextInt();
                sc.nextLine();
                switch (ch){
                    case 1:
                        System.out.println("Enter the Database name");
                        String str = sc.nextLine();
                        //3: Create a new database
                        s.executeUpdate("CREATE DATABASE " + str);
                        s.executeUpdate("USE " + str);
                        System.out.println("Database Created");
                        //4: Create a new table
                        s.executeUpdate("CREATE TABLE FAVMOVIE " +
                                "(name VARCHAR(255) not NULL, " +
                                " actor VARCHAR(255), " +
                                " actress VARCHAR(255), " +
                                " director VARCHAR(255) not NULL, " +
                                " yearOfRelease INTEGER not NULL)");

                        System.out.println("FAVMOVIE Table Created :)");
                        break;
                    case 2:
                        //5: Insert into table
                        System.out.println("Enter the number of entries");
                        int n = sc.nextInt();
                        sc.nextLine();
                        for (int i = 0; i < n; i++) {
                            System.out.println("Enter the Following values");
                            System.out.println("Movie Name: ");
                            String mname = sc.nextLine();
                            System.out.println("Actor Name: ");
                            String aname = sc.nextLine();
                            System.out.println("Actress Name: ");
                            String acname = sc.nextLine();
                            System.out.println("Director Name: ");
                            String dname = sc.nextLine();
                            System.out.println("Year Of Release: ");
                            int date = sc.nextInt();
                            sc.nextLine();
                            s.executeUpdate("insert into FAVMOVIE values('" + mname + "','" + aname + "','" + acname + "','" + dname + "','" + date + "' )");
                        }
                        System.out.println("All the values are inserted successfully");
                        break;
                    case 3:
                        //6: Query the whole table
                        System.out.println("Your Favourite Movie List ");
                        ResultSet rs = s.executeQuery("select * from FAVMOVIE");
                        System.out.println("Movie | Actor | Actress | Director | Year ");
                        while (rs.next()) {
                            System.out.println(rs.getString("name") + "\t\t" +
                                    rs.getString("actor") + "\t\t" +
                                    rs.getString("actress") + "\t\t" +
                                    rs.getString("director") + "\t\t" +
                                    rs.getInt("yearOfRelease"));
                        }
                        break;
                    case 4:
                        //7: Query with constraints
                        System.out.println("Enter the actor name to filter");
                        String actor=sc.nextLine();
                        System.out.println("Your Favourite Movie List ");
                        rs = s.executeQuery("select * from FAVMOVIE where actor = '"+actor+"'");
                        System.out.println("Movie | Actor | Actress | Director | Year ");
                        while (rs.next())
                        {
                            System.out.println(rs.getString("name")+"\t\t"+
                                    rs.getString("actor")+"\t\t"+
                                    rs.getString("actress")+"\t\t"+
                                    rs.getString("director")+"\t\t"+
                                    rs.getInt("yearOfRelease"));
                        }
                }
            }
            System.out.println("Hope you had a good time using our service");
            System.out.println("Have a great day");
            //8: Close the connection
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Oops Software failure !!");
            System.out.println("Enter 1 to check the error");
            int ch=sc.nextInt();
            if (ch==1){
                System.out.println(e);
                System.out.println("Contact subash21annadurai@gmail.com to rectify it");
            }
            System.out.println("Reboot the software");
            System.exit(1);
        }
    }
}