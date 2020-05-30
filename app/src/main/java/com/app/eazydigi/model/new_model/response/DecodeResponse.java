package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class DecodeResponse {

    /**
     * schoolNum :
     * sub : ssnalanda
     * loginUser : {"userId":595,"schoolId":5,"emailId":"anandvardhansinha@gmail.com","userName":"ssnalanda","phone":"","firstName":"Sample School, Nalanda","middleName":"","lastName":"admin","dob":"","isAccountExpired":null,"expiryDate":null,"isEnabled":0,"passResetToken":null,"passResetTokenExpiry":null,"roles":[{"roleId":2,"name":"ADMIN","description":"school admin role"}]}
     * schoolAddess : Nalanda Cantt, Main Road, Nalanda
     * schoolEmail : anandvardhansinha@gmail.com
     * schoolAfflNum : 98989812189898
     * schoolName : Sample School, Nalanda
     * exp : 1590757907
     * userDetails : {"password":"$2a$10$SkPdHU75GexrMrzd/3Nm0OavKmqIM2D1ll6sI4H2KL0XUVnkbQVPi","username":"ssnalanda","authorities":[{"authority":"ROLE_ADMIN"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
     * schoolLogo : school-5.jpeg
     * iat : 1590153107
     * schoolMobNo : 7720007048
     */

    private String schoolNum;
    private String sub;
    private LoginUserBean loginUser;
    private String schoolAddess;
    private String schoolEmail;
    private String schoolAfflNum;
    private String schoolName;
    private int exp;
    private UserDetailsBean userDetails;
    private String schoolLogo;
    private int iat;
    private long schoolMobNo;

    public String getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(String schoolNum) {
        this.schoolNum = schoolNum;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public LoginUserBean getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUserBean loginUser) {
        this.loginUser = loginUser;
    }

    public String getSchoolAddess() {
        return schoolAddess;
    }

    public void setSchoolAddess(String schoolAddess) {
        this.schoolAddess = schoolAddess;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getSchoolAfflNum() {
        return schoolAfflNum;
    }

    public void setSchoolAfflNum(String schoolAfflNum) {
        this.schoolAfflNum = schoolAfflNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public UserDetailsBean getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsBean userDetails) {
        this.userDetails = userDetails;
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }

    public int getIat() {
        return iat;
    }

    public void setIat(int iat) {
        this.iat = iat;
    }

    public long getSchoolMobNo() {
        return schoolMobNo;
    }

    public void setSchoolMobNo(long schoolMobNo) {
        this.schoolMobNo = schoolMobNo;
    }

    public static class LoginUserBean {
        /**
         * userId : 595
         * schoolId : 5
         * emailId : anandvardhansinha@gmail.com
         * userName : ssnalanda
         * phone :
         * firstName : Sample School, Nalanda
         * middleName :
         * lastName : admin
         * dob :
         * isAccountExpired : null
         * expiryDate : null
         * isEnabled : 0
         * passResetToken : null
         * passResetTokenExpiry : null
         * roles : [{"roleId":2,"name":"ADMIN","description":"school admin role"}]
         */

        private int userId;
        private int schoolId;
        private String emailId;
        private String userName;
        private String phone;
        private String firstName;
        private String middleName;
        private String lastName;
        private String dob;
        private Object isAccountExpired;
        private Object expiryDate;
        private int isEnabled;
        private Object passResetToken;
        private Object passResetTokenExpiry;
        private List<RolesBean> roles;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public Object getIsAccountExpired() {
            return isAccountExpired;
        }

        public void setIsAccountExpired(Object isAccountExpired) {
            this.isAccountExpired = isAccountExpired;
        }

        public Object getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Object expiryDate) {
            this.expiryDate = expiryDate;
        }

        public int getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(int isEnabled) {
            this.isEnabled = isEnabled;
        }

        public Object getPassResetToken() {
            return passResetToken;
        }

        public void setPassResetToken(Object passResetToken) {
            this.passResetToken = passResetToken;
        }

        public Object getPassResetTokenExpiry() {
            return passResetTokenExpiry;
        }

        public void setPassResetTokenExpiry(Object passResetTokenExpiry) {
            this.passResetTokenExpiry = passResetTokenExpiry;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public static class RolesBean {
            /**
             * roleId : 2
             * name : ADMIN
             * description : school admin role
             */

            private int roleId;
            private String name;
            private String description;

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }

    public static class UserDetailsBean {
        /**
         * password : $2a$10$SkPdHU75GexrMrzd/3Nm0OavKmqIM2D1ll6sI4H2KL0XUVnkbQVPi
         * username : ssnalanda
         * authorities : [{"authority":"ROLE_ADMIN"}]
         * accountNonExpired : true
         * accountNonLocked : true
         * credentialsNonExpired : true
         * enabled : true
         */

        private String password;
        private String username;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;
        private List<AuthoritiesBean> authorities;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isAccountNonExpired() {
            return accountNonExpired;
        }

        public void setAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
        }

        public boolean isAccountNonLocked() {
            return accountNonLocked;
        }

        public void setAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
        }

        public boolean isCredentialsNonExpired() {
            return credentialsNonExpired;
        }

        public void setCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<AuthoritiesBean> getAuthorities() {
            return authorities;
        }

        public void setAuthorities(List<AuthoritiesBean> authorities) {
            this.authorities = authorities;
        }

        public static class AuthoritiesBean {
            /**
             * authority : ROLE_ADMIN
             */

            private String authority;

            public String getAuthority() {
                return authority;
            }

            public void setAuthority(String authority) {
                this.authority = authority;
            }
        }
    }
}
