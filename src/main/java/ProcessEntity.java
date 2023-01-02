import java.math.BigDecimal;

public class ProcessEntity {

    private int processId;

    private String processName;

    private int processPriority;

    private BigDecimal workingSetSize;

    private BigDecimal virtualSetSize;

    public ProcessEntity(int processId, String processName, int processPriority, BigDecimal workingSetSize, BigDecimal virtualSetSize) {
        this.processId = processId;
        this.processName = processName;
        this.processPriority = processPriority;
        this.workingSetSize = workingSetSize;
        this.virtualSetSize = virtualSetSize;
    }

    public int getProcessId() {
        return processId;
    }

    public String getProcessName() {
        return processName;
    }

    public int getProcessPriority() {
        return processPriority;
    }

    public BigDecimal getWorkingSetSize() {
        return workingSetSize;
    }

    public BigDecimal getVirtualSetSize() {
        return virtualSetSize;
    }

    @Override
    public String toString() {
        return "[PID: " + this.processId +
                "  Name: " + this.processName +
                "  PR: " + this.processPriority +
                "  WS(MB): " + this.workingSetSize +
                "  VS(MB): " + this.virtualSetSize + "]";
    }
}
