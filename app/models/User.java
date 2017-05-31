/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotBlank;
//import org.openqa.selenium.lift.find.Finder;
import play.db.ebean.Model;
/**
 *
 * @author a-yamamoto
 */
@Entity
public class User extends Model{ 
    @Id
    public Long id;
    
    @NotBlank
    public String username;
    public String userid;
    public String password;
    public Integer type;
    public boolean deleteFlag;
    public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
}
