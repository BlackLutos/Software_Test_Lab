import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{8,3,4},new int[]{3,4,8}),
                Arguments.of(new int[]{-6,-1,-8,5},new int[]{-8,-6,-1,5}),
                Arguments.of(new int[]{7,-2,-4,-1,8},new int[]{-4,-2,-1,7,8}),
                Arguments.of(new int[]{3,1,-6,0,9,4},new int[]{-6,0,1,3,4,9}),
                Arguments.of(new int[]{2,7,3,-1,-5},new int[]{-5,-1,2,3,7})
        );
    }
    @ParameterizedTest(name="#(index) - Test with Arugment={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        //int index = 0;
        //Integer s;
        int[] result = new int[random_array.length];


        for(Integer value:random_array){
            test.add(value);
        }
        for(int i = 0;i < random_array.length;i++){
            result[i] = test.poll();
        }
        //System.out.println(Arrays.toString(result));
        //System.out.println(Arrays.toString(correct_array));

        assertArrayEquals(correct_array,result);

    }
    @Test
    public void whenExceptionThrown_testNullPointerException(){
        Exception exception  = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>();
            test.add(null);
        });
    }
    @Test
    public void whenExceptionThrown_testIllegalArgumentException(){
        Exception exception  = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(-1);
        });

    }
    @Test
    public void whenExceptionThrown_testConcurrentModificationException(){
        Exception exception  = assertThrows(ConcurrentModificationException.class, () -> {
            String[] sa = {">ff<", "> f<", ">f <", ">FF<"};
            PriorityQueue<String> pq = new PriorityQueue<String>();
            for (String str : sa) {
                pq.offer(str);
            }
            System.out.println(pq);
            for (String str : pq) {
                System.out.print(pq.poll() + " ");
            }
        });

    }

}
