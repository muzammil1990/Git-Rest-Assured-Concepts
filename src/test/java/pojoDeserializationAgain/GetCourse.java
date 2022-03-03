/**
 * 
 */
package pojoDeserializationAgain;

/**
 * Section 11: Deserialization 
 * 64. Strategies in Parsing Complex nested
 * 
 * main class GetCourses= main parent json
 * 
 * 1.This is to create pojo class to get the courses
 * 2.To generate setter and getters shortcut keys are alt + shift + s
 * 3.Refer section11 for json saved in personal document
 * 
 * 4. Converting the java response into java object using pojo classes, Hence deserialization
 */
public class GetCourse {  

    private String url;
    private String services;
    private String expertise;
    private Courses courses;
    private String instructor;
    private String linkedIn;


    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getServices() {
        return services;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setinstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getInstructor() {
        return instructor;
    }
    
    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

}
