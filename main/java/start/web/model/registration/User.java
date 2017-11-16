package start.web.model.registration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import start.web.common.CommonEntity;

@Entity
@Table(name = "USERS")
public class User extends CommonEntity{
	
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private long id;
	
	public User() {
    }
             
    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.email = email;
        this.lastname = lastname;
        this.password = password;
    }
    
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
