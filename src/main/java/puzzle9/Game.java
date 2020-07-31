package puzzle9;import javax.swing.*;import java.awt.*;import java.awt.event.KeyEvent;import java.awt.event.KeyListener;import java.util.ArrayList;import java.util.List;import java.util.Random;public class Game extends JPanel implements Runnable, KeyListener {    List<Point> randNumber = new ArrayList<>();    private Thread puzzleThread;    Piece[][]piece={            {new Piece(this),new Piece(this),new Piece(this)},            {new Piece(this),new Piece(this),new Piece(this)},            {new Piece(this),new Piece(this),new Piece(this)}    };    private boolean up=false;    private boolean down=false;    private boolean left=false;    private boolean right=false;    private int x=-1;    private int y=-1;    private int a=-1;    private int b=-1;    private int move;    private void findPiece(int x,int y) {        for(int i=0;i<3;i++){            for(int j=0;j<3;j++){                if ((int)piece[i][j].getPointPiece().getX()==x&&(int)piece[i][j].getPointPiece().getY()==y) {                    this.a=i;                    this.b=j;                }            }        }    }    public boolean win(){        for(int i=0;i<3;i++){            for(int j=0;j<3;j++){                if((int)piece[i][j].getPointPiece().getX()!=j||(int)piece[i][j].getPointPiece().getY()!=i){                    return false;                }                if((int)piece[i][j].getPointPiece().getX()==j&&(int)piece[i][j].getPointPiece().getY()==i&&i==2&&j==2){                        return true;                }            }        }        return false;    }    public void init(int selection){     if(selection==0) {        randNumber.add(new Point(2,2));        randNumber.add(new Point(0,2));        randNumber.add(new Point(2,1));        randNumber.add(new Point(1,0));        randNumber.add(new Point(1,1));        randNumber.add(new Point(0,1));        randNumber.add(new Point(0,0));        randNumber.add(new Point(2,0));        randNumber.add(new Point(1,2));     }     if(selection==1) {        randNumber.add(new Point(0,1));        randNumber.add(new Point(0,0));        randNumber.add(new Point(1,1));        randNumber.add(new Point(2,1));        randNumber.add(new Point(0,2));        randNumber.add(new Point(2,2));        randNumber.add(new Point(1,2));        randNumber.add(new Point(2,0));        randNumber.add(new Point(1,0));     }     if(selection==2) {        randNumber.add(new Point(1,0));        randNumber.add(new Point(2,0));        randNumber.add(new Point(1,2));        randNumber.add(new Point(0,0));        randNumber.add(new Point(0,2));        randNumber.add(new Point(1,1));        randNumber.add(new Point(2,1));        randNumber.add(new Point(2,2));        randNumber.add(new Point(0,1));     }     if(selection==3) {        randNumber.add(new Point(0,2));        randNumber.add(new Point(2,0));        randNumber.add(new Point(2,2));        randNumber.add(new Point(1,0));        randNumber.add(new Point(0,1));        randNumber.add(new Point(1,2));        randNumber.add(new Point(0,0));        randNumber.add(new Point(2,1));        randNumber.add(new Point(1,1));     }    }    public void mix_number() {        Random random=new Random();        init(random.nextInt(4));        int k=0;        for (int i = 0; i <= 2; i++){            for(int j=0;j<=2;j++){                piece[i][j].setPointPiece(randNumber.get(k));                piece[i][j].setNumber(k);                k++;            }        }        randNumber.clear();    }    public void addNotify(){        super.addNotify();        puzzleThread=new Thread(this);        puzzleThread.start();    }    @Override    public void paint(Graphics g) {        super.paint(g);        Graphics2D g2 = (Graphics2D) g;        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);        for (int i = 0; i <= 2; i++){            for(int j=0;j<=2;j++){                piece[i][j].paint(g2);            }        }    }    private void checking(){        if(piece[2][2].getPointPiece().getX()==2){            left=false;        }        if(piece[2][2].getPointPiece().getX()==0){            right=false;        }        if(piece[2][2].getPointPiece().getY()==2){            up=false;        }        if(piece[2][2].getPointPiece().getY()==0){            down=false;        }    }    public Game(){        setBounds(5, 5, 450, 450);        setBackground(Color.gray);    }    public void move(){        checking();        if(left==true||right==true||down==true||up==true) {            move++;            Point point = piece[2][2].getPointPiece();            piece[2][2].setPointPiece(piece[a][b].getPointPiece());            piece[a][b].setPointPiece(point);            left=false;            right=false;            down=false;            up=false;        }    }    @Override    public void keyTyped(KeyEvent e) {    }    @Override    public void keyPressed(KeyEvent e) {        piece[2][2].keyPressed(e);            if (e.getKeyCode() == KeyEvent.VK_DOWN) {                findPiece(x,y-1);                down = true;                for (int w = 0; w <= 2; w++) {                    for (int k = 0; k <= 2; k++) {                        System.out.println(w + " " + k + piece[w][k].getPointPiece() + " " + piece[w][k].getNumber());                    }                }                System.out.println("");            }            if (e.getKeyCode() == KeyEvent.VK_UP) {                findPiece(x,y+1);                up = true;                for (int w = 0; w <= 2; w++) {                    for (int k = 0; k <= 2; k++) {                        System.out.println(w + " " + k + piece[w][k].getPointPiece() + " " + piece[w][k].getNumber());                    }                }                System.out.println("");            }            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {                findPiece(x-1,y);                right = true;                for (int w = 0; w <= 2; w++) {                    for (int k = 0; k <= 2; k++) {                        System.out.println(w + " " + k + piece[w][k].getPointPiece() + " " + piece[w][k].getNumber());                    }                }                System.out.println("");            }            if (e.getKeyCode() == KeyEvent.VK_LEFT) {                findPiece(x+1,y);                left = true;                for (int w = 0; w <= 2; w++) {                    for (int k = 0; k <= 2; k++) {                        System.out.println(w + " " + k + piece[w][k].getPointPiece() + " " + piece[w][k].getNumber());                    }                }                System.out.println("");            }            piece[a][b].keyPressed(e);            y=(int)piece[2][2].getPointPiece().getY();            x=(int)piece[2][2].getPointPiece().getX();    }    @Override    public void keyReleased(KeyEvent e) {    }    @Override    public void run() {        mix_number();        for(int i=0;i<=2;i++){            for(int j=0;j<=2;j++){                System.out.println(i+" "+ j +piece[i][j].getPointPiece()+" "+piece[i][j].getNumber());            }        }        System.out.println("");        System.out.println("");        new Game();        y=(int)piece[2][2].getPointPiece().getY();        x=(int)piece[2][2].getPointPiece().getX();        while (true){            if(win()==true){                JFrame f=new JFrame();                JOptionPane.showMessageDialog(f,"تو بردی هورررا");                JOptionPane.showMessageDialog(f,"your score is "+move);                break;            }            move();            repaint();        }    }}