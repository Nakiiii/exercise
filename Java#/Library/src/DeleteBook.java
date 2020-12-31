import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBook implements ActionListener {

    private JFrame frame;
    private Container mainContainer;
    private JLabel title;
    private JLabel name;
    private JTextField book;
    private JButton button;
    private JPanel flow;
    private JPanel topPanel;


    public DeleteBook(){

        createGUI();

        deleteBook();

    }

    private void deleteBook() {



    }

    private void createGUI() {
        frame = new JFrame();
        mainContainer = frame.getContentPane();
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GRAY));
        mainContainer.setLayout(new BorderLayout(8,5));
        title = new JLabel("Delete Book");
        book = new JTextField();
        name = new JLabel("Book:");
        button = new JButton("Delete Book");
        flow = new JPanel();
        topPanel = new JPanel();


        topPanel.setLayout(new FlowLayout(1));
        topPanel.setBorder(new LineBorder(Color.BLACK, 3));
        topPanel.add(title);
        flow.setLayout(new FlowLayout());
        flow.setBorder(new LineBorder(Color.BLACK, 3));
        flow.setBackground(Color.LIGHT_GRAY);
        name.setPreferredSize(new Dimension(50,20));
        book.setPreferredSize(new Dimension(100,20));
        flow.add(name);
        flow.add(book);
        JPanel buttonLayout = new JPanel();
        buttonLayout.setLayout(new FlowLayout());
        buttonLayout.add(button);


        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(flow, BorderLayout.CENTER);
        mainContainer.add(buttonLayout, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Delete Book");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
