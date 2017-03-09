package edu.matc.persistence;


import javax.persistence.*;

/**
 * Created by student on 3/8/17.
 */
@Entity
@Table(name = "user", schema = "PriceCheckData", catalog = "")
public class UserEntity {
    private int userId;
    private String apiKey;

    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "apiKey", nullable = false, length = 32)
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (apiKey != null ? !apiKey.equals(that.apiKey) : that.apiKey != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (apiKey != null ? apiKey.hashCode() : 0);
        return result;
    }
}
