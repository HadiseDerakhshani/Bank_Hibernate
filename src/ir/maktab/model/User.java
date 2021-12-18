package ir.maktab.model;


import ir.maktab.enums.UserType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String family;
    private String nationalCode;
    private String phoneNumber;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date lastUpdate;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Update> updates = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createDate=" + createDate +
                ", lastUpdate=" + lastUpdate +
                ", userType=" + userType +
                ", accounts=" + accounts +
                ", updates=" + updates +
                '}';
    }


    public static final class UserBuilder {
        private int id;
        private String name;
        private String family;
        private String nationalCode;
        private String phoneNumber;
        private Date createDate;
        private Date lastUpdate;
        private UserType userType;
        private List<Account> accounts = new ArrayList<>();
        private List<Update> updates = new ArrayList<>();

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withFamily(String family) {
            this.family = family;
            return this;
        }

        public UserBuilder withNationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public UserBuilder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder withCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public UserBuilder withLastUpdate(Date lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public UserBuilder withUserType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserBuilder withAccounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public UserBuilder withUpdates(List<Update> updates) {
            this.updates = updates;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setFamily(family);
            user.setNationalCode(nationalCode);
            user.setPhoneNumber(phoneNumber);
            user.setCreateDate(createDate);
            user.setLastUpdate(lastUpdate);
            user.setUserType(userType);
            user.setAccounts(accounts);
            user.setUpdates(updates);
            return user;
        }
    }
}
