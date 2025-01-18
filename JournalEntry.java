import java.util.*;

class CHECK_NAME extends Exception {
    public CHECK_NAME(String exception) {
        super(exception);
    }
}

class CHECK_JOURNAL_ID extends Exception {
    public CHECK_JOURNAL_ID(String exception) {
        super(exception);
    }
}

class ISSUE_NUMBER extends Exception {
    public ISSUE_NUMBER(String exception) {
        super(exception);
    }
}

class CHECK_ISSN extends Exception {
    public CHECK_ISSN(String exception) {
        super(exception);
    }
}

class Journal {
    private String name;
    private String journalId;
    private String issueNumber;
    private String issn;

    public Journal(String name, String journalId, String issueNumber, String issn) {
        this.name = name;
        this.journalId = journalId;
        this.issueNumber = issueNumber;
        this.issn = issn;
    }

    public String getName() {
        return name;
    }

    public String getJournalId() {
        return journalId;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public String getIssn() {
        return issn;
    }

}

public class JournalEntry {

    public static void validate(Journal entry) throws CHECK_NAME, CHECK_JOURNAL_ID, ISSUE_NUMBER, CHECK_ISSN {
        // Validate name
        if (entry.getName().length() > 30 || !entry.getName().chars().allMatch(Character::isLetter)) {
            throw new CHECK_NAME("Journal name should not exceed 30 characters and must contain only alphabets.");
        }

        // Validate journal ID
        if (!entry.getJournalId().chars().allMatch(Character::isLetterOrDigit)) {
            throw new CHECK_JOURNAL_ID("Journal ID must contain only alphanumeric characters.");
        }

        // Validate issue number
        if (entry.getIssueNumber().length() > 20) {
            throw new ISSUE_NUMBER("Issue number should not exceed 20 characters.");
        }

        // Validate ISSN
        if (entry.getIssn().length() != 9 || entry.getIssn().chars().filter(ch -> ch == '-').count() != 1) {
            throw new CHECK_ISSN("ISSN must contain exactly 9 characters, including one hyphen.");
        }
    }

    public static void main(String[] args) {
        Scanner rd = new Scanner(System.in);
        List<Journal> Journals = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for Journal " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = rd.nextLine();

            System.out.print("Journal ID: ");
            String journalId = rd.nextLine();

            System.out.print("Issue Number: ");
            String issueNumber = rd.nextLine();

            System.out.print("ISSN: ");
            String issn = rd.nextLine();

            Journal entry = new Journal(name, journalId, issueNumber, issn);

            try {
                validate(entry);
                Journals.add(entry);
                System.out.println("Journal added successfully.");
            } catch (Exception e) {
                System.out.println("Validation error: " + e);
            }
        }

        System.out.println("\nValid Journals:");
        int i = 1;
        for (Journal j : Journals) {
            System.out.println("Journal "+i);
            System.out.println("Name: " + j.getName() + "\nJournal ID: " + j.getJournalId() + "\nIssue Number: " + j.getIssueNumber() + "\nISSN: " + j.getIssn());
            i++;
        }
    }
}
