import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Watch extends JFrame implements ActionListener {
    JButton startandstop = new JButton();
    JButton reset = new JButton();
    static JLabel label = new JLabel();

    Watch() {
        this.setLayout(null);
        //ImageIcon icon=new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());
        label.setBounds(100, 90, 200, 112);
        label.setText(solvewatchhms());
        label.setFont(new Font("Calibri", Font.ITALIC, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        startandstop.setBounds(100, 200, 100, 40);
        startandstop.setText("Start");
        startandstop.setFont(new Font("Gill Sans MT", Font.ITALIC, 16));
        reset.setBounds(200, 200, 100, 40);
        reset.setText("Reset");
        reset.setFont(new Font("Tahoma", Font.ITALIC, 16));
        startandstop.setVisible(true);
        reset.setVisible(true);
        label.setVisible(true);
        label.setBackground(Color.cyan);
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(Color.pink));
        startandstop.addActionListener(this);
        startandstop.setBorder(BorderFactory.createEtchedBorder());
        reset.setBorder(BorderFactory.createEtchedBorder());
        reset.addActionListener(this);
        this.setSize(400, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.magenta);
        this.add(startandstop);
        this.add(reset);
        this.add(label);


    }

    int a = 3;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startandstop) {
            if (a % 2 == 1) {
                startandstop.setText("Stop");
                startwatch();
            } else {
                startandstop.setText("Start");
                stopwatch();
            }
            a += 1;
        } else {
            hours = 0;
            minutes = 0;
            seconds = 0;
            label.setText("00:00:00");
        }
    }

    static Timer timer;
    static int hours = Date.from(Instant.now()).getHours();
    static int minutes = Date.from(Instant.now()).getMinutes();
    static int seconds = Date.from(Instant.now()).getSeconds();

    public static void startwatch() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                label.setText(solvewatchhms());
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public static String solvewatchhms() {
        String[] result = new String[3];
        if (String.valueOf(seconds).length() == 1) {
            result[2] = '0' + String.valueOf(seconds);
        } else if (seconds == 60) {
            result[2] = "00";
            seconds = 0;
            minutes += 1;
        } else {
            result[2] = String.valueOf(seconds);
        }
        if (String.valueOf(minutes).length() == 1) {
            result[1] = '0' + String.valueOf(minutes) + ":";
        } else if (minutes == 60) {
            result[1] = "00:";
            minutes = 0;
            hours += 1;
        } else {
            result[1] = minutes + ":";
        }
        if (String.valueOf(hours).length() == 1) {
            result[0] = '0' + String.valueOf(hours) + ":";
        } else if (hours == 24) {
            result[0] = "00:";

        } else {
            result[0] = hours + ":";
        }
        seconds += 1;
        return result[0]+result[1]+result[2];
    }

    public static void stopwatch() {
        timer.cancel();
    }

}
