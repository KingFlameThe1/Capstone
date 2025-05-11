package src.Machines;

import java.util.*;

public class Vulnerabilities {

    public enum VulnerabilityType {
        OPEN_HTTP_PORT,
        ROGUE_COMMUNICATION
    }

    private Set<VulnerabilityType> activeVulnerabilities;

    public Vulnerabilities() {
        activeVulnerabilities = new HashSet<>();
    }

    // Add a vulnerability
    public void add(VulnerabilityType type) {
        activeVulnerabilities.add(type);
    }

    // Remove a vulnerability
    public void remove(VulnerabilityType type) {
        activeVulnerabilities.remove(type);
    }

    // Check if a specific vulnerability exists
    public boolean has(VulnerabilityType type) {
        return activeVulnerabilities.contains(type);
    }

    // Check if the computer has any vulnerabilities
    public boolean hasAny() {
        return !activeVulnerabilities.isEmpty();
    }

    // Return all current vulnerabilities
    public Set<VulnerabilityType> getAll() {
        return Collections.unmodifiableSet(activeVulnerabilities);
    }

    // Print all vulnerabilities in a readable format
    @Override
    public String toString() {
        if (activeVulnerabilities.isEmpty()) {
            return "No known vulnerabilities.";
        }
        StringBuilder sb = new StringBuilder("Detected Vulnerabilities:\n");
        for (VulnerabilityType type : activeVulnerabilities) {
            switch (type) {
                case OPEN_HTTP_PORT -> sb.append("- Open HTTP Port (port 80 accessible)\n");
                case ROGUE_COMMUNICATION -> sb.append("- Rogue Communication (unauthorized external traffic detected)\n");
            }
        }
        return sb.toString();
    }
}



/*import java.util.ArrayList;

public class Vulnerabilities {
    //private ArrayList<String> vulns = new ArrayList<String>(); 
    String[] vulns = {"open http","rogue coms"};
    static int numVulns;

    public Vulnerabilities(int num){
        
    }
}
*/