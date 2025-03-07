/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package STA;

/**
 *
 * @author Hp
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Electronic_Voting_Machine {
    static final String STUDENTS_FILE = "students.txt"; // students data txt file
    static final String VOTING_DATA_FILE = "voting_data.txt";
    static final String SELECTOR_NAME = "Majharul islam"; // Selector's name
    static final String SELECTOR_ID = "abc123"; // Selector's ID

    static JFrame login_Frame;
    static JTextField name, roll, id;
    static JButton login_Button;

    static JFrame candidate_list;
    static String[] candidates = {
            "Candidate 1",
            "Candidate 2",
            "Candidate 3"
    };
    static JRadioButton[] candidate_buttons;
    static JButton vote_button;

    static String loggedInName;
    static Set<String> voted_Students = new HashSet<>(); // Set to store voted students

    public static void main(String[] args) {
        // Write student information to students.txt
        write_Student_Info_To_File();

        // Determine mode: Student or Selector
        String mode = getMode();
        if (mode.equalsIgnoreCase("student")) {
            // Create login frame for students
            Student_Login_Frame();
        } else if (mode.equalsIgnoreCase("selector")) {
            // Selector login and show results
            if (Selector_Login()) {
                countVotes();
            } else {
                System.out.println("Selector login failed. Exiting program.");
                System.exit(1);
            }
        } else {
            System.out.println("Invalid mode. Exiting program.");
            System.exit(1);
        }
    }

    private static String getMode() {
        String[] options = {" Student ", " Selector "};
        int choice = JOptionPane.showOptionDialog(null, "Select Mode", "Mode Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            return "student";
        } else if (choice == 1) {
            return "selector";
        } else {
            return "invalid";
        }
    }

    private static boolean Selector_Login() {
        // Create selector login
        JFrame Selector_Login_Frame = new JFrame("Selector Login");
        Selector_Login_Frame.setSize(500, 200);
        Selector_Login_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Selector_Login_Frame.setLayout(new GridLayout(3, 2));
        Selector_Login_Frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        JLabel nameLabel = new JLabel(" Name:");
        JLabel idLabel = new JLabel(" Password:");

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 153, 204)); // Blue background
        loginButton.setForeground(Color.WHITE); // White text color

        Selector_Login_Frame.add(nameLabel);
        Selector_Login_Frame.add(nameField);
        Selector_Login_Frame.add(idLabel);
        Selector_Login_Frame.add(idField);
        Selector_Login_Frame.add(new JLabel());
        Selector_Login_Frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String id = idField.getText();

                if (name.equals(SELECTOR_NAME) && id.equals(SELECTOR_ID)) {
                    JOptionPane.showMessageDialog(Selector_Login_Frame, "Login successful.");
                    Selector_Login_Frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(Selector_Login_Frame, "Invalid Information.Please try again.");
                }
            }
        });

        Selector_Login_Frame.setVisible(true);

        while (Selector_Login_Frame.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return !Selector_Login_Frame.isVisible();
    }

    private static void write_Student_Info_To_File() {
        // Array of student information
        String[] students = {
                "jahid,1,117850",
                "emon,3,117877",
                "maruf,4,117878",
                "alamin,6,117890",
                "fahad,8,117911",
                "mehedi,10,117918",
                "shihab,11,117925",
                "raihan,12,117930",
                "mowme,13,117941",
                "jannatul,14,117943",
                "jim,16,117950",
                "sahil,18,117956",
                "ulfat,19,117974",
                "momin,21,117981",
                "faria,23,117984",
                "anik,24,117986",
                "rasel,25,117987",
                "mitu,26,117994",
                "ferdous,27,117995",
                "alvi,29,117998",
                "soumik,34,118024",
                "joy,35,118029",
                "shahnewaz,37,118047",
                "ruku,38,118048",
                "iktear,39,118056",
                "alim,40,118073",
                "hani,41,118079",
                "mukti,43,118082",
                "nahid,44,118083",
                "soaus,45,118093",
                "tomal,46,115945",
                "arifa,49,116861",
                "samiul,50,115809",
                "pranto,51,123338"
        };

        // Path to the students.txt file
        String studentsFile = "students.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentsFile))) {
            for (String student : students) {
                writer.write(student);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void Student_Login_Frame() {
        login_Frame = new JFrame("Login");
        login_Frame.setSize(500, 300);
        login_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_Frame.setLayout(new GridLayout(4, 2));
        login_Frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        JLabel nameLabel = new JLabel(" Name(small letter):");
        JLabel rollLabel = new JLabel(" Roll:");
        JLabel idLabel = new JLabel(" Student ID:");

        JTextField nameField = new JTextField();
        JTextField rollField = new JTextField();
        JTextField idField = new JTextField();

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 153, 204)); // Blue background
        loginButton.setForeground(Color.WHITE); // White text color

        login_Frame.add(nameLabel);
        login_Frame.add(nameField);
        login_Frame.add(rollLabel);
        login_Frame.add(rollField);
        login_Frame.add(idLabel);
        login_Frame.add(idField);
        login_Frame.add(new JLabel());
        login_Frame.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String roll = rollField.getText();
                String id = idField.getText();

                try {
                    if (isValidStudent(name, roll, id)) {
                        loggedInName = name;
                        if (!voted_Students.contains(loggedInName)) {
                            createCandidateListFrame();
                        } else {
                            JOptionPane.showMessageDialog(login_Frame, "Sorry, you have already voted.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(login_Frame, "Invalid information. Please try again.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(login_Frame, "Error reading student information. Try again later.");
                }
            }
        });

        login_Frame.setVisible(true);
    }

    private static boolean isValidStudent(String name, String roll, String id) throws IOException {
        String studentInfo = name.toLowerCase() + "," + roll.toLowerCase() + "," + id.toLowerCase();
        BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.toLowerCase().equals(studentInfo)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    private static void createCandidateListFrame() {
        candidate_list = new JFrame("Candidate List");
        candidate_list.setSize(400, 300);
        candidate_list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        candidate_list.setLayout(new GridLayout(candidates.length + 1, 1));
        candidate_list.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        candidate_buttons = new JRadioButton[candidates.length];
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < candidates.length; i++) {
            candidate_buttons[i] = new JRadioButton(candidates[i]);
            candidate_buttons[i].setBackground(new Color(240, 240, 240)); // Light gray background
            group.add(candidate_buttons[i]);
            candidate_list.add(candidate_buttons[i]);
        }

        vote_button = new JButton("Vote");
        vote_button.setBackground(new Color(0, 153, 204)); // Blue background
        vote_button.setForeground(Color.WHITE); // White text color
        candidate_list.add(vote_button);

        vote_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCandidate = getSelectedCandidate();
                if (selectedCandidate != null && !selectedCandidate.isEmpty()) {
                    vote(selectedCandidate);
                    JOptionPane.showMessageDialog(candidate_list, "Thank you for voting!");
                    candidate_list.dispose();
                } else {
                    JOptionPane.showMessageDialog(candidate_list, "Please select a candidate to vote.");
                }
            }
        });

        candidate_list.setVisible(true);
    }

    private static String getSelectedCandidate() {
        for (int i = 0; i < candidate_buttons.length; i++) {
            if (candidate_buttons[i].isSelected()) {
                return candidates[i];
            }
        }
        return null;
    }

    private static void vote(String candidate) {
        voted_Students.add(loggedInName);

        // Append the vote to the voting data file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(VOTING_DATA_FILE, true))) {
            writer.write(loggedInName + ", " + candidate);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void countVotes() {
        // Path to the voting_data.txt file
        String votingDataFile = "voting_data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(votingDataFile))) {
            String line;
            Map<String, Integer> voteCounts = new HashMap<>(); // Map to store votes for each candidate
            Map<String, Double> candidateCGPAs = new HashMap<>(); // Map to store CGPAs for each candidate

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String candidate = parts[1].trim(); // Candidate's name is in the second part
                    voteCounts.put(candidate, voteCounts.getOrDefault(candidate, 0) + 1);
                }
            }

            // Check if any candidate received no votes
            for (String candidate : candidates) {
                voteCounts.putIfAbsent(candidate, 0);
            }

            // Find the maximum number of votes
            int maxVotes = Collections.max(voteCounts.values());

            // Check if there is a tie for the maximum votes
            boolean tie = false;
            for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
                int votes = entry.getValue();
                if (votes == maxVotes) {
                    tie = true;
                    break; // Found a tie for maximum votes, no need to continue
                }
            }

            if (tie) {
                // If there is a tie for the maximum votes, compare CGPAs
                for (String candidate : candidates) {
                    double cgpa = getCGPAForCandidate(candidate); // Get CGPA for the candidate
                    if (voteCounts.get(candidate) == maxVotes) {
                        candidateCGPAs.put(candidate, cgpa);
                    }
                }

                // Determine the winner based on CGPA among the tied candidates
                String winner = "";
                double maxCGPA = 0.0;
                for (Map.Entry<String, Double> entry : candidateCGPAs.entrySet()) {
                    String candidate = entry.getKey();
                    double cgpa = entry.getValue();
                    if (cgpa > maxCGPA) {
                        maxCGPA = cgpa;
                        winner = candidate;
                    }
                }
                
                StringBuilder result = new StringBuilder("<html><body style='width: 300px; padding: 20px;'>");
                result.append("<h2>Voting Results </h2>");
                for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
                    String candidate = entry.getKey();
                    int votes = entry.getValue();
                    result.append("<p>").append(candidate).append(": ").append(votes).append(" votes</p>");
                }
                result.append("<h3>Winner </h3>");
                result.append("<p>").append(winner).append("</p></body></html>");
                displayResult(result.toString());
            } 
            else {
                // Determine the winner based on votes
                String winner = "";
                for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
                    String candidate = entry.getKey();
                    int votes = entry.getValue();
                    if (votes == maxVotes) {
                        winner = candidate;
                        break;
                    }
                }

                // Display results without CGPA comparison
                StringBuilder result = new StringBuilder("<html><body style='width: 300px; padding: 20px;'>");
                result.append("<h2>Voting Results</h2>");
                for (Map.Entry<String, Integer> entry : voteCounts.entrySet()) {
                    String candidate = entry.getKey();
                    int votes = entry.getValue();
                    result.append("<p>").append(candidate).append(": ").append(votes).append(" votes</p>");
                }
                result.append("<h3>Winner</h3>");
                result.append("<p>").append(winner).append(" with ").append(maxVotes).append(" votes</p></body></html>");
                displayResult(result.toString());
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void displayResult(String result) {
        JFrame resultFrame = new JFrame("Election Results");
        resultFrame.setSize(400, 300);
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel(result, SwingConstants.CENTER);
        resultLabel.setForeground(Color.BLACK); // Black text color
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(resultLabel);
        resultFrame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.setBackground(new Color(0, 153, 204)); // Blue background
        closeButton.setForeground(Color.WHITE); // White text color
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultFrame.dispose();
            }
        });
        resultFrame.add(closeButton, BorderLayout.SOUTH);

        resultFrame.setVisible(true);
    }

    private static double getCGPAForCandidate(String candidate) {
        
        Map<String, Double> candidateCGPAs = new HashMap<>();
        candidateCGPAs.put("Candidate 1", 3.75);
        candidateCGPAs.put("Candidate 2", 3.50);
        candidateCGPAs.put("Candidate 3", 3.80);

        return candidateCGPAs.getOrDefault(candidate, 0.0); // Default CGPA is 0.0 if not found
    }
}
