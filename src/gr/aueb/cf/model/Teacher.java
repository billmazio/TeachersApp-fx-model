package gr.aueb.cf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import gr.aueb.cf.application.TeachersAppFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class Teacher {
	private int id;
	private String sName;
	private String fName;
	
	public static int numberOfDeletedRowsAffected;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSName() {
		return sName;
	}
	
	public void setSName(String sName) {
		this.sName = sName;
	}
	
	public String getFName() {
		return fName;
	}
	public void setFName(String fname) {
		this.fName = fname;
	}
	
	
	public static ObservableList<Teacher> getTeachers(TextField searchNameTxt) {
		ObservableList<Teacher> teachersObsList = FXCollections.observableArrayList();
		String searchName;
		searchName = searchNameTxt.getText();
		PreparedStatement pst;
		ResultSet rs;
		int rsSize;
		
		try{
			String sql = "SELECT TEACHER_ID, S_NAME, F_Name FROM TEACHERS WHERE S_NAME LIKE ?";
			//String sql = "SELECT TEACHER_ID, S_NAME, F_Name FROM TEACHERS WHERE S_NAME LIKE '" + varSearchName + "%'";
			pst =  TeachersAppFX.getConnection().prepareStatement(sql);
			
	        pst.setString(1, searchName + '%');
	        rs = pst.executeQuery();
		
	        rs.last();
	        rsSize=rs.getRow(); 
	        
	        if (rsSize <= 0) {
	        	JOptionPane.showMessageDialog(null,	"Δεν υπάρχουν εγγραφές", "Κενό αποτέλεσμα", JOptionPane.WARNING_MESSAGE);
	        	return null;
	        }
	        
	        if (rsSize > 0 ){
	        	//System.out.println("YPARXOUN EGGRAFES");
	        	rs.beforeFirst();
	        	while (rs.next()){
	        		Teacher t = new Teacher();
	        		t.setId(rs.getInt("TEACHER_ID"));
	        		t.setFName(rs.getString("F_NAME"));
	        		t.setSName(rs.getString("S_Name"));
	        		
	        		teachersObsList.add(t);
	         	}
	        }
	        return teachersObsList;
	        		      
		} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
		}		
	}

	
	public void insert() {
		try {
			PreparedStatement p =  TeachersAppFX.getConnection().prepareStatement("INSERT INTO TEACHERS VALUES (?, ?, ?)");
				
			p.setInt(1, this.id);
			p.setString(2, this.sName);
			p.setString(3, this.fName);
			
			int n = p.executeUpdate();
			
			JOptionPane.showMessageDialog(null, n + " Record inserted.", "INSERT", 
					JOptionPane.PLAIN_MESSAGE);
			
			p.close();
		}  catch (SQLException e2){
			JOptionPane.showMessageDialog(null, "Invalid Key for TEACHER ID, Please try again", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void update() throws SQLException{
		String query = "UPDATE teachers set S_NAME = ?, F_Name = ? where TEACHER_ID = ?";
		PreparedStatement preparedStmt = TeachersAppFX.getConnection().prepareStatement(query);
	    preparedStmt.setString(1, this.sName);
	    preparedStmt.setString(2, this.fName);
	    preparedStmt.setInt(3, this.id);
	     
	    int numberOfRowsAffected = preparedStmt.executeUpdate();
	     
	    JOptionPane.showMessageDialog(null, numberOfRowsAffected + " rows affected", "UPDATE", JOptionPane.PLAIN_MESSAGE);
		preparedStmt.close();	
}
	
	
	public void delete() {
		try {
			String query = "DELETE from teachers where TEACHER_ID = ?";
		    PreparedStatement preparedStmt = TeachersAppFX.getConnection().prepareStatement(query);
		    preparedStmt.setInt(1,this.id);
	
		      // execute the prepared statement
		    int dialogButton;
	        dialogButton = JOptionPane.showConfirmDialog (null, "Είστε σίγουρος;", 
	      		  "Warning", JOptionPane.YES_NO_OPTION);
	
	        if (dialogButton == JOptionPane.YES_OPTION){
	        	numberOfDeletedRowsAffected = preparedStmt.executeUpdate();
	        	JOptionPane.showMessageDialog (null, numberOfDeletedRowsAffected + " rows deleted successfully", 
	          		  "DELETE", JOptionPane.INFORMATION_MESSAGE);
	        }
	      	   
		} catch (SQLException e) {
			System.out.println(e);	
		}
	}
}
