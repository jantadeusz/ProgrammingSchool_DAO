package pl.coderslab.entity;

public class Group {

    private int id;
    private String name;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public static String getCreateTabGroup() {
        return createTabGroup;
    }

    public static void setCreateTabGroup(String createTabGroup) {
        Group.createTabGroup = createTabGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//============================= database methods ============================================

    public static String createTabGroup = "CREATE TABLE `User_group` (\n" +
            "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;\n";






}
