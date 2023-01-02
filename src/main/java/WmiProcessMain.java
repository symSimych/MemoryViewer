import java.util.List;
import java.util.Scanner;

public class WmiProcessMain {
    public static void main(String[] args) {
        ProcessViewerService service = new ProcessViewerService();

        Scanner sc = new Scanner(System.in);
        while (true) {
            startMenu();
            int c = sc.nextInt();
            switch (c) {
                case 1: {
                    service.getAllProcesses();
                    break;
                }
                case 2: {
                    int sortOrder = getSortOrder();
                    List<ProcessEntity> processes =
                            service.getAllProcesses(new WorkingSetComparator(sortOrder));
                    printProcesses(processes);
                    break;
                }
                case 3: {
                    int sortOrder = getSortOrder();
                    List<ProcessEntity> processes =
                            service.getAllProcesses(new VirtualComparator(sortOrder));
                    printProcesses(processes);
                    break;
                }
                case 4: System.exit(0);
                break;
            }
        }
    }

    private static void printProcesses(List<ProcessEntity> processes) {
        for (ProcessEntity process : processes) {
            System.out.println(process);
        }
    }

    private static int getSortOrder() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select sort order:\n" +
                "1. Ascending\n" +
                "-1. Descending");
        return sc.nextInt();
    }

    private static void startMenu() {
        System.out.println("1. View the memory of all processes \n" +
                "2. Sort list by process working set\n" +
                "3. Sort list by process virtual memory\n" +
                "4. EXIT");
    }
}
