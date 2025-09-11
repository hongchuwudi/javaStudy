package anonymousInternalClass.instances2;

import javax.swing.*;
import java.awt.*;

public class Test {
    //  目标: 搞清楚几个匿名内部类的使用场景
    //  需求: 创建一个登录窗口,窗口上只有一个登录按钮
    //  GUI 编程 使用场景

    // 1.设置Windows系统外观
    // 2. 创建主窗口
    // 3. 设置Windows图标
    // 4. 设置Windows图标
    // 5. 创建菜单栏
    // 6. 主内容面板
    // 7. 添加组件
    public static void main(String[] args) {

        try {
            // 设置Windows系统外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 创建主窗口
        JFrame frame = new JFrame("Windows风格应用");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // 设置Windows图标
        ImageIcon icon = new ImageIcon("windows_icon.png");
        frame.setIconImage(icon.getImage());

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem exitItem = new JMenuItem("退出");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        // 主内容面板
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 添加组件
        JLabel label = new JLabel("欢迎使用Windows风格Java应用", SwingConstants.CENTER);
        label.setFont(new Font("微软雅黑", Font.BOLD, 18));
        panel.add(label, BorderLayout.CENTER);

        JButton button = new JButton("点击这里");
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "这是一个Windows风格的消息框",
                    "提示",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(button, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

}

