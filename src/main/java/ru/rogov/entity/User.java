package ru.rogov.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7697573072050702452L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	
    @Column(name="LOGIN", nullable=false)
	private String	username;
	
    @Column(name="PASSWORD", nullable=false)
	private String	password;
	
    @Column(name="ROLE", nullable=false)
	private Long role_id;
	
	@ManyToOne
	@JoinColumn(name = "ROLE", referencedColumnName = "RIGHTS",insertable = false, updatable = false)
	private Role role;

	public User()
	{
		super();
	}


	public User(String username, String password, Long role_id)
	{
		super();
		this.username = username;
		this.password = password;
		this.role_id = role_id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}
	
	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getRole_id()
	{
		return role_id;
	}


	public void setRole_id(Long role_id)
	{
		this.role_id = role_id;
	}
	

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((role_id == null) ? 0 : role_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null)
		{
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (role_id == null)
		{
			if (other.role_id != null)
				return false;
		} else if (!role_id.equals(other.role_id))
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role_id=" + role_id + ", role=" + role + "]";
	}

}
