package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(null /* null est à remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);
        push.addActionListener(new pushOp());
        boutons.add(add);
        add.addActionListener(new addOp());
        boutons.add(sub);
        sub.addActionListener(new subOp());
        boutons.add(mul);
        mul.addActionListener(new mulOp());
        boutons.add(div);
        div.addActionListener(new divOp());
        boutons.add(clear);
        clear.addActionListener(new clearOp());
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {

        if (pile.estPleine())
            push.setEnabled(false);
        if (pile.estVide()) {
            div.setEnabled(false);
            push.setEnabled(true);
            clear.setEnabled(false);
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
        }
        if (pile.taille() == 1) {
            div.setEnabled(false);
            push.setEnabled(true);
            clear.setEnabled(true);
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
        } else {

            mul.setEnabled(true);
            div.setEnabled(true);
            push.setEnabled(true);
            clear.setEnabled(true);
            add.setEnabled(true);
            sub.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)


    private class addOp implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int val1 = 0, val2 = 0, val = 0;

            try {
                val1 = pile.depiler();
                val2 = pile.depiler();
                val = val2 + val1;
                pile.empiler(val);
            } catch (PileVideException e) {
            } catch (PilePleineException e) {
            }
            actualiserInterface();
        }
    }


    private class mulOp implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int val1 = 0, val2 = 0, mul = 0;

            try {
                val1 = pile.depiler();
                val2 = pile.depiler();
                mul = val2 * val1;
                pile.empiler(mul);
            } catch (PileVideException e) {
            } catch (PilePleineException e) {
            }


            actualiserInterface();
        }
    }

    private class divOp implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int val1 = 0, val2 = 0, div = 0;
            try {
                val1 = pile.depiler();
                val2 = pile.depiler();

                if (val1 == 0) {
                    pile.empiler(val2);
                    pile.empiler(val1);
                } else {
                    div = val2 / val1;
                    pile.empiler(div);
                }
            } catch (PileVideException e) {
            } catch (PilePleineException e) {
            }
            actualiserInterface();
        }
    }

    private class pushOp implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                pile.empiler(operande());
            } catch (NumberFormatException e) {
            } catch (PilePleineException e) {
            }
            actualiserInterface();
        }
    }

    private class subOp implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            int val1 = 0, val2 = 0, sub = 0;
            try {
                val1 = pile.depiler();
                val2 = pile.depiler();
                sub = val2 - val1;
                pile.empiler(valeur);
            } catch (PileVideException e) {
            } catch (PilePleineException e) {
            }
            actualiserInterface();
        }
    }

    private class clearOp implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            while (!pile.estVide()) {
                pile.depiler();
            }
            actualiserInterface();
        }
    }

}
