package bong.comm.operation;

public interface Command<ReturnR> {
    ReturnR process();
}
