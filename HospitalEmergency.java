import java.util.*;

// Patient class
class Patient {
    String name;
    int severity;
    int arrivalTime;

    Patient(String name, int severity, int arrivalTime) {
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }
}

// Comparator for Priority Queue
class PatientComparator implements Comparator<Patient> {
    public int compare(Patient p1, Patient p2) {
        // Higher severity = higher priority
        if (p2.severity != p1.severity) {
            return p2.severity - p1.severity;
        }
        // If same severity → earlier arrival first
        return p1.arrivalTime - p2.arrivalTime;
    }
}

public class HospitalEmergency {

    // Heap Sort based on severity
    public static void heapSort(List<Patient> list) {
        PriorityQueue<Patient> pq = new PriorityQueue<>(new PatientComparator());

        pq.addAll(list);

        list.clear();
        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Patient> pq = new PriorityQueue<>(new PatientComparator());
        List<Patient> allPatients = new ArrayList<>();

        int choice;
        int time = 0;

        do {
            System.out.println("\n--- Hospital Emergency System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Treat Patient");
            System.out.println("3. Display Queue");
            System.out.println("4. Heap Sort Patients");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Patient Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Severity (1-10): ");
                    int severity = sc.nextInt();

                    Patient p = new Patient(name, severity, time++);
                    pq.add(p);
                    allPatients.add(p);

                    System.out.println("Patient added!");
                    break;

                case 2:
                    if (pq.isEmpty()) {
                        System.out.println("No patients to treat.");
                    } else {
                        Patient treated = pq.poll();
                        System.out.println("Treating: " + treated.name +
                                " (Severity: " + treated.severity + ")");
                    }
                    break;

                case 3:
                    if (pq.isEmpty()) {
                        System.out.println("No patients in queue.");
                    } else {
                        System.out.println("\nCurrent Queue:");
                        for (Patient pat : pq) {
                            System.out.println(pat.name +
                                    " | Severity: " + pat.severity +
                                    " | Arrival: " + pat.arrivalTime);
                        }
                    }
                    break;

                case 4:
                    heapSort(allPatients);
                    System.out.println("\nSorted Patients (Heap Sort):");
                    for (Patient pat : allPatients) {
                        System.out.println(pat.name +
                                " | Severity: " + pat.severity +
                                " | Arrival: " + pat.arrivalTime);
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}