import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Chào mừng bạn đến với trò chơi phiêu lưu:");
        System.out.println("Nhập tên nhân vật: ");
        String name = input.nextLine();
        Player player = new Player(name);
        System.out.println("Chào mừng " + player.getName() + ". Bạn đang một mình trên một hòn đảo hoang vắng!");
        player.selectChar();
        Location location;
        while (true) {
            System.out.println("----------------------------------------");
            player.printInfo();
            System.out.println("----------------------------------------");
            System.out.println("Khu vực: ");
            System.out.println("Thoát khỏi trò chơi - 0");
            System.out.println("Ngôi Nhà An Toàn - 1 " +
                    "(Sức khỏe của bạn đã được phục hồi. Quái vật không thể đến đây.)");
            System.out.println("Cửa hàng - 2" +
                    "(Bạn có thể mua vũ khí hoặc áo giáp.)");
            System.out.println("Đi rừng - 3" +
                    "(Bạn có thể gặp quái vật ở sâu trong rừng.)");
            System.out.println("Vào hang - 4 " +
                    "(Có thể có quái vật bên trong hang động.)");
            System.out.println("Xuống sông - 5" +
                    "(Quái vật có thể đang đợi bạn dưới sông.)");
            System.out.println("----------------------------------------");
            System.out.println("Chọn khu vực bạn muốn đến:");
            int selectLocation = input.nextInt();

            switch (selectLocation) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Forest(player);
                    break;
                case 4:
                    location = new Cave(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Bạn đang được dẫn đến một ngôi nhà an toàn...");
                    location = new SafeHouse(player);
            }
            if (location == null) {
                System.out.println("Trò chơi kết thúc. Chúng tôi chờ đợi một lần nữa.");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("Bạn đã chết! Trò chơi kết thúc.");
                break;
            }
        }

    }
}
