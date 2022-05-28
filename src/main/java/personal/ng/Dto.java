package personal.ng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "body",
        "permalink",
        "author",
        "title",
        "tags",
        "comments",
        "date"
})
@Generated("jsonschema2pojo")
public class Dto implements Serializable
{
    @JsonProperty("body")
    private String body;
    @JsonProperty("permalink")
    private String permalink;
    @JsonProperty("author")
    private String author;
    @JsonProperty("title")
    private String title;
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("comments")
    private List<Comment> comments = new ArrayList<Comment>();
    @JsonProperty("date")
    private String date;
    private final static long serialVersionUID = 4647430736601194409L;

    @JsonProperty("body")
    public String getBody() {
        return body;
    }
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("permalink")
    public String getPermalink() {
        return permalink;
    }
    @JsonProperty("permalink")
    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }
    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }
    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }
}

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "body",
        "email",
        "author"
})
@Generated("jsonschema2pojo")
class Comment implements Serializable
{
    @JsonProperty("body")
    private String body;
    @JsonProperty("email")
    private String email;
    @JsonProperty("author")
    private String author;
    private final static long serialVersionUID = -3608862303010402241L;

    @JsonProperty("body")
    public String getBody() {
        return body;
    }
    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

}