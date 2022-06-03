package bong.lines.sample;

import java.util.List;

public class LoopCouter {
    
    private final List<Integer> count;

    public LoopCouter(List<Integer> counts) {
        this.count = counts;
    }
    
    public void count(){
        for (Integer integer : count) {
            System.out.println("integer = " + integer);
        }
    }
}
