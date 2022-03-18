package com.example.Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="normaluser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private long userid;
	private String username;
	private String password;

}
