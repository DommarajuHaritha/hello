import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class database extends HttpServlet{
   public void doGet(HttpServletRequest request,HttpServletResponse response)
     throws ServletException, IOException
  {
      // JDBC driver name and database URL
      final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
      final String DB_URL="jdbc:mysql://localhost/id911357_mydb";


      //  Database credentials
      final String USER = "id911357_du";
      final String PASS = "database@123";
	
	Statement stmt=null;
	Connection conn =null;

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String iden=request.getparameter("id");
      String title = "Database Result";
      
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT id FROM product";
         ResultSet rs = stmt.executeQuery(sql);

         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            String id  = rs.getInt("id");
              if((strcmp(id,iden))==0)
             {
                    if((strcmp(pick,"yes"))==0)
                    { out.println("ID: " + id + "<br>");
                      out.println("Executive Name: " + pickName + "<br>");
			out.println("Executive No: " + pickNo + "<br>");
			 out.println("Date: " + pickDate + "<br>");
			out.println("Time: " + pickTime + "<br>");
			}
	          if((strcmp(repair,"yes"))==0)
                  {  out.println("ID: " + id + "<br>");
                     out.println("Repair Time: " + repairTime + "<br>");
		     out.println("Repair comments: " + repairComm + "<br>");
		}
			  if((strcmp(delivery,"yes"))==0)
                    { out.println("ID: " + id + "<br>");
                      out.println("Executive Name: " + delName + "<br>");
			out.println("Executive No: " + delNo + "<br>");
			out.println("Executive Name: " + pickName + "<br>");
			 out.println("Date: " + delDate + "<br>");
			out.println("Time: " + delTime + "<br>");
			}
                     }
			
                      
                
           // String name = rs.getString("name");

            //Display values
           // out.println("ID: " + id + "<br>");
           // out.println(",Name: " + name + "<br>");
         }
         out.println("</body></html>");

         // Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(conn!=null)
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      } //end try
   }
}
