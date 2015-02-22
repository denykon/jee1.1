package by.gsu.epamlab.model.beans;

import java.io.Serializable;

/**
 * User
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1432269255498800284L;
    private int id;
    private String login;
    private String firstName;
    private String lastName;

	public User() {
		super();
	}

    public User(int id, String login, String firstName, String lastName) {
        super();
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * get user login
     * @return String
     */
    public String getLogin() {
        return login;
    }

    /**
     * set user login
     * @param login String
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * get user first name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set user first name
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * get user last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set user last name
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * hashCode
     * @return hashCode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }

    /**
     * equals
     * @param obj user
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }
}
