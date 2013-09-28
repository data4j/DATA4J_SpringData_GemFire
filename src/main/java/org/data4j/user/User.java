package org.data4j.user;

import com.gemstone.gemfire.pdx.PdxReader;
import com.gemstone.gemfire.pdx.PdxSerializable;
import com.gemstone.gemfire.pdx.PdxWriter;

/**
 * User Bean
 * 
 * @author data4j.org
 * @since 29 Sept 2013
 * @version 1.0.0
 *
 */
public class User implements PdxSerializable {
	
	private String id;
	private String name;
	private String surname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}	

	@Override
	public void fromData(PdxReader reader) {
		setId(reader.readString("id"));
		setName(reader.readString("name"));
		setSurname(reader.readString("surname"));
	}

	@Override
	public void toData(PdxWriter writer) {
		writer.writeString("id", getId());
		writer.writeString("name", getName());
		writer.writeString("surname", getSurname());
	}

	@Override
	public String toString() {
		return "User [id=" + id 
				       + ", name=" + name 
				       + ", surname=" + surname	+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
		
}
