import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.EnumVariant;
import com.jacob.com.Variant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessViewerService {

    private final String wmiQuery = "winmgmts:\\\\localhost\\root\\CIMV2";

    private Dispatch dispatcher;

    private ActiveXComponent activeXComponent;

    public ProcessViewerService() {
        activeXComponent = new ActiveXComponent(wmiQuery);
    }

    private BigDecimal createScaledBigDecimalBy(String dispatchName) {

        double rawDispatchedItem =
                Double.parseDouble(Dispatch.call(this.dispatcher, dispatchName).toString());

        rawDispatchedItem = rawDispatchedItem * 9.537 * Math.pow(10, -7);

        return new BigDecimal(rawDispatchedItem)
                .setScale(1, RoundingMode.HALF_UP);
    }

    public void getAllProcesses() {

        Variant vCollection = activeXComponent
                .invoke("ExecQuery", new Variant("SELECT ProcessId, Name, Priority, WorkingSetSize, VirtualSize FROM Win32_Process"));
        EnumVariant enumVariant = new EnumVariant(vCollection.toDispatch());

        while (enumVariant.hasMoreElements()) {
            dispatcher = enumVariant.nextElement().toDispatch();

            int processId = Integer.parseInt(Dispatch.call(dispatcher, "ProcessId").toString());
            String processName = Dispatch.call(dispatcher, "Name").toString();
            int processPriority = Integer.parseInt(Dispatch.call(dispatcher, "ProcessId").toString());

            ProcessEntity process = new ProcessEntity(
                    processId,
                    processName,
                    processPriority,
                    createScaledBigDecimalBy("WorkingSetSize"),
                    createScaledBigDecimalBy("VirtualSize")
            );

            System.out.println(process);

        }
    }

    public <T extends Comparator<ProcessEntity>> List<ProcessEntity> getAllProcesses(T t) {

        Variant vCollection = activeXComponent
                .invoke("ExecQuery", new Variant("SELECT ProcessId, Name, Priority, WorkingSetSize, VirtualSize FROM Win32_Process"));
        EnumVariant enumVariant = new EnumVariant(vCollection.toDispatch());

        List<ProcessEntity> processEntities = new ArrayList<>();

        while (enumVariant.hasMoreElements()) {
            dispatcher = enumVariant.nextElement().toDispatch();

            int processId = Integer.parseInt(Dispatch.call(dispatcher, "ProcessId").toString());
            String processName = Dispatch.call(dispatcher, "Name").toString();
            int processPriority = Integer.parseInt(Dispatch.call(dispatcher, "ProcessId").toString());

            ProcessEntity process = new ProcessEntity(
                    processId,
                    processName,
                    processPriority,
                    createScaledBigDecimalBy("WorkingSetSize"),
                    createScaledBigDecimalBy("VirtualSize")
            );

            processEntities.add(process);
        }

        return processEntities.stream()
                .sorted(t)
                .collect(Collectors.toList());
    }
}
