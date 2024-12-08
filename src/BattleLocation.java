import java.util.Random;
import java.util.Scanner;

public abstract class BattleLocation extends Location {
    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLocation(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int rndMonster = this.randomMonsterNumber();
        System.out.println("#####################################");
        System.out.println("Bạn hiện đang ở đây: " + this.getName());
        System.out.println(this.getMonster().getName() + " Anh ấy ngửi thấy mùi của bạn. Bạn phải cẩn thận.");
        System.out.println("Con " + rndMonster + " " + this.getMonster().getName() + " vẫn còn sống.");
        System.out.println("#####################################");
        System.out.println("CHIẾN ĐẤU hoặc rút lui :");
        String select = input.nextLine();
        select = select.toLowerCase().replace(" ", "");
        if (select.equals("chiến tranh") || select.equals("chiến tranh")) {
            if (combat(rndMonster)) {
                System.out.println(this.getName() + " đã sạch quái vật.");
                return true;
            }
        }
        if (this.getPlayer().getHealth() <= 0) {
            return false;
        }
        return true;
    }

    public boolean combat(int monsterNumber) {
        for (int i = 1; i <= monsterNumber; i++) {
            this.getMonster().setHealth(this.getMonster().getDefaultHealth());
            this.playerStatus();
            this.monsterStatus(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("TẤN CÔNG hoặc CHUYẾN BAY: ");
                String choice = input.nextLine();
                choice = choice.toLowerCase().trim().replace(" ", "");
                if (choice.equals("tấn công") || choice.equals("tấn công")) {
                    int playerHealth = this.getPlayer().getHealth();
                    int monsterHealth = this.getMonster().getHealth();
                    int playerAttack = this.getPlayer().getTotalDamage();
                    int monsterAttack = this.getMonster().getDamage();
                    System.out.println("Bạn đã tấn công quái vật !");
                    this.getMonster().setHealth(monsterHealth - playerAttack);
                    afterHit();
                    if (this.getMonster().getHealth() > 0) {
                        System.out.println("Quái vật tấn công bạn !");
                        int playerBlock = this.getPlayer().getInventory().getArmor().getBlock();
                        monsterAttack = monsterAttack - playerBlock;
                        if (monsterAttack < 0) {
                            monsterAttack = 0;
                        }
                        if (playerBlock > 0) {
                            System.out.println("cuộc tấn công quái vật '" + playerBlock + "' bị chặn.");
                        } else {
                            System.out.println("Bạn không có áo giáp, đòn tấn công không thể bị chặn.");
                        }
                        this.getPlayer().setHealth(playerHealth - monsterAttack);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() <= 0) {
                System.out.println(i + ". " + this.getMonster().getName() + "đã bị giết.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Chiến lợi phẩm thu được: " + this.getMonster().getAward() + " ĐẾN");
            } else {
                return false;
            }
        }
        return true;
    }

    public void playerStatus() {
        System.out.println("****************************************");
        System.out.println(this.getPlayer().getCharacterName() + "Trạng thái:");
        System.out.println("Sức khỏe : " + this.getPlayer().getHealth());
        System.out.println("sức mạnh tấn công : " + this.getPlayer().getTotalDamage());
        System.out.println("sức phòng thủ : " + this.getPlayer().getInventory().getArmor().getBlock());
    }

    public void monsterStatus(int i) {
        System.out.println("****************************************");
        System.out.println(i + ". " + this.getMonster().getName() + " Trạng thái:");
        System.out.println(this.getMonster().getName() + " nhân vật phản diện :" + this.getMonster().getHealth());
        System.out.println("sức mạnh tấn công : " + this.getMonster().getDamage());
    }

    public void afterHit() {
        System.out.println("bạn thân mến: " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + " nhân vật phản diện :" + this.getMonster().getHealth());
        System.out.println();
    }

    public int randomMonsterNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return this.monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
