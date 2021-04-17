package es.deusto.server.db;

//import java.sql.Connection; 
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import es.deusto.spq.domainObjects.Employee;
//
///**
// * Retrive information from mysql DB!
// *
// */
//public class DBTest 
//{
//	
//	private static Statement stmt;
//	private static ResultSet results;
//	
//	public static void main(String[] args) {
//		
//		String sql_select = "Select * From employee_info";
//		
//		try(Connection conn = DBManager.createNewDBconnection()){
//			
//			stmt = conn.createStatement();
//			results = stmt.executeQuery(sql_select);
//			
//			List<Employee> employeeList = new ArrayList<Employee>();			
//			
//			 while (results.next()) {
//				 
//				Employee stdObject = new Employee();
//				
//				stdObject.setId(Integer.valueOf(results.getString("id")));
//				stdObject.setName(results.getString("name"));
//				stdObject.setAddress(results.getString("Address"));
//				stdObject.setDepartment(results.getString("department"));
//				
//				employeeList.add(stdObject);
//			 }
//			
//			ObjectMapper mapper = new ObjectMapper();
//		    String JSONOutput = mapper.writeValueAsString(employeeList);
//		    System.out.println(JSONOutput);
//		    
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//	}
//}