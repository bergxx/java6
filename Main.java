import java.util.*;

class Laptop {
    private String модель;
    private int ОЗУ;
    private int ОбъемЖД;
    private String операционнаяСистема;
    private String цвет;

    public Laptop(String модель, int ОЗУ, int ОбъемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.ОЗУ = ОЗУ;
        this.ОбъемЖД = ОбъемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    public String getModel() {
        return модель;
    }

    public int getRAM() {
        return ОЗУ;
    }

    public int getStorageCapacity() {
        return ОбъемЖД;
    }

    public String getOperatingSystem() {
        return операционнаяСистема;
    }

    public String getColor() {
        return цвет;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "модель='" + модель + '\'' +
                ", ОЗУ=" + ОЗУ +
                ", ОбъемЖД=" + ОбъемЖД +
                ", операционнаяСистема='" + операционнаяСистема + '\'' +
                ", цвет='" + цвет + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Set<Laptop> laptops = createSet();
        Map<String, Object> criteria = requestCriteria();
        filterAndDisplay(laptops, criteria);
    }

    public static Set<Laptop> createSet() {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Acer Swift", 16, 512, "Windows 10", "Серый"));
        laptops.add(new Laptop("MSI Prestige", 16, 256, "Windows 11", "Черный"));
        laptops.add(new Laptop("Huawei MateBook", 8, 256, "Windows 10", "Серебристый"));
        laptops.add(new Laptop("Samsung Galaxy Book", 12, 512, "Windows 10", "Синий"));
        laptops.add(new Laptop("LG Gram", 8, 256, "Windows 10", "Белый"));
        return laptops;
    }

    public static Map<String, Object> requestCriteria() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();
        System.out.println("Выберите критерии фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int RAM = scanner.nextInt();
                criteria.put("ОЗУ", RAM);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int storageCapacity = scanner.nextInt();
                criteria.put("ОбъемЖД", storageCapacity);
                break;
            case 3:
                scanner.nextLine();
                System.out.println("Введите операционную систему:");
                String operatingSystem = scanner.nextLine();
                criteria.put("ОперационнаяСистема", operatingSystem);
                break;
            case 4:
                scanner.nextLine();
                System.out.println("Введите цвет:");
                String color = scanner.nextLine();
                criteria.put("Цвет", color);
                break;
            default:
                System.out.println("Некорректный выбор.");
        }
        return criteria;
    }

    public static void filterAndDisplay(Set<Laptop> laptops, Map<String, Object> criteria) {
        System.out.println("Найденные laptops:");
        for (Laptop laptop : laptops) {
            boolean fitsCriteria = true;
            for (Map.Entry<String, Object> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ОЗУ":
                        if (laptop.getRAM() < (int) entry.getValue()) {
                            fitsCriteria = false;
                        }
                        break;
                    case "ОбъемЖД":
                        if (laptop.getStorageCapacity() < (int) entry.getValue()) {
                            fitsCriteria = false;
                        }
                        break;
                    case "ОперационнаяСистема":
                        if (!laptop.getOperatingSystem().equalsIgnoreCase((String) entry.getValue())) {
                            fitsCriteria = false;
                        }
                        break;
                    case "Цвет":
                        if (!laptop.getColor().equalsIgnoreCase((String) entry.getValue())) {
                            fitsCriteria = false;
                        }
                        break;
                    default:
                        System.out.println("Некорректный критерий.");
                }
            }
            if (fitsCriteria) {
                System.out.println(laptop);
            }
        }
    }
}