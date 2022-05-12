package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.time.LocalDateTime;

@LoggingInterceptor
@Interceptor
public class ApiLogger {
    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        System.err.println("Accessed api on " + LocalDateTime.now() + ", called method " + ctx.getMethod().getName());
        return ctx.proceed();
    }
}
