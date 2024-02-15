package ibrahim_API.utilities.RandomDataGenerator;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class RandomData {

    private static Faker faker = new Faker(Locale.UK);
    private static Random random = new Random();


    public static String getRandomDataFor(DataTypeName dataType) {
        Object data;
        switch (dataType) {
            case FIRSTNAME:
                data = faker.name().firstName();
                break;
            case LASTNAME:
                data = faker.name().lastName();
                break;
            case FULL_NAME:
                data = faker.name().fullName();
                break;
            case EMAIL:
                data = faker.internet().emailAddress();
                break;
            case GENDER:
                data = randomGender();
                break;
            case SKILLS:
                data = randomSkills();
                break;
            case DATE_OF_BIRTH:
                data = dateOfBirth();
                break;
            case COUNTRY:
                data = faker.country().name();
                break;
            case PASSWORD:
                data = faker.internet().password();
                break;
            case RANDOM_STRING:
                data = faker.lorem().sentence();
                break;
            case PHONE_NUMBER:
                data = faker.phoneNumber().cellPhone();
                break;
            default:
                throw new IllegalArgumentException("Invalid data type: " + dataType);
        }
        return data.toString();
    }

    private static String randomGender() {
        String[] genders = {
                "Male",
                "Female",
                "Other"
        };
        return genders[random.nextInt(genders.length)];
    }


    private static String randomSkills() {
        String[] tools = {
                "Eclipse",
                "IntelliJ IDEA",
                "NetBeans",
                "Version Control Systems",
                "Git",
                "SVN (Subversion)",
                "Build Automation Tools",
                "Maven",
                "Gradle",
                "Testing Frameworks",
                "JUnit",
                "TestNG",
                "Mockito (Mocking framework)",
                "Continuous Integration/Continuous Deployment (CI/CD) Tools",
                "Jenkins",
                "Travis CI",
                "CircleCI",
                "Code Quality and Analysis Tools",
                "SonarQube",
                "FindBugs",
                "Checkstyle",
                "Collaboration and Communication Tools",
                "Slack",
                "JIRA",
                "Confluence"
        };
        return tools[random.nextInt(tools.length)];
    }

    private static String dateOfBirth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dtb = dateFormat.format(faker.date().birthday(18, 60));
        return dtb;
    }


}

