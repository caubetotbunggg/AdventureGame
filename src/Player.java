import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int defaultHealth;
    private int money;
    private String characterName;
    private String name;
    private Inventory inventory;
    private Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        Character[] charList = {new Samurai(), new Archer(), new Chevalier()};
        System.out.println("----------------------------------------");
        System.out.println("Hay chọn một nhân vật... ");
        for (Character c : charList) {
            System.out.println(c.getName() + " (Hư hại: " +
                    c.getDamage() + ", Sức khỏe: " +
                    c.getHealth() + ", Gia tri: " +
                    c.getMoney() + ")");
        }
        System.out.println("----------------------------------------");
        System.out.println("Samurai - 1 \nVampire - 2 \nChevalier - 3 \nChonmột con số : ");
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Chevalier());
                break;
            default:
                System.out.println("Bạn đã chọn sai nhân vật. Chúng tôi vẫn sẽ chấp nhận bạn là một samurai!");
                initPlayer(new Samurai());
        }
    }

    public void initPlayer(Character character) {
        this.setCharacterName(character.getName());
        this.setDamage(character.getDamage());
        this.setHealth(character.getHealth());
        this.setDefaultHealth(character.getHealth());
        this.setMoney(character.getMoney());
    }

    public void printInfo() {
        System.out.println(this.getName() + " -" + this.getCharacterName() + "-" + " (súng của bạn: " +
                this.getInventory().getGun().getName() + ", sức mạnh tấn công: " +
                this.getTotalDamage() + ", áo giáp của bạn: " +
                this.getInventory().getArmor().getName() + ", Sức mạnh chặn: " +
                this.getInventory().getArmor().getBlock() + ", Tình trạng sức khỏe: " +
                this.getHealth() + ", tình hình tiền bạc: " +
                this.getMoney() + ")");
    }

    public int getTotalDamage() {
        return this.damage + this.getInventory().getGun().getDamage();
    }

    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
