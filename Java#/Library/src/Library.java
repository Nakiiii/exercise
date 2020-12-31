import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Library implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JPanel topPanel;
    private JButton[] buttons;
    private JLabel label;

    public Library(){

        frame = new JFrame();
        panel = new JPanel();
        buttons = new JButton[]{
                new JButton("Add Book"),
                new JButton("Delete Book"),
                new JButton("View Book List"),
                new JButton("Issue Book to Student"),
                new JButton("Return Book")
        };
        label = new JLabel("Library of Alexandria\n Boogaloo 2.0");

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(label);
        topPanel.setBorder(new LineBorder(Color.BLACK,3));


        panel.setBorder(BorderFactory.createEmptyBorder(20,50,10,50));
        panel.setLayout(new GridLayout(0,1));
        for (JButton i : buttons){
            panel.add(i);
            i.addActionListener(this);
        }


        frame.add(topPanel,BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Library of Alexandria");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Library();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Add Book":
                new AddBook();
                break;
            case "Delete Book":
                new DeleteBook();
                break;
            case "Issue Book to Student":
                new IssueBookToStudent();
                break;
            case "View Book List":
                try {
                    new ViewBookList();
                } catch (ParserConfigurationException parserConfigurationException) {
                    parserConfigurationException.printStackTrace();
                }
                break;
            case "Return Book":
                new ReturnBook();
                break;
        }
    }
}
