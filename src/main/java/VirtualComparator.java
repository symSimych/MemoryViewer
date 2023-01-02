import java.util.Comparator;

public class VirtualComparator implements Comparator<ProcessEntity> {

    private int compareValue;

    public VirtualComparator(int compareValue) {

        if (compareValue >= -1 && compareValue <= 1) {
            this.compareValue = compareValue;
        } else {
            throw new ArithmeticException("compareValue must be from [-1 to 1]!");
        }
    }

    @Override
    public int compare(ProcessEntity o1, ProcessEntity o2) {
        return compareValue * o1.getVirtualSetSize().compareTo(o2.getVirtualSetSize());
    }
}
