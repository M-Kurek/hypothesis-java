package pl.mk.usage;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import works.hypothesis.TestDataRule;
import works.hypothesis.strategies.ListStrategy;
import works.hypothesis.strategies.Strategies;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Maciek on 12/22/20.
 */
public class UsageExample {

    private static int cnt;

    @Rule
    public final TestDataRule data = new TestDataRule();

    @Ignore //NIY
    @Test
    public void testIsSortedAfterSorting(){
        ListStrategy<Integer> innerList = Strategies.lists(Strategies.integers());
        List<Integer> ls = data.draw(innerList);
        ls.sort(Comparator.naturalOrder());

        System.out.println(cnt + " IS list too short " + ls.size() + "\t" + (ls.size() < 2));
        cnt++;
        assertFalse(cnt + " list too short " + ls.size(), ls.size() > 1);
        assertSorted(ls);
    }

    // Utility assertion function. Doesn't use any Hypothesis functionality.
    private <T extends Comparable<T>> void assertSorted(List<T> elements){
        if(elements.isEmpty()) return;
        Iterator<T> it = elements.iterator();
        T previous = it.next();
        while(it.hasNext()){
            T current = it.next();
            assertTrue(previous.compareTo(current) <= 0);
            previous = current;
        }
    }
}
