package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraceTemplate {

    private LogTrace logTrace;

    public TraceTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T execute(String message, TraceCallBack<T> callBack) {

        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T result = callBack.call();
            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
