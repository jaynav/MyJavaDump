import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class StudentMaintenanceApp
{
    public static void main(String[] args)
    {
        JFrame frame = new StudentMaintenanceFrame();
        frame.setVisible(true);
    }
}

class StudentMaintenanceFrame extends JFrame
{
    public StudentMaintenanceFrame()
    {
        setTitle("StudentCalulator");
        setResizable(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new StudentMaintenancePanel());
        this.pack();
        centerWindow(this);
    }

    private void centerWindow(Window w)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension d = toolkit.getScreenSize();
        setLocation((d.width-w.getWidth())/2, (d.height-w.getHeight())/2);
    }
}

class StudentMaintenancePanel extends JPanel
{
   // ProductDAO productDAO;
    //ArrayList<Product> products;
   // Product newProduct = null;

    StudentSelectorPanel selectorPanel;
    StudentDisplayPanel studentPanel;
    ProductButtonPanel buttonPanel;

    public StudentMaintenancePanel()
    {
        // fill the products ArrayList
      //  productDAO = DAOFactory.getProductDAO();
      //  products = productDAO.getProducts();

        // add the panels
        setLayout(new GridBagLayout());
        selectorPanel = new StudentSelectorPanel();
        studentPanel = new StudentDisplayPanel();
        buttonPanel = new ProductButtonPanel();
        add(selectorPanel, getConstraints(0,0,1,1, GridBagConstraints.EAST));
        add(studentPanel, getConstraints(0,1,1,1, GridBagConstraints.EAST));
        add(buttonPanel, getConstraints(0,2,1,1, GridBagConstraints.EAST));

        // set the initial product to be displayed
        //studentPanel.showProduct(products.get(0));
        //selectorPanel.selectProduct(products.get(0));
    }

    // a method for setting grid bag constraints
    private GridBagConstraints getConstraints(int gridx, int gridy,
    int gridwidth, int gridheight, int anchor)
    {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        return c;
    }

    class StudentSelectorPanel extends JPanel implements ActionListener
    {
        public JComboBox    productComboBox;
        private JLabel      productLabel;

        boolean filling = false;            // used to indicate the combo box is being filled

        public StudentSelectorPanel()
        {
            // set panel layout
            setLayout(new FlowLayout(FlowLayout.LEFT));

            // product label
            productLabel = new JLabel("Select Product:");
            add(productLabel);

            // product combo box
            productComboBox = new JComboBox();
            //fillComboBox(student);
            productComboBox.addActionListener(this);
            add(productComboBox);
        }

        public void actionPerformed(ActionEvent e)
        {
            if (!filling)
            {
              //  Student p = (Student)productComboBox.getSelectedItem();
              //  studentPanel.showProduct(p);
            }
        }

      //  public void fillComboBox(ArrayList<Student> a)
        //{
          //  filling = true;
            //productComboBox.removeAllItems();
           // for (Student p : a)
         //       productComboBox.addItem(p);
            //filling = false;
       // }

        //public void selectStudent(Student p)
        //{
          //  productComboBox.setSelectedItem(p);
        //}

       // public Product getCurrentStudent()
        //{
          
        //}
    }

    class StudentDisplayPanel extends JPanel
    {
        public JTextField   idTextField,
                            nameTextField,
                            dobTextField, sexTextField, emailTextField, urlTextField;
        
        private JLabel      idLabel,
                            nameLabel,
                            dobLabel, sexLabel, emailLabel, urlLabel;

        public StudentDisplayPanel()
        {
            // set panel layout
            setLayout(new GridBagLayout());

            // code label
            idLabel = new JLabel("id:");
            nameLabel = new JLabel("name:");
            dobLabel = new JLabel("dob:");
            sexLabel = new JLabel("sex:");
            emailLabel = new JLabel("email:");
            urlLabel = new JLabel("URL:");
            
            idTextField = new JTextField(15);
            nameTextField = new JTextField(15);
            dobTextField = new JTextField(15);
            sexTextField = new JTextField(15);
            emailTextField = new JTextField(15);
            urlTextField = new JTextField(15);
            idTextField.setEditable(false);            
            nameTextField.setEditable(false);
            dobTextField.setEditable(false);
            sexTextField.setEditable(false);
            emailTextField.setEditable(false);
            
            idTextField.setFocusable(false);
            nameTextField.setFocusable(false);
            dobTextField.setFocusable(false);
            sexTextField.setFocusable(false);
            emailTextField.setFocusable(false);
            
            idTextField.addFocusListener(new AutoSelect());// think about this
            nameTextField.addFocusListener(new AutoSelect());
            dobTextField.addFocusListener(new AutoSelect());
            sexTextField.addFocusListener(new AutoSelect());
            emailTextField.addFocusListener(new AutoSelect());
           
            
            // adding to the panel
            add(dobLabel, getConstraints(0,2,1,1, GridBagConstraints.EAST));
           	add(idLabel, getConstraints(0,0,1,1, GridBagConstraints.EAST));
		    add(nameLabel, getConstraints(0,1,1,1, GridBagConstraints.EAST));
		    add(sexLabel, getConstraints(0,3,1,1, GridBagConstraints.EAST));
		    add(emailLabel, getConstraints(0,4,1,1, GridBagConstraints.EAST));
		    add(urlLabel, getConstraints(0,5,1,1, GridBagConstraints.EAST));
		    
		    add(idTextField, getConstraints(1,0,1,1, GridBagConstraints.WEST));
		    add(nameTextField, getConstraints(1,1,1,1, GridBagConstraints.WEST));
		    add(dobTextField, getConstraints(1,2,1,1, GridBagConstraints.WEST));
		    add(sexTextField, getConstraints(1,3,1,1, GridBagConstraints.WEST));
		    add(emailTextField, getConstraints(1,4,1,1, GridBagConstraints.WEST));
		    add(urlTextField, getConstraints(1,5,1,1, GridBagConstraints.EAST));   
             }

      //  public void showStudent(Student p)
        //{
          //  idTextField.setText(p.getId());
            //nameTextField.setText(p.getName());
           // dobTextField.setText(p.getDob());
        // sexTextField.setText(p.getSex));
//       
        //}

        public void clearFields()
        {
            idTextField.setText("");
            nameTextField.setText("");
            dobTextField.setText("");
            sexTextField.setText("");
            //
        }

        // return a new Product object with the data in the text fields
       // public Student getStudent()
       // 

        public void setAddMode()
        {
            idTextField.setEditable(true);
            idTextField.setFocusable(true);
            idTextField.requestFocusInWindow();
            nameTextField.setEditable(true);
            nameTextField.setFocusable(true);
            dobTextField.setEditable(true);
            dobTextField.setFocusable(true);
            sexTextField.setEditable(true);
            sexTextField.setFocusable(true);
        }

        public void setEditMode()
        {
        	idTextField.setEditable(true);
            idTextField.setFocusable(true);
          	nameTextField.setEditable(true);
            nameTextField.setFocusable(true);
            nameTextField.requestFocusInWindow();
            dobTextField.setEditable(true);
            dobTextField.setFocusable(true);
            sexTextField.setEditable(true);
            sexTextField.setFocusable(true);
        }

        public void setDisplayMode()
        {
            idTextField.setEditable(false);
            idTextField.setFocusable(false);
            nameTextField.setEditable(false);
            nameTextField.setFocusable(false);
            dobTextField.setEditable(false);
            dobTextField.setFocusable(false);
            sexTextField.setEditable(false);
            sexTextField.setFocusable(false);
        }
    }

    class ProductButtonPanel extends JPanel
    {
        public JButton addButton, editButton,
                       deleteButton,
                       acceptButton, cancelButton,
                       exitButton, calculateButton;

        public JTextField gradeTextField, averageGradeTextField;
        public JLabel gradeLabel, averageGradeLabel;
        
        public ProductButtonPanel()
        {
        	setLayout(new GridBagLayout());
            // create maintenance button panel
        	
        	

            // making buttons
            addButton = new JButton("   Add     ");
            editButton = new JButton("     Edit   ");
            deleteButton = new JButton("   Delete     ");
            acceptButton = new JButton("Accept");
            cancelButton = new JButton("Cancel");
            exitButton = new JButton("Exit");
            calculateButton = new JButton("Calculate");
            
            gradeLabel = new JLabel("add student Grade");
            averageGradeLabel= new JLabel("average student Grade");
            gradeTextField = new JTextField(10);
		    averageGradeTextField = new JTextField(10);
		    
            
            addButton.addActionListener(new AddListener());
            editButton.addActionListener(new EditListener());
            deleteButton.addActionListener(new DeleteListener());
            exitButton.addActionListener(new ExitListener());
            calculateButton.addActionListener(new CalculateListener());
                        
            acceptButton.setEnabled(false);
            acceptButton.addActionListener(new AcceptListener());
            cancelButton.setEnabled(false);
            cancelButton.addActionListener(new CancelListener());
            
            add(editButton, getConstraints(5,0,1,1, GridBagConstraints.WEST));
            add(deleteButton, getConstraints(4,1,1,1, GridBagConstraints.EAST));
            add(addButton, getConstraints(5,1,1,1, GridBagConstraints.WEST));
            
            add(acceptButton, getConstraints(4,2,1,1, GridBagConstraints.EAST));          
            add(cancelButton, getConstraints(5,2,1,1, GridBagConstraints.WEST));
            
            add(gradeLabel, getConstraints(4,3,1,1, GridBagConstraints.EAST));
            add(gradeTextField, getConstraints(5,3,1,1, GridBagConstraints.WEST));
		    add(averageGradeLabel, getConstraints(4,4,1,1, GridBagConstraints.EAST));
            add(averageGradeTextField, getConstraints(5,4,1,1, GridBagConstraints.WEST));
		    
            add(exitButton, getConstraints(4,5,1,1, GridBagConstraints.EAST));
            add(calculateButton, getConstraints(5,5,1,1, GridBagConstraints.WEST));
            
            
        }

        public void setAddEditMode(boolean e)
        {
            addButton.setEnabled(!e);
            editButton.setEnabled(!e);
            deleteButton.setEnabled(!e);
            acceptButton.setEnabled(e);
            cancelButton.setEnabled(e);
        }
    }

    class AddListener implements ActionListener

    {
        public void actionPerformed(ActionEvent e)
        {
            //newProduct = new Product();
            studentPanel.clearFields();
            buttonPanel.setAddEditMode(true);
            studentPanel.setAddMode();
        }
    }

    class EditListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            buttonPanel.setAddEditMode(true);
            studentPanel.setEditMode();
        }
    }

    class DeleteListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           
            selectorPanel.productComboBox.requestFocusInWindow();
        }
    }

    class AcceptListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           // if (isValidData())
            
                
              
               
                studentPanel.setDisplayMode();
                buttonPanel.setAddEditMode(false);
                selectorPanel.productComboBox.requestFocusInWindow();
            }
        }

      

    }

    class CancelListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           

        }
    }

    class ExitListener implements ActionListener
    {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        }


 class CalculateListener implements ActionListener
 {
	 public void actionPerformed(ActionEvent e)
     {
         
     }
 }





