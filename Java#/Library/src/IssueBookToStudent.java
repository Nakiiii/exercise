import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssueBookToStudent implements ActionListener {

    private JFrame frame;
    private Container mainContainer;
    private JLabel title;
    private JLabel name;
    private JLabel student;
    private JTextField studentName;
    private JTextField book;
    private JButton button;
    private JPanel flow;
    private JPanel flow2;
    private JPanel grid;
    private JPanel topPanel;


    public IssueBookToStudent(){

        frame = new JFrame();
        mainContainer = frame.getContentPane();
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GRAY));
        mainContainer.setLayout(new BorderLayout(8,5));
        title = new JLabel("Issue Book To Student");
        book = new JTextField();
        name = new JLabel("Book:");
        button = new JButton("Issue Book To Student");
        flow = new JPanel();
        topPanel = new JPanel();
        grid = new JPanel();
        student = new JLabel("Student:");
        studentName = new JTextField();
        flow2 = new JPanel();


        grid.setLayout(new GridLayout(2,1));
        topPanel.setLayout(new FlowLayout(1));
        topPanel.setBorder(new LineBorder(Color.BLACK, 3));
        topPanel.add(title);
        flow.setLayout(new FlowLayout());
        flow.setBackground(Color.LIGHT_GRAY);
        flow2.setLayout(new FlowLayout());
        flow2.setBackground(Color.LIGHT_GRAY);
        grid.setBorder(new LineBorder(Color.BLACK, 3));
        name.setPreferredSize(new Dimension(50,20));
        book.setPreferredSize(new Dimension(100,20));
        student.setPreferredSize(new Dimension(50,20));
        studentName.setPreferredSize(new Dimension(100,20));
        flow.add(name);
        flow.add(book);
        flow2.add(student);
        flow2.add(studentName);
        grid.add(flow);
        grid.add(flow2);
        JPanel buttonLayout = new JPanel();
        buttonLayout.setLayout(new FlowLayout());
        buttonLayout.add(button);


        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(grid, BorderLayout.CENTER);
        mainContainer.add(buttonLayout, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Issue Book To Student");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
