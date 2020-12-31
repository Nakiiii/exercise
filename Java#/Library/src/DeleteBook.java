import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
        button.addActionListener(this::actionPerformed);

    }

    private void deleteBook(Document doc) {
        Node root = doc.getDocumentElement();
        NodeList books = doc.getElementsByTagName("book");
        Element bookElement = null;
        for (int i = 0; i < books.getLength(); i++) {
            bookElement = (Element) books.item(i);
            if (bookElement.getAttribute("id").equals(book.getText())){
                root.removeChild(bookElement);
            }
        }


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
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("books.xml");

            deleteBook(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("books.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);

            frame.dispose();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (TransformerConfigurationException transformerConfigurationException) {
            transformerConfigurationException.printStackTrace();
        } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch (SAXException saxException) {
            saxException.printStackTrace();
        } catch (TransformerException transformerException) {
            transformerException.printStackTrace();
        }
    }
}
