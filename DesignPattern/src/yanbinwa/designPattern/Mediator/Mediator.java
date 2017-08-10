package yanbinwa.designPattern.Mediator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mediator
{
    // Colleague
    interface Command 
    {
        void execute();
    }
    
    @SuppressWarnings("serial")
    class BtnView extends JButton implements Command
    {

        MediatorIf med;
        
        public BtnView(ActionListener al, MediatorIf m)
        {
            super("View");
            addActionListener(al);
            med = m;
            med.registerView(this);
        }
        
        @Override
        public void execute()
        {
            med.view();
        }
        
    }
    
    @SuppressWarnings("serial")
    class BtnSearch extends JButton implements Command
    {
        
        MediatorIf med;

        public BtnSearch(ActionListener al, MediatorIf m)
        {
            super("Search");
            addActionListener(al);
            med = m;
            med.registerSearch(this);
        }
        
        @Override
        public void execute()
        {
            med.search();
        }
        
    }
    
    @SuppressWarnings("serial")
    class BtnBook extends JButton implements Command
    {
        
        MediatorIf med;

        public BtnBook(ActionListener al, MediatorIf m)
        {
            super("Book");
            addActionListener(al);
            med = m;
            med.registerBook(this);
        }
        
        @Override
        public void execute()
        {
            med.book();
        }
        
    }
    
    @SuppressWarnings("serial")
    class LblDisplay extends JLabel {

        MediatorIf med;

        LblDisplay(MediatorIf m) {
            super("Just start...");
            med = m;
            med.registerDisplay(this);
            setFont(new Font("Arial", Font.BOLD, 24));
        }

    }
    
    interface MediatorIf 
    {
        void book();
        void view();
        void search();
        void registerView(BtnView v);
        void registerSearch(BtnSearch s);
        void registerBook(BtnBook b);
        void registerDisplay(LblDisplay d);
    }

    class MediatorIfImpl implements MediatorIf
    {

        BtnView btnView;
        BtnSearch btnSearch;
        BtnBook btnBook;
        LblDisplay show;
        
        @Override
        public void book()
        {
            btnView.setEnabled(false);
            btnSearch.setEnabled(false);
            btnBook.setEnabled(true);
            show.setText("Book");
        }

        @Override
        public void view()
        {
            btnView.setEnabled(true);
            btnSearch.setEnabled(false);
            btnBook.setEnabled(false);
            show.setText("View");
        }

        @Override
        public void search()
        {
            btnView.setEnabled(false);
            btnSearch.setEnabled(true);
            btnBook.setEnabled(false);
            show.setText("Search");
        }

        @Override
        public void registerView(BtnView v)
        {
            this.btnView = v;
        }

        @Override
        public void registerSearch(BtnSearch s)
        {
            this.btnSearch = s;
        }

        @Override
        public void registerBook(BtnBook b)
        {
            this.btnBook = b;
        }

        @Override
        public void registerDisplay(LblDisplay d)
        {
            this.show = d;
        }
        
    }
    
    @SuppressWarnings("serial")
    class MediatorDemo extends JFrame implements ActionListener
    {

        MediatorIf mediator = new MediatorIfImpl();
        
        public MediatorDemo()
        {
            BtnView btnView = new BtnView(this, mediator);
            BtnSearch btnSearch = new BtnSearch(this, mediator);
            BtnBook btnBook = new BtnBook(this, mediator);
            LblDisplay show = new LblDisplay(mediator);
            JPanel p = new JPanel();
            p.add(btnView);
            p.add(btnSearch);
            p.add(btnBook);
            getContentPane().add(show, "North");
            getContentPane().add(p, "South");
            setSize(400, 200);
            setVisible(true);
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Command comd = (Command) e.getSource();
            comd.execute();
        }
        
    }
    
    public void test()
    {
        new MediatorDemo(); 
    }
    
    public static void main(String[] args)
    {
        new Mediator().test();
    }
    
}
