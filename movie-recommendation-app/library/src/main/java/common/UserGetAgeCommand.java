package common;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class UserGetAgeCommand extends HystrixCommand<Integer> {

    private User user;

    public UserGetAgeCommand(User user) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("user"))
                .andCommandPropertiesDefaults(
                // we default to a 600ms timeout for primary
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(100)));
        this.user = user;
    }

    @Override
    protected Integer run() throws Exception {
        return user.getAge();
    }
}
