package bong.lines.functionalinterface;

import org.springframework.util.StopWatch;

@FunctionalInterface
public interface FuncExec<T> {
    public void execute(T t, StopWatch stopWatch);
}
