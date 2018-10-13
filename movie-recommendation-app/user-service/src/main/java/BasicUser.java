import common.User;

public class BasicUser implements User {
    private int age;
    public BasicUser(int age) {
        this.age = age;
    }
    @Override
    public int getAge() {
        return age;
    }
}
