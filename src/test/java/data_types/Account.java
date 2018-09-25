package data_types;

import common.CommonMethods;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class Account {
    private String name;
    private String screen_name;
    private long id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getId() == account.getId() &&
                Objects.equals(getName(), account.getName()) &&
                Objects.equals(getScreen_name(), account.getScreen_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getScreen_name(), getId());
    }

    public Account() {
        HashMap<String, String> account =
                CommonMethods
                        .jsonFileToListOfHashMaps(
                                new File("src/test/resources/twitterDeveloperAccount.json"))
                        .get(0);
        this.id = Long.valueOf(account.get("id"));
        this.name = account.get("name");
        this.screen_name = account.get("screen_name");
    }

    public Account(String name, String screen_name, int id) {
        this.name = name;
        this.screen_name = screen_name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public long getId() {
        return id;
    }
}
