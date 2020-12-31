import org.w3c.dom.*;
import org.xml.sax.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ReturnBook implements ActionListener {

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


    public ReturnBook(){

        frame = new JFrame();
        mainContainer = frame.getContentPane();
        frame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.GRAY));
        mainContainer.setLayout(new BorderLayout(8,5));
        title = new JLabel("Return Book");
        book = new JTextField();
        name = new JLabel("Book:");
        button = new JButton("Return book");
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
        button.addActionListener(this::actionPerformed);


        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(grid, BorderLayout.CENTER);
        mainContainer.add(buttonLayout, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Return Book");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("books.xml");

            updateNode(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);

        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch (SAXException saxException) {
            saxException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (TransformerConfigurationException transformerConfigurationException) {
            transformerConfigurationException.printStackTrace();
        } catch (TransformerException transformerException) {
            transformerException.printStackTrace();
        }

    }

    private void updateNode(Document doc) {
        NodeList bookNodeList = doc.getElementsByTagName("book");
        Element bk = null;
        for (int i = 0; i < bookNodeList.getLength(); i++) {
            bk = (Element) bookNodeList.item(i);
            if (bk.getAttribute("id").equals(book.getText())) {
                Node storage = bk.getElementsByTagName("storage").item(0).getFirstChild();
                String value = Integer.toString(Integer.parseInt(storage.getTextContent()) + 1);
                storage.setNodeValue(value);
                NodeList names = bk.getElementsByTagName("students").item(0).getChildNodes();
                for (int j = 1; j < names.getLength(); j += 2) {
                    if (names.item(j).getTextContent().equals(studentName.getText())){
                        bk.removeChild(names.item(j));
                    }
                }
            }
        }
    }
}
