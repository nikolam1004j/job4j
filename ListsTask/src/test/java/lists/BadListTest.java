package lists;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BadListTest {

    @Test
    public void testCycleIfHasCycle() {
        Node<Integer> node0 = new Node<>(62);
        Node<Integer> node1 = new Node<>(40);
        Node<Integer> node2 = new Node<>(41);
        Node<Integer> node3 = new Node<>(0);
        Node<Integer> node4 = new Node<>(5);
        Node<Integer> node5 = new Node<>(18);
        Node<Integer> node6 = new Node<>(36);
        Node<Integer> node7 = new Node<>(71);
        Node<Integer> node8 = new Node<>(8);
        Node<Integer> node9 = new Node<>(59);
        Node<Integer> node10 = new Node<>(94);
        Node<Integer> node11 = new Node<>(11);
        Node<Integer> node12 = new Node<>(57);
        Node<Integer> node13 = new Node<>(63);
        Node<Integer> node14 = new Node<>(34);
        Node<Integer> node15 = new Node<>(25);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = node5;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(node0), is(true));
    }

    @Test
    public void testCycleIfHasNoCycle() {
        Node<Integer> node0 = new Node<>(62);
        Node<Integer> node1 = new Node<>(40);
        Node<Integer> node2 = new Node<>(41);
        Node<Integer> node3 = new Node<>(0);
        Node<Integer> node4 = new Node<>(5);
        Node<Integer> node5 = new Node<>(18);
        Node<Integer> node6 = new Node<>(36);
        Node<Integer> node7 = new Node<>(71);
        Node<Integer> node8 = new Node<>(8);
        Node<Integer> node9 = new Node<>(59);
        Node<Integer> node10 = new Node<>(94);
        Node<Integer> node11 = new Node<>(11);
        Node<Integer> node12 = new Node<>(57);
        Node<Integer> node13 = new Node<>(63);
        Node<Integer> node14 = new Node<>(34);
        Node<Integer> node15 = new Node<>(25);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        node15.next = null;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(node0), is(false));
    }

    @Test
    public void testFromExampleJ4j() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(true));
    }

    @Test
    public void testTwoElementsCycle() {
        Node first = new Node(1);
        Node two = new Node(2);
        first.next = two;
        two.next = first;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(true));
    }

    @Test
    public void testTwoElementsHasNoCycle() {
        Node first = new Node(1);
        Node two = new Node(2);
        first.next = two;
        two.next = null;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(false));
    }

    @Test
    public void testThreeElementsCycle() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        first.next = two;
        two.next = three;
        three.next = first;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(true));
    }

    @Test
    public void testThreeElementsHasNoCycle() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        first.next = two;
        two.next = three;
        three.next = null;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(false));
    }

    @Test
    public void testOneElemCycle() {
        Node first = new Node(1);
        first.next = first;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(true));
    }

    @Test
    public void testOneElemHasNoCycle() {
        Node first = new Node(1);
        first.next = null;

        BadList<Integer> badList = new BadList<>();
        assertThat(badList.hasCycle(first), is(false));
    }

}