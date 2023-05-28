package com.example.jopy.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("message")
    private String message;
    @SerializedName("birth_year")
    private String birthYear;
    @SerializedName("skills")
    private String skills;
    @SerializedName("main_profession")
    private String mainProfession;
    @SerializedName("work_experience")
    private String workExperience;
    @SerializedName("courses")
    private String course;
    @SerializedName("categories")
    private List<String> categories;
    @SerializedName("gender")
    private String gender;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("languages")
    private List<String> languages;
    @SerializedName("education_level")
    private String educationLevel;
    @SerializedName("tags")
    private List<String> tags;
    @SerializedName("account_type")
    private String accountType;
    @SerializedName("account_type_id")
    private int accountTypeId;
    @SerializedName("token")
    private String token;
    @SerializedName("banned")
    private int banned;
    @SerializedName("account_money")
    private int accountMoney;
    @SerializedName("city")
    private String city;
    @SerializedName("city_id")
    private int cityId;
    @SerializedName("country")
    private String country;
    @SerializedName("country_id")
    private int countryId;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("image")
    private String image;
    @SerializedName("id")
    private int id ;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("name")
    private String name;
    @SerializedName("number_of_posts")
    private int number_of_posts;
    @SerializedName("still_have_posts")
    private int still_have_posts;
    @SerializedName("details")
    private User details;
    @SerializedName("education")
    private String education;
    @SerializedName("verification")
    private String verification;
    @SerializedName("institute_type")
    private String instituteType;
    @SerializedName("institute_name")
    private String instituteName;
    @SerializedName("number_of_employees")
    private String numberOfEmployees;
    @SerializedName("institute_field")
    private String instituteField;
    @SerializedName("about")
    private String about;

    public User() {
    }

    public User(String gender, String firstName, String lastName, List<String> languages, String educationLevel, List<String> tags, String accountType, int accountTypeId, String token, int banned, int accountMoney, String city, int cityId, String country, int countryId,
                String phoneNumber, String image, int id, String email, String password) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.languages = languages;
        this.educationLevel = educationLevel;
        this.tags = tags;
        this.accountType = accountType;
        this.accountTypeId = accountTypeId;
        this.token = token;
        this.banned = banned;
        this.accountMoney = accountMoney;
        this.city = city;
        this.cityId = cityId;
        this.country = country;
        this.countryId = countryId;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public User(int id, int accountTypeId, String phoneNumber, int countryId, int cityId, int accountMoney, int banned,
                String email, String password, String image, String token) {
        this.id = id;
        this.accountTypeId = accountTypeId;
        this.phoneNumber = phoneNumber;
        this.countryId = countryId;
        this.cityId = cityId;
        this.accountMoney = accountMoney;
        this.banned = banned;
        this.email = email;
        this.password = password;
        this.image = image;
        this.token = token;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getStill_have_posts() {
        return still_have_posts;
    }

    public void setStill_have_posts(int still_have_posts) {
        this.still_have_posts = still_have_posts;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(int accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getMainProfession() {
        return mainProfession;
    }

    public void setMainProfession(String mainProfession) {
        this.mainProfession = mainProfession;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumber_of_posts() {
        return number_of_posts;
    }

    public void setNumber_of_posts(int number_of_posts) {
        this.number_of_posts = number_of_posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getDetails() {
        return details;
    }

    public void setDetails(User details) {
        this.details = details;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(String numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getInstituteField() {
        return instituteField;
    }

    public void setInstituteField(String instituteField) {
        this.instituteField = instituteField;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "User{" +
                "message='" + message + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", skills='" + skills + '\'' +
                ", mainProfession='" + mainProfession + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", course=" + course +
                ", categories=" + categories +
                ", gender='" + gender + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", languages=" + languages +
                ", educationLevel='" + educationLevel + '\'' +
                ", tags=" + tags +
                ", accountType='" + accountType + '\'' +
                ", accountTypeId=" + accountTypeId +
                ", token='" + token + '\'' +
                ", banned=" + banned +
                ", accountMoney=" + accountMoney +
                ", city='" + city + '\'' +
                ", cityId=" + cityId +
                ", country='" + country + '\'' +
                ", countryId=" + countryId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", image='" + image + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", number_of_posts=" + number_of_posts +
                ", details=" + details +
                ", education='" + education + '\'' +
                '}';
    }

}

