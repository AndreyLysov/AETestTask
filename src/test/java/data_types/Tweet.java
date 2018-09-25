package data_types;

import io.restassured.response.Response;

import java.util.Objects;

import static common.CommonMethods.generateString;

public class Tweet {
    private String text;
    private Account account;

    public Tweet() {
        this.text = generateString();
        this.account = new Account();
    }

    public Tweet(String text, Account account) {
        this.text = text;
        this.account = account;
    }

    public Tweet(Response response) {
        this.text = response.getBody().jsonPath().getString("text");
        new Account(
                response.getBody().jsonPath().getString("user.name"),
                response.getBody().jsonPath().getString("user.screen_name"),
                response.getBody().jsonPath().getInt("user.id")
        );
    }

    public Account getAccount() {
        return account;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(getText(), tweet.getText()) &&
                Objects.equals(getAccount(), tweet.getAccount());
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "text='" + text + '\'' +
                ", account=" + account +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getAccount());
    }


}
