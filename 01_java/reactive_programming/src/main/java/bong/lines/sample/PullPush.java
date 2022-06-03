package bong.lines.sample;

import java.util.Arrays;
import java.util.List;

public class PullPush {
    public static void main(String[] args) {

        // 가져오다 ( Pull )
        // 이건 내가 직업 하나씩 돌려가면 다른사람과 계속 일해야해.
        // Thread -> 해당 스레드는 쉴틈이 없음.
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        for (Integer integer : integers) {
            System.out.println("integer = " + integer);
        }

        // Thread A - Worker Thread
        //  -----(5초)

        // 넣다 ( Push )
        // 내가 다른 사람에게 역할을 주고 그결과가 오는 동안 뭐든지 다른걸 할 수 있어
        // Thread -> 여유가 있음. 이 Thread는 일을 던져주고 그이후 결과만 알면됨.
        LoopCouter loopCounter = new LoopCouter(integers);
        loopCounter.count();

        // Thread B - Event Loop ( Worker Thread 5 )
        // -(0.1)   -(0.1)
        // Thread Q
        // -----(5초)
    }
}
