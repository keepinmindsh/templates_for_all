package bong.lines.stopwatch;

import bong.lines.functionalinterface.FuncExec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

@Slf4j
public class StopWatchFactory {
    public static <T> void execute(FuncExec<T> funcExec, T parameter, StopWatch stopWatch){
        funcExec.execute(parameter,stopWatch);
    }
}
