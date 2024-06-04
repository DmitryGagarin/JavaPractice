package microservice.zuul_api_gateway;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class LoggingFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public boolean shouldFilter() {
        return true; // Change this to true to enable the filter
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri -> {}", request, request.getRequestURI());
        return null;
    }

    @Override
    public String filterType() {
        return "pre"; // Intercept all the requests before execution
    }

    @Override
    public int filterOrder() {
        return 1; // The order in which this filter is to be run, relative to other filters
    }
}
